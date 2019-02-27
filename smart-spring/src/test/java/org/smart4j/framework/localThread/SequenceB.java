package org.smart4j.framework.localThread;

public class SequenceB implements Sequence{
	private static ThreadLocal<Integer> numberContainer = new ThreadLocal<Integer>(){
		protected Integer initialValue() {
			return 0;
		};
	};
	
	@Override
	public int getNumber() {
		numberContainer.set(numberContainer.get()+1);
		return numberContainer.get();
	}
	
	
	public static void main(String[] args) {
		SequenceB sequenceB = new SequenceB();
		
		
		ClientThread thread1 = new ClientThread(sequenceB);
		ClientThread thread2 = new ClientThread(sequenceB);
		ClientThread thread3 = new ClientThread(sequenceB);
		thread1.start();
		thread2.start();
		thread3.start();
		
		/***
		 * 通过ThreadLocal 封装了一个Integer类型的numberCOntainer静态变量，而且初始值是0,。
		 * 再看getNumber方法，首先虫numberContainer中get出当前的值，加一，随后set到numberContainer
		 * 中，最后在numberContainer中get出当前的值并返回
		 */
		
	}
	
}
