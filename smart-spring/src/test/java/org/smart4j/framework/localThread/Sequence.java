package org.smart4j.framework.localThread;
/***
 * 
 * @author Administrator
 * 先定义一个接口，一个序列号生成器的程序可能同时会有多个线程并发访问他，要暴增每个线程得到的序列哈都是自增的而且不能相互干扰
 */
public interface Sequence {
	int getNumber();
}
