package org.smart4j.framework.util;

import org.smart4j.framework.helper.AopHelper;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ClassHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.helper.IocHelper;

/***
 * 用于统一加载各个helper
 * @author acer
 *
 */
public class HelperLoader {
	public static void init(){
		//AopHelper要在IocHelper之前加载，因为首先需要AopHelper获取代理对象，然后才能通过IocHelper进行依赖注入
		Class<?>[] helperList = {
				ClassHelper.class,
				BeanHelper.class,
				AopHelper.class,
				IocHelper.class,
				ControllerHelper.class
		};
		for(Class<?> clz:helperList){
			ClassUtil.loadClass(clz.getName(), true);
		}
	}
}
