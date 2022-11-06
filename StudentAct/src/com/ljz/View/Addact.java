package com.ljz.View;

import com.ljz.Dao.ActDao;
import com.ljz.Dao.ActTypeDao;
import com.ljz.Model.ActType;
import com.ljz.Model.Activity;
import com.ljz.Util.DbUtil;
import com.ljz.Util.StringUtil;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

public class Addact extends JInternalFrame {
	private JTextField actName;
	private JTextField leadername;
	private JTextField actTime;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox actType;
	private JTextArea actDesc;
	private JRadioButton man;
	private JRadioButton woman;
	
	private DbUtil dbUtil=new DbUtil();
	private ActTypeDao actTypedao =new ActTypeDao();
	private ActDao actDao =new ActDao();


	//启动
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Addact frame = new Addact();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	//创建添加活动窗体
	public Addact() {
		setTitle("活动添加");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 500, 501);
		
		JLabel lblNewLabel = new JLabel("活动名称：");
		
		actName = new JTextField();
		actName.setBackground(new Color(224,255,255));
		actName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("活动负责人：");
		
		leadername = new JTextField();
		leadername.setBackground(new Color(224,255,255));
		leadername.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("负责人性别：");
		
		JLabel lblNewLabel_3 = new JLabel("活动时间：");
		
		actTime = new JTextField();
		actTime.setBackground(new Color(224,255,255));
		actTime.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("社团名称：");
		
		JLabel lblNewLabel_5 = new JLabel("活动描述：");
		
		actDesc = new JTextArea();
		actDesc.setBackground(new Color(224,255,255));
		
		JButton btnNewButton = new JButton("添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addActAction(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(Addact.class.getResource("/images/add.png")));
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Addact.class.getResource("/images/reset.png")));
		
		man = new JRadioButton("男");
		man.setBackground(new Color(135,206,235));
		man.setSelected(true);
		buttonGroup.add(man);
		
		woman = new JRadioButton("女");
		woman.setBackground(new Color(135,206,235));
		buttonGroup.add(woman);
		
		actType = new JComboBox();
		actType.setBackground(new Color(224,255,255));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(86)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addGap(88))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(34)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_5)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(actDesc, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel_4)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(actType, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel_2)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(man)
											.addGap(18)
											.addComponent(woman)))
									.addPreferredGap(ComponentPlacement.RELATED, 218, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(35)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(actName, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(leadername))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(actTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(4)))
					.addGap(31))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(73)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel)
										.addComponent(actName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(77)
									.addComponent(lblNewLabel_1)))
							.addGap(36)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_3)
								.addComponent(actTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(woman)
								.addComponent(man))
							.addGap(34)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_4)
								.addComponent(actType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(74)
							.addComponent(leadername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(actDesc, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		getContentPane().setBackground(new Color(135,206,235));
		actDesc.setBorder(new LineBorder(new Color(127,157,185),1,false));
		fillActType();
		//加载图片
		ImageIcon icon=new ImageIcon(Main.class.getResource("/images/dla.png"));
		//Image im=new Image(icon);
		//将图片放入label中
		JLabel label=new JLabel(icon);

		//设置label的大小
		label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		getContentPane().add(label,new Integer(Integer.MIN_VALUE));
	}



	//添加活动

	private void addActAction(ActionEvent evt) {
		String Actname =this.actName.getText();
		String Leadername =this.leadername.getText();
		String Acttime =this.actTime.getText();
		String Actdesc =this.actDesc.getText();
		if(StringUtil.isEmpty(Actname)) {
			JOptionPane.showMessageDialog(null, "活动名称不能为空");
			return;
		}
		if(StringUtil.isEmpty(Leadername)) {
			JOptionPane.showMessageDialog(null, "活动负责人不能为空");
			return;
		}
		if(StringUtil.isEmpty(Acttime)) {
			JOptionPane.showMessageDialog(null, "活动时间不能为空");
			return;
		}
		String sex="";
		if(man.isSelected()) {
			sex="男";
		}else {
			sex="女";
		}
		
		ActType acttype =(ActType) actType.getSelectedItem();
		int acttypeId = acttype.getId();
		
		Activity activity=new Activity(Actname, Leadername, sex, Acttime, acttypeId, Actdesc);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			int addNumber= actDao.addAct(con, activity);
			if(addNumber==1) {
				JOptionPane.showMessageDialog(null, "添加成功");
				reset();
			}else {
				JOptionPane.showMessageDialog(null, "添加失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "添加失败");
		}finally {
			try {
				dbUtil.close(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	//重置
	private void reset() {
		// TODO Auto-generated method stub
		this.actName.setText("");
		this.leadername.setText("");
		this.man.setSelected(true);
		this.actTime.setText("");
		this.actDesc.setText("");
		if(this.actType.getItemCount()>0) {
			this.actType.setSelectedIndex(0);
		}
	}

	//初始化下拉框

	private void fillActType() {
		Connection con=null;
		ActType acttype =new ActType();
		try {
			con=dbUtil.getCon();
			ResultSet rs= actTypedao.list(con, new ActType());
			while(rs.next()) {
				acttype =new ActType();
				acttype.setId(rs.getInt("id"));
				acttype.setActTypeName(rs.getString("actTypeName"));
				this.actType.addItem(acttype);
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
