package org.smart4j.framework.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/***
 * 数据库操作助手类
 * @author Administrator
 *
 */
public class DatabaseHelper {
	/***
	 * 数据库连接存放容器(线程安全)
	 */
	public static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<>();
	
	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mysql";
		String username = "root";
		String password = "111111";
		Class.forName(driver);
		return DriverManager.getConnection(url, username, password);
	}
	/***
	 * 开启事务
	 * 默认的是自动提交事务的，所以需要把自动提交属性设置为false。
	 * 需要将connection对象放入本地线程变量中。
	 * 当事务提交或者回滚后，需要移除bending线程变量中的connection对象
	 * 
	 */
	public static void beginTransaction(){
		Connection conn = null;
		try {
			conn = getConnection();
			if(conn != null){
				conn.setAutoCommit(false);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally{
			CONNECTION_HOLDER.set(conn);
		}
	}
	/***
	 * 提交事务
	 */
	public static void connitTransaction(){
		try {
			Connection conn = getConnection();
			if(conn!=null){
				conn.commit();
				conn.close();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally{
			CONNECTION_HOLDER.remove();
		}
	}
	/***
	 * 回滚事务
	 */
	public static void rollbackTransaction(){
		try {
			Connection conn = getConnection();
			if(conn != null){
				conn.rollback();
				conn.close();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally{
			CONNECTION_HOLDER.remove();
		}
	}
	
	
	
	
	
	
}
