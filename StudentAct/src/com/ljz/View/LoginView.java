package com.ljz.View;

import com.ljz.Dao.UserDao;
import com.ljz.Model.User;
import com.ljz.Util.DbUtil;
import com.ljz.Util.StringUtil;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	private JPasswordField userPassword;
	
	private DbUtil dbUtil=new DbUtil();
	private UserDao userDao=new UserDao();

	//启动整个程序

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//创建登录界面的frame
	public  LoginView() {
		setFont(new Font("仿宋", Font.PLAIN, 20));
		setResizable(false);
		setTitle("那就先登录吧！");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(Color.ORANGE);



		JLabel lblNewLabel = new JLabel("社团活动管理系统");
		lblNewLabel.setIcon(new ImageIcon(LoginView.class.getResource("/images/logo.png")));
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 12));
		
		JLabel lblNewLabel_1 = new JLabel("用户名：");
		lblNewLabel_1.setIcon(new ImageIcon(LoginView.class.getResource("/images/userName.png")));
		
		JLabel lblNewLabel_2 = new JLabel("密   码：");
		lblNewLabel_2.setIcon(new ImageIcon(LoginView.class.getResource("/images/password.png")));
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginAction(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(LoginView.class.getResource("/images/login.png")));
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultAction(e);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(LoginView.class.getResource("/images/reset.png")));
		
		userName = new JTextField();
		userName.setColumns(10);
		
		userPassword = new JPasswordField();

		
		JButton btnNewButton_2 = new JButton("注册");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterUser registerUser=new RegisterUser();
				registerUser.setVisible(true);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(LoginView.class.getResource("/images/me.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);



		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(67)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnNewButton)
									.addGap(42)
									.addComponent(btnNewButton_1)
									.addGap(35)
									.addComponent(btnNewButton_2))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1)
										.addComponent(lblNewLabel_2))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(userPassword)
										.addComponent(userName, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(126)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(64, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(userName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(userPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton))
					.addGap(45))
		);
		contentPane.setLayout(gl_contentPane);

		//加载图片
		ImageIcon icon=new ImageIcon(Main.class.getResource("/images/dla1.png"));
		//Image im=new Image(icon);
		//将图片放入label中
		JLabel label=new JLabel(icon);

		//设置label的大小
		label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		getContentPane().add(label,new Integer(Integer.MIN_VALUE));
		
		//设置窗体居中
		this.setLocationRelativeTo(null);

	}

	private void loginAction(ActionEvent evt) {
		// TODO Auto-generated method stub
		String username=this.userName.getText();
		String password=new String(this.userPassword.getPassword());
		if(StringUtil.isEmpty(username)) {

			JOptionPane.showMessageDialog(null, "Sorry,请输入用户名");

			return;
		}
		if(StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "Sorry,请输入密码");
			return;
		}
		User user=new User(username,password);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			User currentuser=userDao.login(con, user);
			if(currentuser!=null) {
				dispose();
				new Main().setVisible(true);;
			}else {
				JOptionPane.showMessageDialog(null, "用户名或密码错误");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void resultAction(ActionEvent evt) {      //重置事件
		// TODO Auto-generated method stub
		this.userName.setText("");
		this.userPassword.setText("");
	}
}
