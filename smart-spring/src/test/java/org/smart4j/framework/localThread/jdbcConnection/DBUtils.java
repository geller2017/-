package org.smart4j.framework.localThread.jdbcConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/***
 * 
 * @author Administrator 通过LocalThread存放JDBCconnection，以达到事务控制的能力
 *         当修改产品价格的时候，需要记录操作日志，什么时候做了什么事情
 */
public class DBUtils {
	// 数据库配置
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/mysql";
	private static final String username = "root";
	private static final String password = "111111";

	// 定义一个数据库连接
	private static ThreadLocal<Connection> connectionContainer = new ThreadLocal<>();

	// 获取连接
	public static Connection getConnection() {
		try {
			Class.forName(driver);
			connectionContainer.set(DriverManager.getConnection(url, username, password));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connectionContainer.get();
	}

	// 关闭连接
	public static void closeConnection() {
		Connection conn = connectionContainer.get();
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
