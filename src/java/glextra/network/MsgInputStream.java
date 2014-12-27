package glextra.network;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class MsgInputStream {
	private InputStream m_input;
	private MsgDefines m_defines;
		
	public MsgInputStream(InputStream in, MsgDefines defines) {
		m_input = in;
		m_defines = defines;
	}
	/**
	 * Reads a message from the input stream,
	 * will return null if the end of the stream has been reached
	 * @return 
	 * @throws IOException 
	 */
	public Msg read() throws IOException {
		try {
			Msg msg = new Msg();
			msg.read(m_input, m_defines);
			return msg;
		} catch (EOFException e) { return null; }
	}
}
