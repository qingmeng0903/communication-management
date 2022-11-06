package com.ljz.Dao;

import com.ljz.Model.Activity;
import com.ljz.Util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ActDao {
	//活动添加

	public int addAct(Connection con, Activity activity) throws Exception {
		String sql="insert into activity values(?,?,?,?,?,?,0)";
		PreparedStatement pstmt=con.prepareStatement(sql);   //SQL 语句被预编译并且存储在 PreparedStatement 对象中
		pstmt.setString(1, activity.getActName());
		pstmt.setInt(5, activity.getActTypeid());
		pstmt.setString(4, activity.getActTime());
		pstmt.setString(2, activity.getLeaderName());
		pstmt.setString(3, activity.getSex());
		pstmt.setString(6, activity.getActDesc());
		return pstmt.executeUpdate();    //executeUpdate(sql)的返回值是更新的条数
	}

	//活动信息查询
	public ResultSet ListAct(Connection con, Activity activity) throws Exception {
		StringBuffer sb=new StringBuffer("select * from activity b,acttype t where b.actTypeid=t.id");
		if(StringUtil.isNotEmpty(activity.getActName())) {
			sb.append(" and b.actName like '%"+ activity.getActName()+"%'");
		}
		if(StringUtil.isNotEmpty(activity.getLeaderName())) {
			sb.append(" and b.leaderName like '%"+ activity.getLeaderName()+"%'");
		}
		if(activity.getActTypeid()!=null&& activity.getActTypeid()!=-1) {
			sb.append(" and b.actTypeid="+ activity.getActTypeid());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();   //executeQuery()方法会把数据库响应的查询结果存放在ResultSet类对象中供我们使用
	}


	//修改活动信息
	public int updateAct(Connection con, Activity activity) throws Exception {
		String sql="update activity set actName=? ,leaderName=?,sex=?,actTime=?,actTypeid=?,actDesc=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, activity.getActName());
		pstmt.setString(2, activity.getLeaderName());
		pstmt.setString(3, activity.getSex());
		pstmt.setString(4, activity.getActTime());
		pstmt.setInt(5, activity.getActTypeid());
		pstmt.setString(6, activity.getActDesc());
		pstmt.setInt(7, activity.getId());
		return pstmt.executeUpdate();  //返回修改次数
	}


	//删除活动
	public int deleteAct(Connection con, String id) throws Exception {
		String sql="delete from activity where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
	public boolean exitActByActTypeId(Connection con, String id) throws Exception {
		String sql="select * from activity where actTypeid=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,id);
		ResultSet rs=pstmt.executeQuery();       //查询结果如果为空说明社团下无活动 下一行就返回0（反之）
		return rs.next();
	}
}
