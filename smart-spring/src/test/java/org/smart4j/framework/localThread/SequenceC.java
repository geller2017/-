package org.smart4j.framework.localThread;

public class SequenceC implements Sequence{
	private static MyThreadLocal<Integer> container = new MyThreadLocal<Integer>(){
		protected Integer initialValue() {return 0;}
	};
	@Override
	public int getNumber() {
		container.set(container.get()+1);
		return container.get();
	}
	
	public static void main(String[] args) {
		
		SequenceC sequenceC = new SequenceC();
		
		ClientThread thread1 = new ClientThread(sequenceC);
		ClientThread thread2 = new ClientThread(sequenceC);
		ClientThread thread3 = new ClientThread(sequenceC);
		
		thread1.start();
		thread2.start();
		thread3.start();
	}
	/***
	 * 当一个类中使用static成员变量的时候，一定要多问问自己这个static成
	 * 员变量需要考虑“线程安全”吗？也就是说，多个线程需要独享自己的static成员变量吗？
	 * 如果需要考虑，不防请用ThreadLocal
	 */
	

}
