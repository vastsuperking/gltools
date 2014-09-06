package gltools.shader;

import gltools.vector.Matrix2f;
import gltools.vector.Matrix3f;
import gltools.vector.Matrix4f;
import gltools.vector.Vector2f;
import gltools.vector.Vector3f;
import gltools.vector.Vector4f;

import java.util.ArrayList;
import java.util.Iterator;

public class InputList<T extends Input> extends Input implements Iterable<T> {
	private ArrayList<T> m_inputs = new ArrayList<T>();
	//TODO: Make values arraylist
	//private HashMap<InputUsage, List<T>> m_usageMap = new HashMap<InputUsage, List<T>>();
	
	public void  add(T i) {
		m_inputs.add(i);
		//if (m_usageMap.get(i.getUsage()) == null) m_usageMap.put(i.getUsage(), new ArrayList<T>());  
		//m_usageMap.get(i.getUsage()).add(i);
	}
	public void  remove(T i) { 
		m_inputs.remove(i);
		//if (m_usageMap.get(i.getUsage()) != null) m_usageMap.get(i.getUsage()).remove(i);
	}
	public T get(int i) { return m_inputs.get(i); }
	public int   size() { return m_inputs.size(); }
	public void clear() { m_inputs.clear(); /*m_usageMap.clear();*/ }
	
	@SuppressWarnings("unchecked")
	public <I extends Input> InputList<I> getInputs(Class<I> c, InputUsage usage) {
		if (!usage.getInputType().equals(c)) throw new RuntimeException("Usage " + usage + " is not of type " + c);
		InputList<I> inputs = new InputList<I>();
		for(Input i : m_inputs) {
			if (i.getUsage().equals(usage) && c.isInstance(i)) inputs.add((I) i);
		}
		return inputs;
	}
	@SuppressWarnings("unchecked")
	public <I extends Input> InputList<I> getInputs(Class<I> c) {
		InputList<I> inputs = new InputList<I>();
		for(Input i : m_inputs) {
			if (c.isInstance(i)) inputs.add((I) i);
		}
		return inputs;
	}
	@SuppressWarnings("unchecked")
	public <I extends Input> I getInput(Class<I> c, String name) {
		for (Input i : m_inputs) {
			if (i.getName().equals(name) && c.isInstance(i)) return (I) i;
		}
		throw new RuntimeException("Could not find input of type " + c.getSimpleName() + " and name " + name);
	}
	
	@Override
	public void setValue(int val) {
		for (Input i : m_inputs) {
			i.setValue(val);
		}
	}
	@Override
	public void setValue(boolean val) {
		for (Input i : m_inputs) {
			i.setValue(val);
		}
	}
	
	@Override
	public void setValue(float val) {
		for (Input i : m_inputs) {
			i.setValue(val);
		}
	}

	@Override
	public void setValue(Vector2f val) {
		for (Input i : m_inputs) {
			i.setValue(val);
		}
	}

	@Override
	public void setValue(Vector3f val) {
		for (Input i : m_inputs) {
			i.setValue(val);
		}
	}

	@Override
	public void setValue(Vector4f val) {
		for (Input i : m_inputs) {
			i.setValue(val);
		}
	}

	@Override
	public void setValue(Matrix2f val) {
		for (Input i : m_inputs) {
			i.setValue(val);
		}
	}
	@Override
	public void setValue(Matrix3f val) {
		for (Input i : m_inputs) {
			i.setValue(val);
		}
	}
	@Override
	public void setValue(Matrix4f val) {
		for (Input i : m_inputs) {
			i.setValue(val);
		}
	}
	
	@Override
	public Iterator<T> iterator() {
		return m_inputs.iterator();
	}
	@Override
	public String toString() {
		return "InputList" + m_inputs.toString();
	}
}
