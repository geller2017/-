package org.smart4j.framework.helper;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.util.ClassUtil;


/***
 * 类操作帮助类
 * @author Administrator
 *
 */

public final class ClassHelper {
	/***
	 * 定义类集合（用于存放所加载的类）
	 */
	private static final Set<Class<?>> CLASS_SET;
	static{
		String base_package = ConfigHelper.getAppBasePackage();
		CLASS_SET = ClassUtil.getClassSet(base_package);
	}
	
	//获取应用下所有的类
	public static Set<Class<?>> getClassSet(){
		return CLASS_SET;
	}
	//获取应用下所有的Service类
	public static Set<Class<?>> getServiceClassSet(){
		Set<Class<?>> serviceClassSet = new HashSet<>();
		for(Class<?> clazz:serviceClassSet){
			if(clazz.isAnnotationPresent(Service.class)){
				serviceClassSet.add(clazz);
			}
		}
		return serviceClassSet;
	}
	//获取应用下所有的Controller类
	public static Set<Class<?>> getControllerClassSet(){
		Set<Class<?>> controllerClassSet = new HashSet<>();
		for(Class<?> clazz:controllerClassSet){
			if(clazz.isAnnotationPresent(Service.class)){
				controllerClassSet.add(clazz);
			}
		}
		return controllerClassSet;
	}
	
	//获取系统中全部被注解的类
	public static Set<Class<?>> getBeanClassSet(){
		Set<Class<?>> cotrollerClasses = getControllerClassSet();
		Set<Class<?>> serviceClasses = getServiceClassSet();
		Set<Class<?>> beans = new HashSet<>();
		beans.addAll(cotrollerClasses);
		beans.addAll(serviceClasses);
		return beans;
	}
	
	
	//aop需要 AspectProxy抽象类的所有具体了，此外，还需要获取带有Aspect注解的所有类，因此需要在ClassHelper中添加两个方法
	
	/***
	 * 获取应用包下某父类（或接口）的所有子类（或实现了）
	 */
	public static Set<Class<?>> getClassSetBySuper(Class<?> supperClass){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> cls:CLASS_SET){
			if(supperClass.isAssignableFrom(cls)&&!supperClass.equals(cls)){
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	/***
	 * 获取应用包下指定注解的所有类
	 * @param annotationClass
	 * @return
	 */
	public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> cls:CLASS_SET){
			if(cls.isAnnotationPresent(annotationClass)){
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	
	
}
