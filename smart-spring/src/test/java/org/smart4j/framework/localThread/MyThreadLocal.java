package org.smart4j.framework.localThread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/***
 * 模拟一个对象，实现功能类似ThreadLocal
 * LocalThread 是一个存放线程和变量的容器，key为当前线程，value是要存放的变量
 * @author Administrator
 *
 */
public class MyThreadLocal<T> {
	private Map<Thread,T> container = Collections.synchronizedMap(new HashMap<Thread,T>());
	
	public void set(T value){
		container.put(Thread.currentThread(), value);
	}
	
	
	public T get(){
		Thread currThread = Thread.currentThread();
		T value = container.get(currThread);
		if(value == null || !container.containsKey(Thread.currentThread())){
			value = initialValue();
			container.put(currThread, value);
		}
		return value;
	}


	protected T initialValue() {
		return null;
	}
	
	public void remove(){
		container.remove(Thread.currentThread());
	}
	
	
}
