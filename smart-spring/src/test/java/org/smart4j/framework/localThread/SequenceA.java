package org.smart4j.framework.localThread;

public class SequenceA implements Sequence {
	private int number = 0;
	@Override
	public int getNumber() {
		number = number+1;
		return number;
	}
	
	
	public static void main(String[] args) {
		Sequence sequence = new SequenceA();
		
		
		ClientThread thread1 = new ClientThread(sequence);
		ClientThread thread2 = new ClientThread(sequence);
		ClientThread thread3 = new ClientThread(sequence);
		thread1.start();
		thread2.start();
		thread3.start();
		/***
		 * 由于线程的启动顺序是随机的，所以并不是0,1,2这样的顺序，这个好理解，为什么当Thread-0
		 * 输出1,2,3之后，而Thread-2却输出了4,5,6呢，不应该是从0开始输出吗
		 * 仔细分析才发现，线程之间的共享static(valotile)变量无法保证对于不同线程而言是安全的，也就
		 * 是说，此时无法保证线程安全
		 * 那么如何才能做到“线程安全”呢？对应于这个案例，救赎说不同的线程可以拥有自己的static变量，如何实现呢
		 */
	}
}
