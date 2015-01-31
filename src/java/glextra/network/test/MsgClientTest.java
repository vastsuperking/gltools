package glextra.network.test;

import glextra.network.Msg;
import glextra.network.MsgDefines;
import glextra.network.Field.ByteField;
import glextra.network.Field.FloatField;
import glextra.network.Field.IntField;
import glextra.network.Field.StringField;
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
		client.write(create());
		
	}
	public Msg create() {
		Msg send = m_defines.create("testMsg");
		send.setByte("byte", (byte) 0x10);
		send.setInt("foo", 5);
		send.setFloat("bar", 10);
		send.setString("foobar", "foooooo");
		return send;
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
		
		MsgClientTest test = new MsgClientTest(defines);
		test.run();
	}
}
