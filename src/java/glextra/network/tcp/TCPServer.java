package glextra.network.tcp;

import glextra.network.Msg;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class TCPServer implements Runnable {
	private int m_port;
	
	private ServerSocket m_socket;
	
	private HashSet<TCPServerListener> m_listeners = new HashSet<TCPServerListener>();
	private Thread m_listenerThread;
	
	public TCPServer(int port) { m_port = port; }
	
	public int getPort() {
		return m_port;
	}


	public boolean isOpen() {
		return m_socket != null;
	}
	
	public void addListener(TCPServerListener l) {
		synchronized(m_listeners) {
			m_listeners.add(l);
		}
	}

	public void open() throws IOException {
		m_socket = new ServerSocket(m_port);
	}

	
	public void close() throws IOException {
		if (m_socket != null) {
			m_socket.close();
			m_socket = null;
		}
		if (m_listenerThread != null) {
			m_listenerThread.interrupt();
			m_listenerThread = null;
		}
	}

	public void listen() {
		if (m_listenerThread == null) {
			m_listenerThread = new Thread(this);
			m_listenerThread.start();
		}
	}
	
	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			try {
				Socket s = m_socket.accept();
				final TCPClient client = new TCPClient(s);
				client.addListener(new TCPConnectionListener() {
					@Override
					public void msg(Msg msg) {
						synchronized(m_listeners) {
							for (TCPServerListener l : m_listeners) l.msg(client, msg);
						}
					}

					@Override
					public void connectionClosed() {
						synchronized(m_listeners) {
							for (TCPServerListener l : m_listeners) l.clientDisconnected(client);
						}
					}
				});
				synchronized(m_listeners) {
					for (TCPServerListener l : m_listeners) l.clientConnected(client);
				}
				client.listen();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
