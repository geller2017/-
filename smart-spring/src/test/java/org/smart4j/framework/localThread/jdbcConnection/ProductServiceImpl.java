package org.smart4j.framework.localThread.jdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.smart4j.framework.util.DateUtil;

import com.mysql.jdbc.jdbc2.optional.PreparedStatementWrapper;

public class ProductServiceImpl implements ProductService{
	
	
	private static final String UPDATE_PRODUCT_SQL = "update product set price=? where id=?";
	private static final String INSERT_LOG_SQL = "INSERT INTO log (created,description) VALUES (?,?)";
	@Override
	public void updateProductProce(long productId, double price) {
		//获取连接
		Connection conn = DBUtils.getConnection();
		try {
			conn.setAutoCommit(false);//关闭自动提交事务（开启事务管理）
			
			//执行操作
			updateProduct(conn,UPDATE_PRODUCT_SQL,productId,price);
			
			//插入日志
			insetLog(conn,INSERT_LOG_SQL,"Create_product.");
			
			//提交事务
			conn.commit();
		} catch (Exception e) {
			/*try {
				//conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}*/
			e.printStackTrace();
		}finally{
			//关闭连接
			DBUtils.closeConnection();
		}
	}
	private void insetLog(Connection conn, String insertLogSql, String des) throws SQLException {
			PreparedStatement psmt = conn.prepareStatement(insertLogSql);
			psmt.setString(1, DateUtil.fomateDate("yyyy-MM-dd HH:mm:ss", new Date()));//更新时间
			psmt.setString(2, des);
			int rows = psmt.executeUpdate();
			if(rows != 0){
				System.out.println("Insert log success");
			}
	}
	private void updateProduct(Connection conn, String updateProductSql,
			long productId, double price) throws SQLException {
			PreparedStatement psmt = conn.prepareStatement(updateProductSql);
			psmt.setDouble(1, price);
			psmt.setLong(2, productId);
			int rows = psmt.executeUpdate();
			if(rows != 0){
				System.out.println("Update price success");
			}
	}
	
	
	public static void main(String[] args) {
		ProductService productService = new ProductServiceImpl();
		productService.updateProductProce(1, 700);
	}
	
	

}
