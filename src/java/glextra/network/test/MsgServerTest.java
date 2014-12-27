package glextra.network.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import glextra.network.Msg;
import glextra.network.MsgDefines;
import glextra.network.MsgOutputStream;
import glextra.network.Field.ByteField;
import glextra.network.Field.FloatField;
import glextra.network.Field.IntField;
import glextra.network.Field.StringField;

public class MsgServerTest {
	private MsgDefines m_defines;
	public MsgServerTest(MsgDefines defines) {
		m_defines = defines;
		

	}
	
	public void run() throws IOException {
		Msg send = m_defines.create("testMsg");
		send.setByte("byte", (byte) 0x10);
		send.setInt("foo", 5);
		send.setFloat("bar", 10);
		send.setString("foobar", "foooooo");
		
		ServerSocket socket = new ServerSocket(3000);
		
		Scanner scanner = new Scanner(System.in);
		//Number of times to send the message
		int times = 0;
		while ((times = scanner.nextInt()) != -1) {
			Socket s = socket.accept();
			MsgOutputStream out = new MsgOutputStream(s.getOutputStream());
			for (int i = 0; i < times; i++) out.write(send);
			s.close();
		}
		socket.close();
		scanner.close();
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
