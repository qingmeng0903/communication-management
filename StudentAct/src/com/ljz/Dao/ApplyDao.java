package com.ljz.Dao;

import com.ljz.Model.ActType;
import com.ljz.Model.Activity;
import com.ljz.Util.StringUtil;
import com.ljz.Model.Apply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ApplyDao {

    //查询社团
    public ResultSet listApp(Connection con, Apply apply) throws Exception {
        StringBuffer sb=new StringBuffer("select * from apply");
        if(StringUtil.isNotEmpty(apply.getApplyName())) {
            sb.append(" and actTypeName like '%"+ apply.getApplyName()+"%'");
        }
        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and","where"));
        return pstmt.executeQuery();
    }


}