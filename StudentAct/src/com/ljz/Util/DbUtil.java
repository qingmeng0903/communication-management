package com.ljz.Util;

import java.sql.Connection;
import java.sql.DriverManager;


public class DbUtil {
	private String driver="com.mysql.cj.jdbc.Driver";
	private String url="jdbc:mysql://localhost:3306/actDBMS";
	private String name="root";
	private String password="0903";
	public Connection getCon() throws Exception {
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url, name, password);
		return con;
	}
	public void close(Connection con) throws Exception {
		if(con!=null) {
			con.close();
		}
	}
//用于测试数据库是否连接成功
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
