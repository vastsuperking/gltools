package glextra.network;

import java.io.IOException;
import java.io.OutputStream;

public class MsgOutputStream {
	private OutputStream m_out;
	
	public MsgOutputStream(OutputStream out) {
		m_out = out;
	}
	
	public void write(Msg msg) throws IOException {
		msg.write(m_out);
	}
}
