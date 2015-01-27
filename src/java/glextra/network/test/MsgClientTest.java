package glextra.network.test;

import glcommon.util.ResourceLocator.ClasspathResourceLocator;
import glextra.network.Msg;
import glextra.network.MsgDefines;
import glextra.network.tcp.TCPClient;
import glextra.network.tcp.TCPConnectionListener;

import java.io.IOException;

public class MsgClientTest {
	private MsgDefines m_defines;
	public MsgClientTest(MsgDefines defines) {
		m_defines = defines;
	}
	
	public void run() throws IOException {
		TCPClient client = new TCPClient("localhost", 3000);
		client.setDefines(m_defines);
		client.connect();
		client.addListener(new TCPConnectionListener() {
			@Override
			public void connectionClosed() {
				System.out.println("Connection closed!");
			}
			@Override
			public void msg(Msg m) {
				System.out.println("Received: " + m);
			}
		});
		client.listen();
	}
	
	public static void main(String[] args) throws IOException {
		MsgDefines defines = new MsgDefines();
		
		defines.loadXML("Test/msgs.msg", new ClasspathResourceLocator());
		
		MsgClientTest test = new MsgClientTest(defines);
		test.run();
	}
}
