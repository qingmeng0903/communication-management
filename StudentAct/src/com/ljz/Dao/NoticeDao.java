package com.ljz.Dao;

import com.ljz.Model.Member;
import com.ljz.Model.Notice;
import com.ljz.Util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NoticeDao {
    //    添加通知
    public int add(Connection con, Notice notice) throws Exception {
        String sql = "insert into notice values(?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, notice.getNoticeTitle());
        pstmt.setString(2, notice.getNoticeText());
        pstmt.setString(3, notice.getNoticeTime());
        return pstmt.executeUpdate();
    }

    //查询
    public ResultSet list(Connection con, Notice notice) throws Exception {
        StringBuffer sb=new StringBuffer("select * from notice");
        if(StringUtil.isNotEmpty(notice.getNoticeTitle())) {
            sb.append(" and noticeTitle like '%"+ notice.getNoticeTitle()+"%'");
        }
        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and","where"));
        return pstmt.executeQuery();
    }

    //    删除通知
    public int delete(Connection con,String noticeTitle) throws Exception {
        String sql="delete from notice where noticeTitle=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, noticeTitle);
        return pstmt.executeUpdate();
    }

}
