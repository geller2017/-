package org.smart4j.framework.proxy;


/***
 * 
 * @author Administrator
 * 搭建代理框架
 */
public interface Proxy {
	//执行链式代理
	Object doProxy(ProxyChain proxy);
}
