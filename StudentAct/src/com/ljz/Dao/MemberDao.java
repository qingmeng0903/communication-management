package com.ljz.Dao;

import com.ljz.Model.Member;
import com.ljz.Util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDao {
    //添加成员
    public int add(Connection con, Member member) throws Exception {
        String sql = "insert into member values(?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, member.getMemberName());
        pstmt.setString(2, member.getMemberSex());
        pstmt.setInt(3, member.getMemberTel());
        pstmt.setInt(4, member.getMemberAge());
        pstmt.setString(5, member.getMemberMajor());

        return pstmt.executeUpdate();
    }


    //查询成员
    public ResultSet listMember(Connection con, Member member) throws Exception {
        StringBuffer sb=new StringBuffer("select * from member");
        if(StringUtil.isNotEmpty(member.getMemberName())) {
            sb.append(" and MemberName like '%"+ member.getMemberName()+"%'");
        }
        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and","where"));
        return pstmt.executeQuery();
    }


    //删除成员
    public int delete(Connection con,String MemberName) throws Exception {
        String sql="delete from member where membername=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,MemberName);
        return pstmt.executeUpdate();
    }



    //更新成员信息
    public int update(Connection con, Member member) throws Exception {
        String sql="update member set membersex=?,membertel=?,memberage=?,membermajor=? where membername=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(5, member.getMemberName());
        pstmt.setString(1, member.getMemberSex());
        pstmt.setInt(2, member.getMemberTel());
        pstmt.setInt(3, member.getMemberAge());
        pstmt.setString(4, member.getMemberMajor());
        return pstmt.executeUpdate();
    }
}
