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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

public class RegisterUser extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField verification;
	private JPasswordField password1;
	private JPasswordField password2;
	private JTextArea Verification1;
	
	private DbUtil dbUtil=new DbUtil();
	private UserDao userDao=new UserDao();

	//启动
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterUser frame = new RegisterUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//注册界面
	public RegisterUser() {
		setTitle("那就先注册吧！");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 509, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(Color.YELLOW);
		
		JLabel lblNewLabel = new JLabel("开始注册");
		lblNewLabel.setFont(new Font("仿宋", Font.BOLD, 27));
		lblNewLabel.setIcon(new ImageIcon(RegisterUser.class.getResource("/images/logo.png")));
		
		JLabel lblNewLabel_1 = new JLabel("用  户  名：");
		
		name = new JTextField();
		name.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("密        码：");
		
		JLabel lblNewLabel_3 = new JLabel("验  证  码：");
		
		verification = new JTextField();
		verification.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ComperPassword(e);
			}
		});
		verification.setText("");
		verification.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("");
		
		JLabel lblNewLabel_5 = new JLabel("确认密码：");
		
		JButton btnNewButton = new JButton("注册");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterUserAction(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(RegisterUser.class.getResource("/images/me.png")));
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(RegisterUser.class.getResource("/images/reset.png")));
		
		Verification1 = new JTextArea();


		//验证码框得到焦点时判断两次密码是否相同
		Verification1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Verification1.setText(StringUtil.RegsiterString());
			}
		});
		Verification1.setFont(new Font("Courier New", Font.PLAIN, 24));
		Verification1.setBackground(Color.BLUE);
		Verification1.setForeground(Color.orange);
		Verification1.setText(StringUtil.RegsiterString());
		Verification1.setEnabled(false);
		
		password1 = new JPasswordField();
		
		password2 = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(0)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(115)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
							.addComponent(btnNewButton_1)
							.addGap(86))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(105)
											.addComponent(lblNewLabel_3))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(101)
											.addComponent(lblNewLabel_4)))
									.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
									.addComponent(Verification1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
									.addGap(36))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(104)
									.addComponent(lblNewLabel_5)))
							.addGap(140))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(104)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1)
										.addComponent(lblNewLabel_2))
									.addGap(44)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(name, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
										.addComponent(password2, Alignment.LEADING)
										.addComponent(verification, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
										.addComponent(password1)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(140)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 129, Short.MAX_VALUE)))
					.addGap(0))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addGap(12))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(13)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(password1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_5)
								.addComponent(password2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(52)
									.addComponent(lblNewLabel_4))
								.addComponent(lblNewLabel_3))
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton)
								.addComponent(btnNewButton_1))
							.addGap(14))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(verification, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(Verification1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addGap(55))))
		);
		contentPane.setLayout(gl_contentPane);

		//加载图片
		ImageIcon icon=new ImageIcon(Main.class.getResource("/images/dla.png"));
		//Image im=new Image(icon);
		//将图片放入label中
		JLabel label=new JLabel(icon);

		//设置label的大小
		label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		getContentPane().add(label,new Integer(Integer.MIN_VALUE));
		
		this.setLocationRelativeTo(null);
	}
	private void RegisterUserAction(ActionEvent evt) {
		// TODO Auto-generated method stub
		String user=this.name.getText();
		String passworld=new String(this.password1.getPassword());
		if(StringUtil.isEmpty(user)||StringUtil.isEmpty(passworld)) {
			JOptionPane.showMessageDialog(null, "Sorry,填写内容不能为空");
			return;
			}
		String yzm=this.verification.getText();
		String yzm1=this.Verification1.getText();
		if(yzm != yzm1 && ! yzm.equals(yzm1.trim())) {
			JOptionPane.showMessageDialog(null, "Sorry,验证码错误");
			return;
		}
		Connection con=null;
		try {
			con=dbUtil.getCon();
			boolean flag=userDao.isExist(con, user);
			if(flag) {
				JOptionPane.showMessageDialog(null, "Sorry,用户名已存在");
				this.name.setText("");
				this.verification.setText("");
				this.Verification1.setText(StringUtil.RegsiterString());
				return; 
			}
			User user1=new User(user,passworld);
			int n=userDao.Register(con, user1);
			if(n==1) {
				JOptionPane.showMessageDialog(null, "Congratulations！注册成功");
				reset();
				dispose();
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

	//判断密码是否一致
	private void ComperPassword(MouseEvent evt) {
		String passworld1=new String(this.password1.getPassword());
		String passworld2=new String(this.password2.getPassword());
		if (passworld1 != passworld2 && ! passworld1.equals(passworld2.trim()))
		{
		  JOptionPane.showMessageDialog(null, "两次密码不一致，请再次确认后输入");
		  this.password2.setText(""); 
		  }		 
	}


	//重置
	private void reset() {
		// TODO Auto-generated method stub
		this.name.setText("");
		this.password1.setText("");
		this.password2.setText("");
		this.verification.setText("");
		this.Verification1.setText(StringUtil.RegsiterString());
	}
}
