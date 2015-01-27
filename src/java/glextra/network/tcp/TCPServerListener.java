package glextra.network.tcp;

import glextra.network.Msg;


public interface TCPServerListener {
	public void msg(TCPConnection client, Msg msg);
	public void clientConnected(TCPConnection client);
	public void clientDisconnected(TCPConnection client);
}
