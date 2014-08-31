package gltools;

import java.util.ArrayList;
import java.util.Iterator;

public interface Primitive extends Iterable<Vertex> {
	public ArrayList<Vertex> getVertices();
	public int getVertexCount();
	public Mode getMode();
	
	@Override
	public Iterator<Vertex> iterator();
}
