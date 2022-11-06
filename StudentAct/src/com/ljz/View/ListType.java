package com.ljz.View;

import com.ljz.Dao.ActDao;
import com.ljz.Dao.ActTypeDao;
import com.ljz.Model.ActType;
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

public class ListType extends JInternalFrame {
	private JTable actTypeTable;
	private DbUtil dbUtil=new DbUtil();
	private ActTypeDao actTypeDao =new ActTypeDao();
	private ActDao actDao =new ActDao();
	private JTextField acttype;
	private JTextField id;
	private JTextField actTypename;
	private JTextArea actTypedesc;



	//启动
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListType frame = new ListType();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//创建社团维护窗体
	public ListType() {
		setTitle("社团管理");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 495, 491);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("社团名称 ");
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			actTypeSearchAction(e);
			}
		});
		
		acttype = new JTextField();
		acttype.setBackground(new Color(224,255,255));
		acttype.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "修改信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		panel.setBackground(new Color(187,255,255));
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(77)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(acttype, 145, 145, 145)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addGap(82))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE))
					.addGap(50))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(acttype, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 197, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblNewLabel_1 = new JLabel("编号");
		
		id = new JTextField();
		id.setBackground(new Color(224,255,255));
		id.setEditable(false);
		id.setText("");
		id.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("社团名称");
		
		actTypename = new JTextField();
		actTypename.setBackground(new Color(224,255,255));
		actTypename.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("描述");
		
		actTypedesc = new JTextArea();
		actTypedesc.setBackground(new Color(224,255,255));
		
		JButton btnNewButton_1 = new JButton("修改");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actTypeUpdate(e);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(ListType.class.getResource("/images/modify.png")));
		
		JButton btnNewButton_2 = new JButton("删除");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteTypeAction(e);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(ListType.class.getResource("/images/delete.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(id, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(actTypename, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(actTypedesc, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(btnNewButton_1)
							.addGap(76)
							.addComponent(btnNewButton_2)
							.addGap(64))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(actTypename, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(actTypedesc, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_1))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);

		getContentPane().setBackground(new Color(135,206,250));
		actTypedesc.setBorder(new LineBorder(new Color(127,157,185),1,false));
		
		actTypeTable = new JTable();
		actTypeTable.setBackground(new Color(187,255,255));
		actTypeTable.setGridColor(new Color(70,130,180));
		actTypeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actTypemousePressed(e);
				}
		});
		actTypeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"社团编号", "社团名称", "社团描述"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		actTypeTable.getColumnModel().getColumn(1).setPreferredWidth(123);
		actTypeTable.getColumnModel().getColumn(2).setPreferredWidth(176);
		scrollPane.setViewportView(actTypeTable);
		scrollPane.setBackground(new Color(70,130,180));
		getContentPane().setLayout(groupLayout);
		actTypeTable.setForeground(new Color(70,130,180));
		actTypeTable.getTableHeader().setBackground(new Color(173,216,230));
		actTypeTable.getParent().setBackground(new Color(173,216,230));


		//加载图片
		ImageIcon icon=new ImageIcon(Main.class.getResource("/images/dala.png"));
		//Image im=new Image(icon);
		//将图片放入label中
		JLabel label=new JLabel(icon);

		//设置label的大小
		label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		getContentPane().add(label,new Integer(Integer.MIN_VALUE));

		this.fillTable(new ActType());
	}


	//修改数据
	private void actTypeUpdate(ActionEvent e) {
		String i=id.getText();
		String actTypeName = actTypename.getText();
		String actTypeDesc= actTypedesc.getText();
		if(StringUtil.isEmpty(i)) {
			JOptionPane.showMessageDialog(null, "请选择要修改的社团");
			return;
		}
		if(StringUtil.isEmpty(actTypeName)){
			JOptionPane.showMessageDialog(null, "社团名称不能为空");
			return;
		}
		ActType actType =new ActType(Integer.parseInt(i), actTypeName,actTypeDesc);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			int modifNum= actTypeDao.update(con, actType);
			if(modifNum==1) {
				JOptionPane.showMessageDialog(null, "修改社团信息成功");
				this.fillTable(new ActType());
			}else {
				JOptionPane.showMessageDialog(null,"修改社团信息失败");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null,"修改社团信息失败");
		}finally {
			try {
				dbUtil.close(con);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	//删除社团
	private void deleteTypeAction(ActionEvent evt) {
		// TODO Auto-generated method stub
		String i=this.id.getText();
		if(StringUtil.isEmpty(i)) {
			JOptionPane.showMessageDialog(null, "请选择要删除的社团");
			return;
		}
		int t=JOptionPane.showConfirmDialog(null, "确定要删除这个社团吗");
		if(t==0) {
		Connection con=null;
		try {
			con=dbUtil.getCon();
			if(actDao.exitActByActTypeId(con, i)) {
				JOptionPane.showMessageDialog(null, "社团下有活动，不能删除");
				return;
			}
			int n= actTypeDao.delete(con, i);
			if(n==1) {
				JOptionPane.showMessageDialog(null, "删除社团成功");
				this.resetValue();
				this.fillTable(new ActType());;
			}else {
				JOptionPane.showMessageDialog(null, "删除社团失败");
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


	//重置
	private void resetValue() {
		// TODO Auto-generated method stub
		this.id.setText("");
		this.actTypename.setText("");
		this.actTypedesc.setText("");
	}



	//表格点击事件
	private void actTypemousePressed(MouseEvent evt) {
		int row= actTypeTable.getSelectedRow();
		Object c= actTypeTable.getValueAt(row,0);
		String str=c.toString();
		id.setText(str);
		actTypename.setText((String) actTypeTable.getValueAt(row, 1));
		actTypedesc.setText((String) actTypeTable.getValueAt(row, 2));
	}



	//初始化表格
	private void fillTable(ActType actType) {
		DefaultTableModel dtm=(DefaultTableModel) actTypeTable.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			ResultSet rs= actTypeDao.list(con, actType);
			while(rs.next()) {
				Vector v=new Vector();
				v.add(rs.getInt("id"));
				v.add(rs.getString("actTypeName"));
				v.add(rs.getString("actTypeDesc"));
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


	//社团查询
	private void actTypeSearchAction(ActionEvent evt) {
		// TODO Auto-generated method stub
		String sname=this.acttype.getText();
		ActType actType =new ActType();
		actType.setActTypeName(sname);
		this.fillTable(actType);
	}

}
