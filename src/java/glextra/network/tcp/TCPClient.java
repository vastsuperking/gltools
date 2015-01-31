package glextra.network.tcp;

import glextra.network.Msg;
import glextra.network.MsgDefines;
import glextra.network.MsgInputStream;
import glextra.network.MsgOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashSet;

public class TCPClient implements TCPConnection, Runnable {
	private int m_port;
	private String m_host;
	
	private Socket m_socket = null;
	private MsgOutputStream m_out;
	
	private MsgDefines m_defines;
	
	private Thread m_listenThread;
	
	private HashSet<TCPConnectionListener> m_listeners = new HashSet<TCPConnectionListener>();
	
	public TCPClient(String host, int port) {
		m_port = port;
		m_host = host;
	}
	protected TCPClient(Socket s) throws IOException {
		m_port = s.getPort();
		m_host = s.getInetAddress().getHostName();
		m_socket = s;
		m_out = new MsgOutputStream(m_socket.getOutputStream());
	}
	
	public int getPort() { return m_port; }
	public String getHost() { return m_host; }
	
	public MsgDefines getDefines() { return m_defines; }
	
	public void setDefines(MsgDefines defines) { m_defines = defines; }

	public boolean isOpen() { return m_socket != null; }
	
	public void addListener(TCPConnectionListener l) {
		synchronized(m_listeners) {
			m_listeners.add(l);			
		}
	}
	
	public void connect() throws IOException {
		open();
	}
	public void disconnect() throws IOException {
		close();
	}
	
	@Override
	public void open() throws IOException {
		m_socket = new Socket(m_host, m_port);
		m_out = new MsgOutputStream(m_socket.getOutputStream());
		if (m_defines == null)
			throw new IllegalStateException("No msg defines set!");
	}

	@Override
	public void listen() {
		if (!isOpen())
			throw new IllegalStateException("Must be open!");
		if (m_listenThread == null) {
			m_listenThread = new Thread(this);
			m_listenThread.start();
		}
	}
	
	@Override
	public void close() throws IOException {
		if (m_socket != null) {
			m_socket.close();
			m_socket = null;
		}
		if (m_listenThread != null && m_listenThread.isAlive()) {
			m_listenThread.interrupt();
		}
		if (m_listenThread != null) m_listenThread = null;
	}

	@Override
	public void write(Msg msg) throws IOException {
		if (!isOpen())
			throw new IllegalStateException("Must be open!");
		m_out.write(msg);
		m_out.flush();
		System.out.println("Writing: " + msg); 
 	}

	@Override
	public void run() {
		InputStream is = null;
		try {
			is = m_socket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error! Stopping listening!");
			return;
		}
		MsgInputStream in = new MsgInputStream(is, m_defines);
		
		while(true) {
			try {
				Msg m = in.read();
				if (m == null) {
					synchronized(m_listeners) {
						for (TCPConnectionListener l : m_listeners) l.connectionClosed();;
					}
					disconnect();
					return;
				}
				synchronized(m_listeners) {
					for (TCPConnectionListener l : m_listeners) l.msg(m);;
				}
				if (Thread.currentThread().isInterrupted()) break;
			} catch(SocketException e) {
				//The connection has closed, exit silently
				synchronized(m_listeners) {
					for (TCPConnectionListener l : m_listeners) l.connectionClosed();;
				}
				return;
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error! Stopping listening!");
				break;
			}
		}
	}
}
