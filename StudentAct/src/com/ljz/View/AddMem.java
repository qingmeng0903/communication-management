package com.ljz.View;

import com.ljz.Dao.MemberDao;
import com.ljz.Model.ActType;
import com.ljz.Model.Member;
import com.ljz.Util.DbUtil;
import com.ljz.Util.StringUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class AddMem extends JInternalFrame{
    private JTextField Mbname;  //填写的数据
    private JTextField Mbsex;
    private JTextField Mbtel;
    private JTextField Mbage;
    private JTextField Mbmajor;

    private JTextField membername;
    private JTextField membersex;
    private JTextField memberage;
    private JTextField membertel;
    private JTextField membermajor;

    private DbUtil dbUtil=new DbUtil();
    private MemberDao memberDao= new MemberDao();



    //启动窗体
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddMem frame = new AddMem ();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }



    //创建社团添加窗体
    public AddMem() {
        setIconifiable(true);
        setClosable(true);
        setTitle("添加成员");
        setBounds(100, 100, 500, 400);

        JLabel lblNewLabel = new JLabel("成员名称:");

        membername = new JTextField();
        membername.setBackground(new Color(224,255,255));
        membername.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("性别");
        membersex = new JTextField();
        membersex.setBackground(new Color(224,255,255));
        membersex.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("电话");
        membertel = new JTextField();
        membertel.setBackground(new Color(224,255,255));
        membertel.setColumns(10);


        JLabel lblNewLabel_3 = new JLabel("年龄");
        memberage = new JTextField();
        memberage.setBackground(new Color(224,255,255));
        memberage.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("所属社团");
        membermajor = new JTextField();
        membermajor.setBackground(new Color(224,255,255));
        membermajor.setColumns(10);
        JButton btnNewButton = new JButton("添加");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addmem(e);
            }
        });
        btnNewButton.setIcon(new ImageIcon(AddType.class.getResource("/images/add.png")));


        JButton btnNewButton_1 = new JButton("重置");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetAction(e);
            }
        });
        btnNewButton_1.setIcon(new ImageIcon(AddType.class.getResource("/images/reset.png")));

        membersex = new JTextField();
        membersex.setBackground(new Color(224,255,255));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(64)
                                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(lblNewLabel_1)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(membersex, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(lblNewLabel_2)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(membertel, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(lblNewLabel_3)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(memberage, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(lblNewLabel_4)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(membermajor, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(lblNewLabel)
                                                                .addGap(33)
                                                                .addComponent(membername, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(107)
                                                .addComponent(btnNewButton)
                                                .addGap(114)
                                                .addComponent(btnNewButton_1)))
                                .addContainerGap(78, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(85)
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(lblNewLabel)
                                                .addGap(22)
                                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblNewLabel_1)
                                                        .addComponent(membersex, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(22)
                                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblNewLabel_2)
                                                        .addComponent(membertel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(22)
                                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblNewLabel_3)
                                                        .addComponent(memberage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(22)
                                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblNewLabel_4)
                                                        .addComponent(membermajor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(22)
                                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btnNewButton_1)
                                                        .addComponent(btnNewButton)))
                                        .addComponent(membername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(187))
        );
        getContentPane().setLayout(groupLayout);
        getContentPane().setBackground(new Color(135,206,235));
        membersex.setBorder(new LineBorder(new Color(127,157,185),1,false));

        //加载图片
        ImageIcon icon=new ImageIcon(Main.class.getResource("/images/dla1.png"));
        //Image im=new Image(icon);
        //将图片放入label中
        JLabel label=new JLabel(icon);

        //设置label的大小
        label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        getContentPane().add(label,new Integer(Integer.MIN_VALUE));
    }

    private void addmem(ActionEvent evt) {
        String membername =this.membername.getText();
        String membersex =this.membersex.getText();
        int memberage = Integer.parseInt(this.memberage.getText());
        int membertel = Integer.parseInt(this.membertel.getText());
        String membermajor =this.membermajor.getText();
        if(StringUtil.isEmpty(membername)||StringUtil.isEmpty(membersex)) {
            JOptionPane.showMessageDialog(null, "填写信息不能有空");
            return;
        }else {
            Connection con=null;
            try {
                con=dbUtil.getCon();
                Member member=new Member(membername,membersex,membertel,memberage,membermajor);
                int n= memberDao.add(con,member );
                if(n==1) {
                    JOptionPane.showMessageDialog(null, "添加成功");
                    this.membername.setText("");
                    this.membersex.setText("");
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(null, "添加失败");
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

    private void resetAction(ActionEvent evt) {
        // TODO Auto-generated method stub
        this.membername.setText("");
        this.membersex.setText("");
    }
}
