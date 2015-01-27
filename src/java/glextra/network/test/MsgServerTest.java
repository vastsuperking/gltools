package glextra.network.test;

import glextra.network.Field.ByteField;
import glextra.network.Field.FloatField;
import glextra.network.Field.IntField;
import glextra.network.Field.StringField;
import glextra.network.Msg;
import glextra.network.MsgDefines;
import glextra.network.tcp.TCPConnection;
import glextra.network.tcp.TCPServer;
import glextra.network.tcp.TCPServerListener;

import java.io.IOException;

public class MsgServerTest {
	private MsgDefines m_defines;
	public MsgServerTest(MsgDefines defines) {
		m_defines = defines;
	}
	
	public void run() throws IOException {
		final Msg send = m_defines.create("testMsg");
		send.setByte("byte", (byte) 0x10);
		send.setInt("foo", 5);
		send.setFloat("bar", 10);
		send.setString("foobar", "foooooo");
		
		TCPServer server = new TCPServer(3000);
		server.open();
		server.addListener(new TCPServerListener() {
			@Override
			public void msg(TCPConnection client, Msg msg) {
				System.out.println("Message received: " + msg + " from " + client);
			}

			@Override
			public void clientConnected(TCPConnection client) {
				System.out.println("Client connected: " + client);
				client.listen();
				try {
					client.write(send);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void clientDisconnected(TCPConnection client) {
				System.out.println("Client disconnected: " + client);
			}
		});
		server.listen();
	}
	
	public static void main(String[] args) throws IOException {
		MsgDefines defines = new MsgDefines();
		
		Msg def = new Msg();
		def.setTypeByte((byte) 0x01);
		def.setTypeName("testMsg");
		def.addField(new ByteField("byte"));
		def.addField(new IntField("foo"));
		def.addField(new FloatField("bar"));
		def.addField(new StringField("foobar"));
		
		defines.addDefinition(def);
		
		MsgServerTest test = new MsgServerTest(defines);
		test.run();
	}
}
