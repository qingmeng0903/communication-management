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

public class ListAct extends JInternalFrame {
	private JTable actTable;
	private JTextField sname;
	private JTextField sleadername;
	private JComboBox sactType;

	
	private DbUtil dbUtil=new DbUtil();
	private ActDao actDao =new ActDao();
	private ActTypeDao actTypeDao =new ActTypeDao();
	private JTextField id;
	private JTextField actName;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField acttime;
	private JTextField leadername;
	private JRadioButton man;
	private JRadioButton women;
	private JTextArea actDesc;
	private JComboBox actType;


	//启动
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListAct frame = new ListAct();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	//创建维护活动窗口
	public ListAct() {
		setTitle("活动管理");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 709, 546);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135,206,235));
		panel.setBorder(new TitledBorder(null, "条件筛选", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(135,206,235));
		panel_1.setBorder(new TitledBorder(null, "修改信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(33)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(36, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(scrollPane, Alignment.TRAILING)
								.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE))))
					.addGap(39))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_3 = new JLabel("编号：");
		
		id = new JTextField();
		id.setBackground(new Color(224,255,255));
		id.setEditable(false);
		id.setText("");
		id.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("活动名称：");
		
		actName = new JTextField();
		actName.setBackground(new Color(224,255,255));
		actName.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("负责人性别：");
		
		man = new JRadioButton("男");
		man.setBackground(new Color(135,206,235));
		man.setSelected(true);
		buttonGroup.add(man);
		
		women = new JRadioButton("女");
		women.setBackground(new Color(135,206,235));
		buttonGroup.add(women);
		
		JLabel lblNewLabel_6 = new JLabel("活动时间：");
		
		acttime = new JTextField();
		acttime.setBackground(new Color(224,255,255));
		acttime.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("负责人：");
		
		leadername = new JTextField();
		leadername.setBackground(new Color(224,255,255));
		leadername.setText("");
		leadername.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("社团名称：");
		
		actType = new JComboBox();
		actType.setBackground(new Color(224,255,255));
		
		JLabel lblNewLabel_9 = new JLabel("活动描述：");
		
		actDesc = new JTextArea();
		actDesc.setBackground(new Color(224,255,255));
		
		JButton btnNewButton_1 = new JButton("修改");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateActAction(e);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(ListAct.class.getResource("/images/modify.png")));
		
		JButton btnNewButton_2 = new JButton("删除");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteActAction(e);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(ListAct.class.getResource("/images/delete.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNewLabel_6)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(acttime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(27)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_4)
								.addComponent(lblNewLabel_7))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(leadername)
								.addComponent(actName, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_5)
								.addComponent(lblNewLabel_8))
							.addGap(10)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(man)
									.addGap(10)
									.addComponent(women))
								.addComponent(actType, 0, 108, Short.MAX_VALUE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_9)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(actDesc, GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)))
					.addContainerGap())
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(159)
					.addComponent(btnNewButton_1)
					.addPreferredGap(ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
					.addComponent(btnNewButton_2)
					.addGap(132))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(actName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_5)
							.addComponent(women)
							.addComponent(man))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_3)
							.addComponent(id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_4)))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_6))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(acttime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_7)
								.addComponent(leadername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_8)
								.addComponent(actType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_9)
						.addComponent(actDesc, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		getContentPane().setBackground(new Color(135,206,250));
		
		JLabel lblNewLabel = new JLabel("活动名称：");
		
		sname = new JTextField();
		sname.setBackground(new Color(224,255,255));
		sname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("负责人：");
		
		sleadername = new JTextField();
		sleadername.setBackground(new Color(224,255,255));
		sleadername.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("社团名称：");
		
		sactType = new JComboBox();
		sactType.setBackground(new Color(224,255,255));
		
		JButton btnNewButton = new JButton("搜索");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchActAction(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(ListAct.class.getResource("/images/search.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sname, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sleadername, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sactType, 0, 93, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(sname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(sactType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton)
						.addComponent(lblNewLabel_1)
						.addComponent(sleadername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		actTable = new JTable();
		actTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ActTableMouseAction(e);
			}
		});
		actTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"活动编号", "活动名称", "负责人", "负责人性别", "活动时间", "所属社团", "活动描述"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		actTable.getColumnModel().getColumn(0).setPreferredWidth(54);
		actTable.getColumnModel().getColumn(1).setPreferredWidth(116);
		actTable.getColumnModel().getColumn(2).setPreferredWidth(47);
		actTable.getColumnModel().getColumn(5).setPreferredWidth(88);
		actTable.getColumnModel().getColumn(6).setPreferredWidth(95);

		scrollPane.setViewportView(actTable);
		getContentPane().setLayout(groupLayout);
		actDesc.setBorder(new LineBorder(new Color(127,157,185),1,false));
		actTable.getTableHeader().setBackground(new Color(135,206,235));
		actTable.getParent().setBackground(new Color(135,206,235));
		actTable.setBackground(new Color(175,238,238));
		actTable.setForeground(new Color(70,130,180));
		
		this.fillActType("search");
		this.fillActType("modify");
		this.fillTable(new Activity());

		//加载图片
		ImageIcon icon=new ImageIcon(Main.class.getResource("/images/dala.png"));
		//Image im=new Image(icon);
		//将图片放入label中
		JLabel label=new JLabel(icon);

		//设置label的大小
		label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		getContentPane().add(label,new Integer(Integer.MIN_VALUE));
	}


	//修改活动信息
	private void updateActAction(ActionEvent e) {
		String id=this.id.getText();
		if(StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要删除的活动信息");
			return;
		}
		String actName =this.actName.getText();
		String leadername =this.leadername.getText();
		String sex="";
		if(man.isSelected()) {
			sex="男";
		}else if(women.isSelected()) {
			sex="女";
		}
		String acttime =this.acttime.getText();
		if(StringUtil.isEmpty(actName)) {
			JOptionPane.showMessageDialog(null, "活动名称不能为空");
			return;
		}
		if(StringUtil.isEmpty(leadername)) {
			JOptionPane.showMessageDialog(null, "活动负责人不能为空");
			return;
		}
		if(StringUtil.isEmpty(acttime)) {
			JOptionPane.showMessageDialog(null, "活动时间不能为空");
			return;
		}
		ActType actType =(ActType) this.actType.getSelectedItem();
		int actTypeid = actType.getId();
		String actDesc =this.actDesc.getText();
		
		
		Connection con=null;
		Activity activity=new Activity(Integer.parseInt(id), actName, leadername,sex,acttime, actTypeid, actDesc);
		try {
			con=dbUtil.getCon();
			int result= actDao.updateAct(con, activity);
			if(result==1) {
				JOptionPane.showMessageDialog(null, "修改活动成功");
				reset();
				this.fillTable(new Activity());
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



	//删除活动
	private void deleteActAction(ActionEvent e) {

		String id=this.id.getText();
		Connection con=null;
		try {
			con=dbUtil.getCon();
			int result= actDao.deleteAct(con, id);
			if(result==1) {
				JOptionPane.showMessageDialog(null, "删除活动成功");
				reset();
				this.fillTable(new Activity());     //初始化表格数据
			}else {
				JOptionPane.showMessageDialog(null, "删除活动失败");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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



	//表格点击事件
	private void ActTableMouseAction(MouseEvent met) {
		// TODO Auto-generated method stub
		int row=this.actTable.getSelectedRow();
		int a=(int) actTable.getValueAt(row,0);
		this.id.setText(a+""); 
		this.actName.setText((String) actTable.getValueAt(row, 1));
		this.leadername.setText((String) actTable.getValueAt(row, 2));
		String sex=(String) actTable.getValueAt(row, 3);
		if("男".equals(sex)) {
			this.man.setSelected(true);
		}else if("女".equals(sex)) {
			this.women.setSelected(true);
		}
		this.acttime.setText((String) actTable.getValueAt(row, 4)+"");
		String actTypeName =(String)this.actTable.getValueAt(row, 5);
		int n=this.actType.getItemCount();
		for(int i=0;i<n;i++) {
			ActType item=(ActType)this.actType.getItemAt(i);
			if(item.getActTypeName().equals(actTypeName)) {
				this.actType.setSelectedIndex(i);
			}
		}
		this.actDesc.setText((String) actTable.getValueAt(row, 6));
	}



	//查询活动
	private void SearchActAction(ActionEvent evt) {
		String actName =this.sname.getText();
		String LeaderName =this.sleadername.getText();
		ActType actType =(ActType) this.sactType.getSelectedItem();
		int actTypeid = actType.getId();
		
		Activity activity=new Activity(actName, LeaderName, actTypeid);
		this.fillTable(activity);
	}



	//初始化下拉框
	private void fillActType(String type) {
		Connection con=null;
		ActType actType =null;
		try {
			con=dbUtil.getCon();
			ResultSet rs= actTypeDao.list(con, new ActType());
			if("search".equals(type)) {
				actType =new ActType();
			    actType.setActTypeName("请选择");
			    actType.setId(-1);
			  this.sactType.addItem(actType);
			}
			while(rs.next()) {
				actType =new ActType();
				actType.setId(rs.getInt("id"));
				actType.setActTypeName(rs.getString("actTypeName"));
				if("search".equals(type)) {
					this.sactType.addItem(actType);
			  }else if("modify".equals(type)) {
				  	this.actType.addItem(actType);
			  } 
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

	//初始化表格数据
	private void fillTable(Activity activity) {
		DefaultTableModel dtm=(DefaultTableModel) actTable.getModel(); //要显示JTable组件（需要用到）TableModel接口（需要下面这个类才能实现）DefaultTableModel类
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			ResultSet rs= actDao.ListAct(con,activity);
			while(rs.next()) {
				Vector v=new Vector();                //定义一个Vector数组（Vector各个元素由Vector组成，即数组的“二维存储”）
				v.add(rs.getInt("id"));
				v.add(rs.getString("actName"));
				v.add(rs.getString("leadername"));
				v.add(rs.getString("sex"));
				v.add(rs.getString("acttime"));
				v.add(rs.getString("actTypeName"));
				v.add(rs.getString("actDesc"));
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
		this.id.setText("");
		this.actName.setText("");
		this.leadername.setText("");
		this.man.setSelected(true);
		this.acttime.setText("");
		this.actDesc.setText("");
		if(this.actType.getItemCount()>0) {
			this.actType.setSelectedIndex(0);
		}
	}

}
	