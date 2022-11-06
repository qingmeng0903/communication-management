package com.ljz.Dao;

import com.ljz.Model.ActType;
import com.ljz.Util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ActTypeDao {


	//添加
	public int add(Connection con, ActType actType) throws Exception {
		String sql = "insert into actType values(0,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, actType.getActTypeName());
		pstmt.setString(2, actType.getActTypeDesc());
		return pstmt.executeUpdate();
	}


	//查询社团
	public ResultSet list(Connection con, ActType actType) throws Exception {
		StringBuffer sb=new StringBuffer("select * from acttype");
		if(StringUtil.isNotEmpty(actType.getActTypeName())) {
			 sb.append(" and actTypeName like '%"+ actType.getActTypeName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and","where"));
		return pstmt.executeQuery();
	}


	//删除社团
	public int delete(Connection con,String id) throws Exception {
		String sql="delete from acttype where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);		
		return pstmt.executeUpdate();
	}



	//更新社团信息
	public int update(Connection con, ActType actType) throws Exception {
		String sql="update actType set actTypeName=?,actTypeDesc=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, actType.getActTypeName());
		pstmt.setString(2, actType.getActTypeDesc());
		pstmt.setInt(3, actType.getId());
		return pstmt.executeUpdate();
	}
}
