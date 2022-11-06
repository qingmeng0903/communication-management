package com.ljz.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

	private JPanel contentPane;
	private JDesktopPane table;


	//进入主界面
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//创建主界面的frame
	public Main() {
		setTitle("Welcome to 社团活动管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 900);


		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("管      理");
		mnNewMenu.setIcon(new ImageIcon(Main.class.getResource("/images/base.png")));
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_2 = new JMenu("社团管理");
		mnNewMenu_2.setIcon(new ImageIcon(Main.class.getResource("/images/actTypeManager.png")));
		mnNewMenu.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("社团添加");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddType addtype=new AddType();
				addtype.setVisible(true);
				table.add(addtype);
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(Main.class.getResource("/images/add.png")));
		mnNewMenu_2.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("社团维护");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListType listtype=new ListType();
				listtype.setVisible(true);
				table.add(listtype);
			}
		});
		mntmNewMenuItem_2.setIcon(new ImageIcon(Main.class.getResource("/images/edit.png")));
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_3 = new JMenu("活动管理");
		mnNewMenu_3.setIcon(new ImageIcon(Main.class.getResource("/images/actManager.png")));
		mnNewMenu.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("活动添加");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Addact addact =new Addact();
				addact.setVisible(true);
				table.add(addact);
			}
		});
		mntmNewMenuItem_3.setIcon(new ImageIcon(Main.class.getResource("/images/add.png")));
		mnNewMenu_3.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("活动管理");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListAct listAct=new ListAct();
				listAct.setVisible(true);
				table.add(listAct);
			}
		});

		mntmNewMenuItem_4.setIcon(new ImageIcon(Main.class.getResource("/images/edit.png")));
		mnNewMenu_3.add(mntmNewMenuItem_4);


		//		新加部分  成员管理
		JMenu mnNewMenu_4 = new JMenu("成员管理");
		mnNewMenu_4.setIcon(new ImageIcon(Main.class.getResource("/images/actManager.png")));
		mnNewMenu.add(mnNewMenu_4);

		JMenuItem mntmNewMenuItem_8 = new JMenuItem("查看申请");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListApply listApply= new ListApply();
				listApply.setVisible(true);
				table.add(listApply);
			}
		});
		mntmNewMenuItem_8.setIcon(new ImageIcon(Main.class.getResource("/images/edit.png")));
		mnNewMenu_4.add(mntmNewMenuItem_8);
//查看、添加成员
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("添加成员");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListApply listApply= new ListApply();
				listApply.setVisible(true);
				table.add(listApply);
			}
		});
//		查看


		JMenuItem mntmNewMenuItem_12 = new JMenuItem("查看成员");
		mntmNewMenuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListMember listMember = new ListMember();
				listMember.setVisible(true);
				table.add(listMember);
			}
		});
		mntmNewMenuItem_12.setIcon(new ImageIcon(Main.class.getResource("/images/edit.png")));
		mnNewMenu_4.add(mntmNewMenuItem_12);


		JMenuItem mntmNewMenuItem_13 = new JMenuItem("添加成员");
		mntmNewMenuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddMem addMem = new AddMem();
				addMem.setVisible(true);
				table.add(addMem);
			}
		});
		mntmNewMenuItem_13.setIcon(new ImageIcon(Main.class.getResource("/images/edit.png")));
		mnNewMenu_4.add(mntmNewMenuItem_13);


//通知  添加通知   查看通知

		JMenu mnNewMenu_5 = new JMenu("通知管理");
		mnNewMenu_5.setIcon(new ImageIcon(Main.class.getResource("/images/actManager.png")));
		mnNewMenu.add(mnNewMenu_5);

		JMenuItem mntmNewMenuItem_10 = new JMenuItem("查看通知");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListNotice listNotice = new ListNotice();
				listNotice.setVisible(true);
				table.add(listNotice);
			}
		});
		mntmNewMenuItem_10.setIcon(new ImageIcon(Main.class.getResource("/images/edit.png")));
		mnNewMenu_5.add(mntmNewMenuItem_10);
////		添加通知
//		JMenuItem mntmNewMenuItem_11 = new JMenuItem("发布通知");
//		mntmNewMenuItem_11.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				ListApply listApply= new ListApply();
//				listApply.setVisible(true);
//				table.add(listApply);
//			}
//		});
//		mntmNewMenuItem_11.setIcon(new ImageIcon(Main.class.getResource("/images/edit.png")));
//		mnNewMenu_5.add(mntmNewMenuItem_11);




		
		JMenuItem mntmNewMenuItem = new JMenuItem("退         出");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result=JOptionPane.showConfirmDialog(null, "是否确定退出");
				if(result==0) {
					dispose();
				}
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(Main.class.getResource("/images/exit.png")));
		mnNewMenu.add(mntmNewMenuItem);




		JMenu mnNewMenu_1 = new JMenu("A b o u t");
		mnNewMenu_1.setIcon(new ImageIcon(Main.class.getResource("/images/about.png")));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("About me");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ljzwork ljzwork =new ljzwork();
				ljzwork.setVisible(true);
				table.add(ljzwork);
			}
		});
		mntmNewMenuItem_5.setIcon(new ImageIcon(Main.class.getResource("/images/about.png")));
		mnNewMenu_1.add(mntmNewMenuItem_5);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		table = new JDesktopPane();
		table.setBackground(new Color(187,255,255));
		contentPane.add(table, BorderLayout.CENTER);

		//加载图片
		ImageIcon icon=new ImageIcon(Main.class.getResource("/images/ameng1.png"));
		//Image im=new Image(icon);
		//将图片放入label中
		JLabel label=new JLabel(icon);

		//设置label的大小
		label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		table.add(label,new Integer(Integer.MIN_VALUE));
		table.setOpaque(false);






		//设置窗体居中
		this.setLocationRelativeTo(null);
		this.setResizable(false);










	}


}
