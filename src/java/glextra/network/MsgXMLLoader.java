package glextra.network;

import glcommon.util.ResourceLocator;
import glcommon.util.StringUtils;
import glextra.network.Field.ByteField;
import glextra.network.Field.FloatField;
import glextra.network.Field.IntField;
import glextra.network.Field.StringField;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class MsgXMLLoader {
	public static List<Msg> s_read(String res, ResourceLocator locator) throws IOException {
		return s_read(locator.getResource(res));
	}
	public static List<Msg> s_read(InputStream in) throws IOException {
		ArrayList<Msg> messages = new ArrayList<Msg>();
		
		Document doc = Jsoup.parse(in, null, "", Parser.xmlParser());
		Elements msgs = doc.getElementsByTag("msg");
		for (Element m : msgs) {
			messages.add(s_parseMsg(m));
		}
		return messages;
	}
	private static Msg s_parseMsg(Element e) {
		Msg msg = new Msg();
		msg.setTypeName(e.attr("name"));
		msg.setTypeByte(StringUtils.s_parseHexByte(e.attr("type")));
		
		Elements fields = e.children();
		for (Element f : fields) {
			msg.addField(s_parseField(f));
		}
		return msg;
	}
	private static Field s_parseField(Element e) {
		String val = e.text();
		if (val.equals("")) val = null;
		String name = e.attr("name");
		switch(e.tagName()) {
		case "byte": return s_parseByteField(name, val);
		case "int": return s_parseIntField(name, val);
		case "float": return s_parseFloatField(name, val);
		case "string": return s_parseStringField(name, val);
		default: throw new RuntimeException("Cannot parse: " + e);
		}
	}
	private static ByteField s_parseByteField(String name, String val) {
		if (val == null) return new ByteField(name);
		else return new ByteField(name, StringUtils.s_parseHexByte(val));
	}
	private static IntField s_parseIntField(String name, String val) {
		if (val == null) return new IntField(name);
		else return new IntField(name, Integer.parseInt(val));
	}
	private static FloatField s_parseFloatField(String name, String val) {
		if (val == null) return new FloatField(name);
		else return new FloatField(name, Float.parseFloat(val));		
	}
	private static StringField s_parseStringField(String name, String val) {
		if (val == null) return new StringField(name);
		else return new StringField(name, val);
	}
}
