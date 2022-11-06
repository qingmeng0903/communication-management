package com.ljz.View;


import com.ljz.Dao.ActDao;
import com.ljz.Dao.ApplyDao;




import com.ljz.Model.Apply;
import com.ljz.Util.DbUtil;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;


public class ListApply<applyTable> extends JInternalFrame {
    private JTable applyTable;


    private DbUtil dbUtil = new DbUtil();
    private ApplyDao applyDao = new ApplyDao();



    private JTextField applyName;
    private JTextArea applyDesc;
    private JTextField actTypeName;
    private JTextField applyTime;



    private final ButtonGroup buttonGroup = new ButtonGroup();



    //启动
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ListApply frame = new ListApply();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }


    //窗体部分
    public ListApply() {
        setTitle("申请管理");
        setIconifiable(true);
        setClosable(true);
        setBounds(100, 100, 495, 491);

        JScrollPane scrollPane = new JScrollPane();



        JButton jButton = new JButton("123456789");
        jButton.setEnabled(true);
        add(jButton);
        applyTable = new JTable();


        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(GroupLayout.Alignment.LEADING, groupLayout.createSequentialGroup()
                                                .addGap(33)
                                                .addComponent(applyTable, GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addContainerGap(36, Short.MAX_VALUE)
                                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(scrollPane, GroupLayout.Alignment.TRAILING)
                                                        )))
                                .addGap(39))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(applyTable, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                                .addGap(13)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)

                                .addContainerGap(26, Short.MAX_VALUE))
        );

        applyTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "申请人姓名", "申请时间", "申请社团", "申请原因"
                }
        ){
            boolean[] columnEditables = new boolean[] {
                    false, true, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        applyTable.getColumnModel().getColumn(1).setPreferredWidth(123);
        applyTable.getColumnModel().getColumn(2).setPreferredWidth(176);
        scrollPane.setViewportView(applyTable);
        scrollPane.setBackground(new Color(70,130,180));

        applyTable.setForeground(new Color(70,130,180));
        applyTable.getTableHeader().setBackground(new Color(173,216,230));
        applyTable.getParent().setBackground(new Color(173,216,230));
        scrollPane.setViewportView(applyTable);
        getContentPane().setLayout(groupLayout);



        this.fillTable(new Apply());


        //加载图片
        ImageIcon icon=new ImageIcon(Main.class.getResource("/images/dala.png"));
        //Image im=new Image(icon);
        //将图片放入label中
        JLabel label=new JLabel(icon);

        //设置label的大小
        label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        getContentPane().add(label,new Integer(Integer.MIN_VALUE));
    }

    //初始化表格
    private void fillTable(Apply apply) {
        DefaultTableModel dtm=(DefaultTableModel) applyTable.getModel();
        dtm.setRowCount(0);
        Connection con=null;
        try {
            con=dbUtil.getCon();
            ResultSet rs=applyDao.listApp(con,apply);
            while(rs.next()) {
                Vector v=new Vector();
                v.add(rs.getString("applyname"));
                v.add(rs.getString("applytime"));
                v.add(rs.getString("acttypename"));
                v.add(rs.getString("applydesc"));
                dtm.addRow(v);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                dbUtil.close(con);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}


























