package glextra.network.tcp;

import glextra.network.Msg;
import glextra.network.MsgDefines;

import java.io.IOException;

public interface TCPConnection {
	
	
	public int getPort();
	public String getHost();

	public void setDefines(MsgDefines defines);
	public MsgDefines getDefines();
	
	public void addListener(TCPConnectionListener l);
	
	public boolean isOpen();
	
	public void open() throws IOException;
	public void close() throws IOException;
	
	public void listen();
	
	public void write(Msg msg) throws IOException;
}
