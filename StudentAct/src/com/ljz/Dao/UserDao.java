package com.ljz.Dao;

import com.ljz.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {

	//登录验证
	public User login(Connection con,User user) throws Exception {
		User resultuser=null;
		String sql="select * from user where username=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			resultuser=new User();
			resultuser.setId(rs.getInt("id"));
			resultuser.setUsername(rs.getString("username"));
			resultuser.setPassword(rs.getString("password"));
		}
		return resultuser;
	}

	//注册账号
	public int Register(Connection con,User user) throws Exception {
		String sql="insert into user values(0,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		return pstmt.executeUpdate();
	}


	//注册的用户名是否存在
	public boolean isExist(Connection con,String name) throws Exception {
		String sql="select id from user where userName=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs=pstmt.executeQuery();
		return rs.next();
	}
}
