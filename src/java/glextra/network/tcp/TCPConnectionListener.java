package glextra.network.tcp;

import glextra.network.MsgListener;

public interface TCPConnectionListener extends MsgListener {
	public void connectionClosed();
}
