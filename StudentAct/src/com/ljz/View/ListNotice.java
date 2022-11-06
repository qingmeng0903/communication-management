package com.ljz.View;

import com.ljz.Dao.NoticeDao;
import com.ljz.Model.Member;
import com.ljz.Model.Notice;
import com.ljz.Util.DbUtil;
import com.ljz.Util.StringUtil;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class ListNotice extends JInternalFrame{
    private JTable noticeTable;
    private NoticeDao noticeDao = new NoticeDao();
    private DbUtil dbUtil = new DbUtil();

    private JTextField noticeTitle;
    private JTextField noticeText;
    private JTextField noticeTime;



   //启动
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ListNotice frame = new ListNotice();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //窗体
    public ListNotice(){
        setTitle("通知管理");
        setIconifiable(true);
        setClosable(true);
        setBounds(100, 100, 709, 546);
        JScrollPane scrollPane = new JScrollPane();


        noticeTable =new JTable();
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(135,206,235));
        panel_1.setBorder(new TitledBorder(null, "添加通知", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(GroupLayout.Alignment.LEADING, groupLayout.createSequentialGroup()

                                                .addComponent(noticeTable, GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE))

                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addContainerGap(36, Short.MAX_VALUE)
                                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)

                                                        .addComponent(scrollPane, GroupLayout.Alignment.TRAILING)

                                                        .addComponent(panel_1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                                                )))
                                .addGap(50))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(noticeTable, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                                .addGap(13)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 197, Short.MAX_VALUE)
                                .addContainerGap(26, Short.MAX_VALUE))
        );



        noticeTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "标题", "内容", "时间"
                }
        ){
            boolean[] columnEditables = new boolean[] {
                    false, true, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        noticeTable.getColumnModel().getColumn(1).setPreferredWidth(123);
        noticeTable.getColumnModel().getColumn(2).setPreferredWidth(176);
        scrollPane.setViewportView(noticeTable);
        scrollPane.setBackground(new Color(70,130,180));

        noticeTable.setForeground(new Color(70,130,180));
        noticeTable.getTableHeader().setBackground(new Color(173,216,230));
        noticeTable.getParent().setBackground(new Color(173,216,230));
        scrollPane.setViewportView(noticeTable);
        getContentPane().setLayout(groupLayout);



        this.fillTable(new Notice());

        //添加
        JLabel lblNewLabel_1 = new JLabel("标题：");

        noticeTitle = new JTextField();
        noticeTitle.setBackground(new Color(224,255,255));
        noticeTitle.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("内容：");

        noticeText = new JTextField();
        noticeText.setBackground(new Color(224,255,255));
        noticeText.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("时间：");

        noticeTime = new JTextField();
        noticeTime.setBackground(new Color(224,255,255));
        noticeTime.setColumns(10);

        JButton btnNewButton_1 = new JButton("添加");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                noticeUpdate(e);
            }
        });
        btnNewButton_1.setIcon(new ImageIcon(ListMember.class.getResource("/images/modify.png")));


        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
                gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING))
                                .addGroup(gl_panel_1.createSequentialGroup()
                                        .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)

                                                .addGroup(gl_panel_1.createSequentialGroup()
                                                        .addComponent(lblNewLabel_1)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(noticeTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                        .addGap(27)
                                        .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(lblNewLabel_2)
                                                )
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(noticeText, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                        )
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(lblNewLabel_3)
                                                )
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(noticeTime, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                                )
                                        .addGap(10)

                                )
                                .addContainerGap())
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addGap(260)
                                .addComponent(btnNewButton_1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)

                                .addGap(132))
        );

        gl_panel_1.setVerticalGroup(
                gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(noticeTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblNewLabel_2)
                                                .addComponent(noticeText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblNewLabel_3)
                                                .addComponent(noticeTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        )
                                        .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)

                                                .addComponent(lblNewLabel_1)))
                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                .addGap(2)



                                                )
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)

                                .addGap(18)
                                .addGroup(gl_panel_1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnNewButton_1)
                                )
                                .addContainerGap())
        )));
        panel_1.setLayout(gl_panel_1);
        getContentPane().setBackground(new Color(135,206,250));

        //加载图片
        ImageIcon icon=new ImageIcon(Main.class.getResource("/images/dala.png"));
        //Image im=new Image(icon);
        //将图片放入label中
        JLabel label=new JLabel(icon);

        //设置label的大小
        label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        getContentPane().add(label,new Integer(Integer.MIN_VALUE));
    }
//添加
    public void noticeUpdate(ActionEvent evt) {
        String noticeTitle = this.noticeTitle.getText();
        String noticeText = this.noticeText.getText();
        String noticeTime = this.noticeTime.getText();

        if (StringUtil.isEmpty(noticeTitle) || StringUtil.isEmpty(noticeText)) {
            JOptionPane.showMessageDialog(null, "填写信息不能有空");
            return;
        } else {
            Connection con = null;
            try {
                con = dbUtil.getCon();
                Notice notice = new Notice(noticeTitle, noticeText, noticeTime);
                int n = noticeDao.add(con, notice);
                if (n == 1) {
                    JOptionPane.showMessageDialog(null, "添加成功");
                    this.noticeTitle.setText("");
                    this.noticeText.setText("");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "添加失败");
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    dbUtil.close(con);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }

    }
    //初始化表格
    private void fillTable(Notice notice) {

        DefaultTableModel dtm=(DefaultTableModel) noticeTable.getModel();
        dtm.setRowCount(0);
        Connection con=null;
        try {
            con=dbUtil.getCon();
            ResultSet rs=noticeDao.list(con,notice);
            while(rs.next()) {
                Vector v=new Vector();
                v.add(rs.getString("noticeTitle"));
                v.add(rs.getString("noticeText"));
                v.add(rs.getString("noticeTime"));
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
