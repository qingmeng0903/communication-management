package com.ljz.View;


import com.ljz.Dao.MemberDao;

import com.ljz.Model.Member;
import com.ljz.Util.DbUtil;

import javax.swing.GroupLayout.Alignment;

import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class ListMember extends JInternalFrame{



    private JTable MemberTable;
    private DbUtil dbUtil = new DbUtil();
    private MemberDao memberDao= new MemberDao();



    private JTextField membername;
    private JTextField membersex;
    private JTextField membertel;
    private JTextField memberage;
    private JTextField membermajor;

    private JComboBox member;


    private final ButtonGroup buttonGroup = new ButtonGroup();

    //启动
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ListMember frame = new ListMember();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

//窗体部分
    public ListMember() {
            setTitle("成员管理");
            setIconifiable(true);
            setClosable(true);
            setBounds(100, 100, 800, 491);

            JScrollPane scrollPane = new JScrollPane();




            MemberTable= new JTable();
        MemberTable.addMouseListener(new MouseAdapter()  {
            @Override
            public void mousePressed(MouseEvent e) {
                MemberTableMouseAction(e);
            }
        });



        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(135,206,235));
        panel_1.setBorder(new TitledBorder(null, "修改信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));

            GroupLayout groupLayout = new GroupLayout(getContentPane());
            groupLayout.setHorizontalGroup(
                    groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(groupLayout.createSequentialGroup()
                                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addGroup(GroupLayout.Alignment.LEADING, groupLayout.createSequentialGroup()

                                                    .addComponent(MemberTable, GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE))

                                            .addGroup(groupLayout.createSequentialGroup()
                                                    .addContainerGap(36, Short.MAX_VALUE)
                                                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)

                                                            .addComponent(scrollPane, GroupLayout.Alignment.TRAILING)

                                                            .addComponent(panel_1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                                                    )))
                                    .addGap(110))
            );
            groupLayout.setVerticalGroup(
                    groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(groupLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(MemberTable, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                                    .addGap(13)
                                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18)
                                    .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 197, Short.MAX_VALUE)
                                    .addContainerGap(26, Short.MAX_VALUE))
            );

        MemberTable.setModel(new DefaultTableModel(
                    new Object[][] {
                    },
                    new String[] {
                            "姓名", "性别", "电话", "年龄","专业"
                    }
            ){
                boolean[] columnEditables = new boolean[] {
                        false, true, false, false
                };
                public boolean isCellEditable(int row, int column) {
                    return columnEditables[column];
                }
            });
        MemberTable.getColumnModel().getColumn(1).setPreferredWidth(123);
        MemberTable.getColumnModel().getColumn(2).setPreferredWidth(176);
            scrollPane.setViewportView(MemberTable);
            scrollPane.setBackground(new Color(70,130,180));

        MemberTable.setForeground(new Color(70,130,180));
        MemberTable.getTableHeader().setBackground(new Color(173,216,230));
        MemberTable.getParent().setBackground(new Color(173,216,230));
            scrollPane.setViewportView(MemberTable);
            getContentPane().setLayout(groupLayout);



            this.fillTable(new Member());

//修改
        JLabel lblNewLabel_4 = new JLabel("姓名：");

        membername = new JTextField();
        membername.setBackground(new Color(224,255,255));
        membername.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("性别：");

        membersex = new JTextField();
        membersex.setBackground(new Color(224,255,255));
        membersex.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("电话：");

        membertel = new JTextField();
        membertel.setBackground(new Color(224,255,255));
        membertel.setColumns(10);

        JLabel lblNewLabel_7 = new JLabel("年龄：");

        memberage = new JTextField();
        memberage.setBackground(new Color(224,255,255));
        memberage.setText("");
        memberage.setColumns(10);

        JLabel lblNewLabel_8 = new JLabel("所属社团：");
        membermajor = new JTextField();
        membermajor.setBackground(new Color(224,255,255));
        membermajor.setText("");
        membermajor.setColumns(10);

        JButton btnNewButton_1 = new JButton("修改");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                memberUpdate(e);
            }
        });
        btnNewButton_1.setIcon(new ImageIcon(ListMember.class.getResource("/images/modify.png")));
//
////
       GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)

								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNewLabel_6)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(membertel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(27)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_4)
								.addComponent(lblNewLabel_7))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(memberage, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
								.addComponent(membername, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_5)
								.addComponent(lblNewLabel_8))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(membersex, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                        .addComponent(membermajor, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
							.addGap(10)

						)
					.addContainerGap())
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(260)
					.addComponent(btnNewButton_1)
					.addPreferredGap(ComponentPlacement.RELATED, 152, Short.MAX_VALUE)

					.addGap(132))
		);

		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(membername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_5)
                                .addComponent(membersex, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)

							.addComponent(lblNewLabel_4)))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_6))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(membertel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_7)
								.addComponent(memberage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_8)
                                 .addComponent(membermajor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								)))
					.addPreferredGap(ComponentPlacement.RELATED)

					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						)
					.addContainerGap())
		);
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
//    修改数据
    private void memberUpdate(ActionEvent e) {


        String membername =this.membername.getText();
        String membersex =this.membersex.getText();
        int membertel = Integer.parseInt(this.membertel.getText());
        int  memberage = Integer.parseInt(this.memberage.getText());
        String membermajor =this.membermajor.getText();






        Connection con=null;
        Member member=new Member(membername,membersex,membertel,memberage,membermajor);
        try {
            con=dbUtil.getCon();
            int result= memberDao.update(con, member);
            if(result==1) {
                JOptionPane.showMessageDialog(null, "修改活动成功");
                reset();
                this.fillTable(new Member());
            }else {
                JOptionPane.showMessageDialog(null, "修改活动失败");
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace(); //在命令行打印异常信息在程序中出错的位置及原因
            JOptionPane.showMessageDialog(null, "修改活动失败");
        }finally {
            try {
                dbUtil.close(con);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
//表格点击
    private void MemberTableMouseAction(MouseEvent met) {
        // TODO Auto-generated method stub
        int row=this.MemberTable.getSelectedRow();


        this.membername.setText((String) MemberTable.getValueAt(row, 0));
        this.membersex.setText((String) MemberTable.getValueAt(row, 1));
        this.membertel.setText(String.valueOf(MemberTable.getValueAt(row, 2)));

        this.memberage.setText(String.valueOf(MemberTable.getValueAt(row, 3)));
        this.membermajor.setText((String) MemberTable.getValueAt(row, 4));
        member = new JComboBox();

        int n=this.member.getItemCount();
        for(int i=0;i<n;i++) {
            Member item=(Member) this.member.getItemAt(i);
            if(item.getMemberName().equals(membername)) {
                this.member.setSelectedIndex(i);
            }
        }



    }

        //初始化表格
        private void fillTable(Member member) {
            DefaultTableModel dtm=(DefaultTableModel) MemberTable.getModel();
            dtm.setRowCount(0);
            Connection con=null;
            try {
                con=dbUtil.getCon();
                ResultSet rs=memberDao.listMember(con,member);
                while(rs.next()) {
                    Vector v=new Vector();
                    v.add(rs.getString("membername"));
                    v.add(rs.getString("membersex"));
                    v.add(rs.getString("membertel"));
                    v.add(rs.getString("memberage"));
                    v.add(rs.getString("membermajor"));
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
    private void reset() {
        this.membername.setText("");
        this.membersex.setText("");
        this.membertel.setText("");
        this.memberage.setText("");
        this.membermajor.setText("");
        if(this.member.getItemCount()>0) {
            this.member.setSelectedIndex(0);
        }
    }

}




