package glextra.network.test;

import glcommon.util.ResourceLocator.ClasspathResourceLocator;
import glextra.network.Msg;
import glextra.network.MsgDefines;
import glextra.network.MsgXMLLoader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

public class MsgBufferTest {
	public static void main(String[] args) throws IOException {
		MsgDefines defines = new MsgDefines();
		
		List<Msg> defs = MsgXMLLoader.s_read("Test/msgs.msg", new ClasspathResourceLocator());
		defines.addAllDefinitions(defs);
		/*Msg def = new Msg
		def.setTypeByte((byte) 0x01);
		def.setTypeName("testMsg");
		def.addField(new ByteField("byte"));
		def.addField(new IntField("foo"));
		def.addField(new FloatField("bar"));
		def.addField(new StringField("foobar"));
		defines.addDefinition(def);
		*/
		
		Msg msg = defines.create("testMsg");
		msg.setByte("byte", (byte) 0x10);
		msg.setInt("foo", 5);
		msg.setFloat("bar", 10);
		msg.setString("foobar", "foooooo");
		System.out.println(msg);
		
		ByteBuffer buffer = msg.create();
		
		Msg msg2 = new Msg();
		msg2.read(buffer, defines);
		System.out.println(msg2);
	}
}
