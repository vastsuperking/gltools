package glextra.network;

import glcommon.util.ResourceLocator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;

public class MsgDefines {
	private HashMap<Byte, Msg> m_messageTypes = new HashMap<Byte, Msg>();
	private HashMap<String, Msg> m_messageNameTypes = new HashMap<String, Msg>();
	
	public void addDefinition(Msg msg) {
		m_messageTypes.put(msg.getTypeByte(), msg);
		m_messageNameTypes.put(msg.getTypeName(), msg);
	}
	public void addAllDefinitions(Collection<? extends Msg> msgs) {
		for (Msg m : msgs) {
			addDefinition(m);
		}
	}
	
	protected Msg getDefinition(byte type) {
		return m_messageTypes.get(type);
	}
	protected Msg getDefinition(String name) {
		return m_messageNameTypes.get(name);
	}
	
	public void loadXML(InputStream in) throws IOException {
		addAllDefinitions(MsgXMLLoader.s_read(in));
	}
	
	public void loadXML(String resource, ResourceLocator loc) throws IOException {
		addAllDefinitions(MsgXMLLoader.s_read(resource, loc));
	}
	
	public Msg create(byte type) {
		Msg def = getDefinition(type);
		Msg msg = new Msg();
		msg.inherit(def);
		return msg;
	}
	
	public Msg create(String type) {
		Msg def = getDefinition(type);
		if (def == null) throw new RuntimeException("No definition for: " + type);
		Msg msg = new Msg();
		msg.inherit(def);
		return msg;
	}
}
