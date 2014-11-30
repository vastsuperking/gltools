package gltools.gl;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CurrentGL {
	//All threads with OGL contexts
	private static HashMap<GL, Thread> s_contextThreads = new HashMap<GL, Thread>();
	//Prevents a GLContext from being in two threads
	private static HashMap<GL, Lock> s_GLLocks = new HashMap<GL, Lock>();
	//Current GLContexts, used for synchronizing other objects as well
	private static HashMap<Thread, GL> s_GLs = new HashMap<Thread, GL>();
	
	private static Lock s_changeContextLock = new ReentrantLock();
	
	public static GL s_getCurrent() {
		synchronized(s_GLs) {
			return s_GLs.get(Thread.currentThread());
		}
	}
	public static boolean s_isCurrent(GL gl) {
		synchronized(s_GLs) {
			return s_GLs.get(Thread.currentThread()) == gl;
		}
	}
	
	public static void s_makeCurrent(GL gl) {
		s_changeContextLock.lock();
		Thread thread = Thread.currentThread();

		//If there is a GL in the current thread, throw an exception,
		//as that one has not yet finished
		if (s_contextThreads.containsValue(thread)) {
			throw new RuntimeException("Context in thread! Must call release()"
					+ " on other context first!");
		} else {
			//If not, this thread has a gl context
			s_contextThreads.put(gl, thread);
		}



		//Grab a context-specific lock so that no one
		//can put the context in a different thread
		if (!s_GLLocks.containsKey(gl)) s_GLLocks.put(gl, new ReentrantLock());

		//Unlock while we wait for release to free the context
		s_changeContextLock.unlock();
		s_GLLocks.get(gl).lock();		
		s_changeContextLock.lock();
		//Finally, make the context current
		s_GLs.put(thread, gl);
		s_changeContextLock.unlock();
	}
	public static void s_releaseCurrent(GL gl) {
		s_changeContextLock.lock();
		//If there is no opengl in this thread, throw an error
		if (!s_GLs.containsKey(Thread.currentThread()))
			throw new RuntimeException("No context in thread!");
		else s_GLs.remove(Thread.currentThread());

		//We are done with this context,
		//other threads can now use it
		if (s_GLLocks.containsKey(gl)) {
			s_GLLocks.get(gl).unlock();
			//Remove the lock so the
			//garbage collector can remove
			//the context if need be
			s_GLLocks.remove(gl);
		}
		//This gl context no longer belongs to a thread
		s_contextThreads.remove(gl);
		s_changeContextLock.unlock();
	}
	public static void s_disposeContext(GL gl) {
		s_GLLocks.remove(gl);
	}
}
