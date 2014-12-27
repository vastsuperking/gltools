package glextra.network.test;

import java.io.IOException;
import java.net.Socket;

import glextra.network.Msg;
import glextra.network.MsgDefines;
import glextra.network.MsgInputStream;
import glextra.network.Field.ByteField;
import glextra.network.Field.FloatField;
import glextra.network.Field.IntField;
import glextra.network.Field.StringField;

public class MsgClientTest {
	private MsgDefines m_defines;
	public MsgClientTest(MsgDefines defines) {
		m_defines = defines;
	}
	
	public void run() throws IOException {
		Socket s = new Socket("localhost", 3000);
		MsgInputStream in = new MsgInputStream(s.getInputStream(), m_defines);
		
		Msg msg = null;
		while((msg = in.read()) != null) {
			System.out.println(msg);
		}
		s.close();
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
