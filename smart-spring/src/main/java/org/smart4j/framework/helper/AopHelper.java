package org.smart4j.framework.helper;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.annotation.Transaction;
import org.smart4j.framework.proxy.AspectProxy;
import org.smart4j.framework.proxy.Proxy;
import org.smart4j.framework.proxy.ProxyManager;
import org.smart4j.framework.proxy.TransactionProxy;


public class AopHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);
	/***
	 * 通过一个静态块来初始化整个aop框架
	 */
	static{
		try {
			Map<Class<?>,Set<Class<?>>> proxyMap = createProxyMap();
			Map<Class<?>,List<Proxy>> targetMap = createTargetMap(proxyMap);
			for(Map.Entry<Class<?>, List<Proxy>> targetEntry:targetMap.entrySet()){
				Class<?> targetClass = targetEntry.getKey();
				List<Proxy> proxyList = targetEntry.getValue();
				Object proxy = ProxyManager.createProxy(targetClass, proxyList);
				BeanHelper.setBran(targetClass, proxy);
			}
		} catch (Exception e) {
			LOGGER.error("aop init failue",e);
		}
	}

	/***
	 * 带有Aspect注解的所有类
	 * 获取Aspect注解中设置的注解类，若该注解类不是Aspect类，则可调用
	 * ClassHelper#getClassSetByAnnotation方法获取相关类，并把这些类放入目标类集合中，
	 * 最终返回这个集合
	 */
	private static Set<Class<?>> createTargetClassSet(Aspect aspect){
		Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
		Class<? extends Annotation> annotation = aspect.value();
		if(annotation!=null && !annotation.equals(Aspect.class)){
			targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
		}
		return targetClassSet;
	}
	
	/***
	 * 获取代理类及其目标类集合之间的映射关系，一个代理类可对应一个或者多个目标类
	 * 需要强调的是，这里所说的代理类指的是切面类
	 */
	private static Map<Class<?>,Set<Class<?>>> createProxyMap(){
		Map<Class<?>,Set<Class<?>>> proxyMap = new HashMap<Class<?>,Set<Class<?>>>();
		addAspectProxy(proxyMap);
		addTransActionProxy(proxyMap);
		return proxyMap;
	}

	private static void addAspectProxy(Map<Class<?>, Set<Class<?>>> proxyMap) {
		Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
		for(Class<?> proxyClass:proxyClassSet){
			if(proxyClass.isAnnotationPresent(Aspect.class)){
				Aspect aspect = proxyClass.getAnnotation(Aspect.class);
				Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
				proxyMap.put(proxyClass, targetClassSet);
			}
		}
	}
	
	/***
	 * 添加事务管理切面
	 * 对service注解的类，进行事务管理
	 * 事务管理的类为TransactionProxy
	 * @param proxyMap
	 */
	private static void addTransActionProxy(Map<Class<?>, Set<Class<?>>> proxyMap) {
		Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(Service.class);
		proxyMap.put(TransactionProxy.class, proxyClassSet);
	}
	
	
	
	
	/***
	 * 代理类需要扩展AspectProxy抽象类，还需要带有Aspect注解，只有满足这两个条件，才能根据
	 * Aspect注解中所定义的注解属性去获取该注解对用的目标类集合，然后才能建立代理类与目标类集合之间的映射关系，最终返回这个映射关系。
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private static Map<Class<?>,List<Proxy>> createTargetMap(Map<Class<?>,Set<Class<?>>> proxyMap) throws InstantiationException, IllegalAccessException{
		Map<Class<?>,List<Proxy>> targetMap = new HashMap<Class<?>,List<Proxy>>();
		for(Map.Entry<Class<?>, Set<Class<?>>> proxyEntry:proxyMap.entrySet()){
			Class<?> proxyClass = proxyEntry.getKey();
			Set<Class<?>> targetClassSet = proxyEntry.getValue();
			for(Class<?> targetClass : targetClassSet){
				Proxy proxy = (Proxy) proxyClass.newInstance();
				if(targetMap.containsKey(targetClass)){
					targetMap.get(targetClass).add(proxy);
				}else{
					List<Proxy> proxyList = new ArrayList<Proxy>();
					proxyList.add(proxy);
					targetMap.put(targetClass, proxyList);
				}
			}
		}
		return targetMap;
	}
}
