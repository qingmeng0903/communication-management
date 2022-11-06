package com.ljz.View;

import com.ljz.Dao.ActTypeDao;
import com.ljz.Model.ActType;
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

public class AddType extends JInternalFrame {
	private JTextField actTypename;
	private JTextArea actTypedesc;
	private DbUtil dbUtil=new DbUtil(); 
	private ActTypeDao actTypedao =new ActTypeDao();



	//启动窗体
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddType frame = new AddType();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	//创建社团添加窗体
	public AddType() {
		setIconifiable(true);
		setClosable(true);
		setTitle("社团添加");
		setBounds(100, 100, 500, 400);
		
		JLabel lblNewLabel = new JLabel("社团名称:");
		
		actTypename = new JTextField();
		actTypename.setBackground(new Color(224,255,255));
		actTypename.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("社团描述：");
		
		JButton btnNewButton = new JButton("添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addactType(e);
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
		
		actTypedesc = new JTextArea();
		actTypedesc.setBackground(new Color(224,255,255));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(64)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(actTypedesc, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addGap(33)
									.addComponent(actTypename, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(107)
							.addComponent(btnNewButton)
							.addGap(114)
							.addComponent(btnNewButton_1)))
					.addContainerGap(78, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(85)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(33)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(actTypedesc, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
							.addGap(41)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton_1)
								.addComponent(btnNewButton)))
						.addComponent(actTypename, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(187))
		);
		getContentPane().setLayout(groupLayout);
		getContentPane().setBackground(new Color(135,206,235));
		actTypedesc.setBorder(new LineBorder(new Color(127,157,185),1,false));

		//加载图片
		ImageIcon icon=new ImageIcon(Main.class.getResource("/images/dla1.png"));
		//Image im=new Image(icon);
		//将图片放入label中
		JLabel label=new JLabel(icon);

		//设置label的大小
		label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		getContentPane().add(label,new Integer(Integer.MIN_VALUE));
	}

	private void addactType(ActionEvent evt) {
		String acttypename =this.actTypename.getText();
		String acttypedesc =this.actTypedesc.getText();
		if(StringUtil.isEmpty(acttypename)||StringUtil.isEmpty(acttypedesc)) {
			JOptionPane.showMessageDialog(null, "填写信息不能有空");
			return;
		}else {
			Connection con=null;
			try {
				con=dbUtil.getCon();
				ActType actType =new ActType(acttypedesc,acttypename);
				int n= actTypedao.add(con, actType);
				if(n==1) {
					JOptionPane.showMessageDialog(null, "添加成功");
					this.actTypename.setText("");
					this.actTypedesc.setText("");
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
		this.actTypename.setText("");
		this.actTypedesc.setText("");
	}
}
