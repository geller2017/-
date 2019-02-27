package org.smart4j.framework.util;

import java.text.SimpleDateFormat;
import java.util.Date;




//时间转换工具
public class DateUtil {
	public static String fomateDate(String pattern,Date date){
		return new SimpleDateFormat(pattern).format(date);
	}
}
