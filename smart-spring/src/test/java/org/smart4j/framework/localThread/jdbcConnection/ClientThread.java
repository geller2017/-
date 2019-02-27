package org.smart4j.framework.localThread.jdbcConnection;

public class ClientThread extends Thread{
	private ProductService productService;
	Long price;
	
	public ClientThread(ProductService productService,Long price){
		this.productService = productService;
		this.price = price;
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		productService.updateProductProce(1, price);
	}
	
	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			ProductService productService = new ProductServiceImpl();
			ClientThread thread = new ClientThread(productService, i*100L);
			thread.start();
			/***
			 * 多個線程使用Connection的时候回出现错误
			 * connection在DBUtil中是static的，就是线程不安全的
			 * 所以只有放入ThreadLocal中才能做到线程安全
			 * 所以ThreadLocal的用法是：static变量+需要线程安全
			 */
		}
	}
}
