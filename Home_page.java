package bankAdmin;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Home_page extends JFrame implements ActionListener{
	//动作事件监听器，实现ActionListener接口
	
	JFrame jf = new JFrame();
	Container container = jf.getContentPane();
	JButton inquire,add,change,delete,home,out,deposit,draw,loan;
	JPanel jp1,jp2;
	JLabel  num;
	JTextField cardNum;
	JTable table;
	CustomerModel cm;
	JScrollPane jsp;
	
	 //定义连接数据库的变量
	 Statement stat = null;
	 PreparedStatement ps;
	 Connection ct = null;
	 ResultSet rs = null;
	
	public Home_page() {
		
		jf.setTitle("村镇银行管理系统");
		jf.setVisible(true);//使窗口可视
		jf.setSize(1200,500);//窗口的大小
		jf.setLocation(150, 150);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗口关闭方式
		
		jp1 = new JPanel();
		num = new JLabel("请输入卡号：");
		cardNum = new JTextField(20);
		inquire = new JButton("查询");
		inquire.addActionListener(this);
		jp1.add(num);
		jp1.add(cardNum);
		jp1.add(inquire);
		
		
		jp2 = new JPanel();
		add = new JButton("添加");
		add.addActionListener(this);
		change = new JButton("修改");
		change.addActionListener(this);
		delete = new JButton("删除");
		delete.addActionListener(this);
		home = new JButton("主界面");
		home.addActionListener(this);
		out = new JButton("退出系统");
		out.addActionListener(this);
		deposit = new JButton("存款");
		deposit.addActionListener(this);
		draw = new JButton("取款");
		draw.addActionListener(this);
		loan = new JButton("贷款");
		loan. addActionListener(this);

		
		jp2.add(add);
		jp2.add(change);
		jp2.add(delete);
		jp2.add(home);
		jp2.add(out);
		jp2.add(deposit);
		jp2.add(draw);
		jp2.add(loan);
		
		//创建模型对象
		cm = new CustomerModel();
		
		//初始化
		table = new JTable(cm);
		
		//若表格显示不全设置滚动条
		jsp = new JScrollPane(table);
		jf.add(jsp);
		jf.add(jp1,"North");
		jf.add(jp2,"South");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == inquire) { 
			//因为把对表的数据封装到StuModel中，可以比较简单的完成查询
			String cardNum = this.cardNum.getText().trim();
			//写一个sql语句
			String sql = "select * from customer1 where cardNumber = '"+cardNum+"' ";
			//构建一个数据模型类，并更新
			cm = new CustomerModel(sql);
			//更新jtable
			table.setModel(cm);
		}
		
		else if(e.getSource() == add) {
			AddCustomer add = new AddCustomer(this,"添加学生",true);
			String cardNum= add.cardNumber;
			String sql = "select * from customer1 where cardNumber = '"+cardNum+"' ";
			//构建一个数据模型类，并更新
			cm = new CustomerModel(sql);
			table.setModel(cm);
		}
		
		else if(e.getSource()==delete) {
			int rowNum = this.table.getSelectedRow();//getSelectedRow会返回给用户点中的行
			//如果该用户一行都没有选，就返回-1
			if(rowNum == -1){
				//提示
				JOptionPane.showMessageDialog(this, "请选中一行");
				return ;
			}
			
			//得到用户ID
			String cusCardNum = (String)cm.getValueAt(rowNum, 0);
			System.out.println("Id： "+cusCardNum);
			
			String tip = "你确定要删除卡号为"+ cusCardNum + "的成员嘛？";
			int n = JOptionPane.showConfirmDialog(this, tip , "询问",JOptionPane.YES_NO_OPTION);
			if(n==0) {
			//连接数据库,完成删除任务
			  try{
			  //1.加载驱动
			  Class.forName("com.mysql.jdbc.Driver");
			  //2.连接数据库
			  ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
			  System.out.println("连接成功");
			  ps = ct.prepareStatement("delete from customer1 where cardNumber = ?");
			  ps.setString(1,cusCardNum);
			  ps.executeUpdate();
			  }catch(Exception arg1){
				  arg1.printStackTrace();
			  }finally{
				  try{
					  if(rs!= null){
						  rs.close();
						  rs = null;

					  }
					  if(ps!= null){
						  ps.close();
						  ps = null;
					  }
					  if(ct != null){
						  ct.close();
						  ct = null;
					  }
				  } catch(Exception arg2){
					  arg2.printStackTrace();
				  }
			  }
			  cm = new CustomerModel();
			  //更新jtable
			  table.setModel(cm);
			}
		}
		
		//主界面
		else if(e.getSource() == home) {
			cm = new CustomerModel();
			cardNum.setText("");
			table.setModel(cm);
		}
		
		//退出
		else if(e.getSource() == out) {
			int n = JOptionPane.showConfirmDialog(this,"你确定要退出嘛？", "询问",JOptionPane.YES_NO_OPTION);
//			JOptionPane.showMessageDialog(null, "你选择了退出", "提示信息",
//		              JOptionPane.INFORMATION_MESSAGE);
//			jf.dispose();//关闭添加对话框
			if(n==0) {
				System.exit(0); 
			}
		}
		
		//更改
		else if(e.getSource() == change) {
			int rowNum = this.table.getSelectedRow();//getSelectedRow会返回给用户点中的行
			//如果该用户一行都没有选，就返回-1
			if(rowNum == -1){
				//提示
				JOptionPane.showMessageDialog(this, "请选中一行");
				return ;
			}
			ChangeCustomer changeCustomer = new ChangeCustomer(this, "修改信息", true, cm, rowNum);
			String cardNum= changeCustomer.cardNumber;
			String sql = "select * from customer1 where cardNumber = '"+cardNum+"' ";
			//构建一个数据模型类，并更新
			cm = new CustomerModel(sql);
			table.setModel(cm);
		}
		//存款
		else if(e.getSource() == deposit) {
			int rowNum = this.table.getSelectedRow();//getSelectedRow会返回给用户点中的行
			//如果该用户一行都没有选，就返回-1
			if(rowNum == -1){
				//提示
				JOptionPane.showMessageDialog(this, "请选择存款用户");
				return ;
			}
			Deposit deposit = new Deposit(this, "存款", true, cm, rowNum);
			String cardNum=deposit.cardNumber;
			String sql = "select * from customer1 where cardNumber = '"+cardNum+"' ";
			//构建一个数据模型类，并更新
			cm = new CustomerModel(sql);
			table.setModel(cm);
		}
		//取款
		else if(e.getSource() == draw) {
			int rowNum = this.table.getSelectedRow();//getSelectedRow会返回给用户点中的行
			//如果该用户一行都没有选，就返回-1
			if(rowNum == -1){
				//提示
				JOptionPane.showMessageDialog(this, "请选择存款用户");
				return ;
			}
			Draw draw = new Draw(this, "取款", true, cm, rowNum);
			String cardNum=draw.cardNumber;
			String sql = "select * from customer1 where cardNumber = '"+cardNum+"' ";
			//构建一个数据模型类，并更新
			cm = new CustomerModel(sql);
			table.setModel(cm);
		}
	}
	
}
