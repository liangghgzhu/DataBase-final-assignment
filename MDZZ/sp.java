package MDZZ;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class sp {
	static Scanner sc = new Scanner(System.in);
	private static String username;
	private static String password;
	public static void main(String args[])
	{
		//略略略
	}
	public void login()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("成功连接数据库");
		}
		catch(Exception e){
			System.out.println("连接数据库失败");
			e.printStackTrace();
		}
		try{
			int n = 0;
			System.out.println("请输入序号进行操作：1.登录  2.注册 ");
			if(sc.hasNext())
			{
				n = sc.nextInt();
				while(n != 1 && n != 2)
				{
					System.out.println("无效数字，请重新输入。");
					System.out.println("请输入序号进行操作：1.登录  2.注册");
					n = sc.nextInt();
				}
				switch(n)
				{
				case 1:
				{
					SignIn();
					Operate();
					break;
				}
				case 2:
				{
					SignUp();
					Operate();
					break;
				}
				}
			}
		}
		catch(Exception e){
			System.out.println("Error connecting MySQL Server.");
			e.printStackTrace();
		}
	}
	public void SignIn()throws SQLException
	{
		try
		{
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sp1?useSSL=false","root","kuangjunhao29");
			System.out.print("输入账号：");
			username = sc.next();
			System.out.print("输入密码：");
			password = sc.next();
			PreparedStatement pst =connect.prepareStatement("SELECT NAME,PASSWORD FROM USER WHERE NAME = ?AND PASSWORD = ?");
			pst.setString(1, username);
			pst.setString(2, password);
			ResultSet result = pst.executeQuery();
			if(result.next())
			{
				System.out.println("登陆成功");
			}
			else
			{
				System.out.println("登录失败，账号或密码错误");
				SignIn();
			}
			result.close();
			connect.close();
			pst.close();
		}
		catch(Exception e){
			System.out.println("登录失败");
		}
	}
	public void SignUp()throws SQLException
	{
		try
		{
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sp1?useSSL=false","root","kuangjunhao29");
			System.out.print("输入账号：");
			username = sc.next();
			PreparedStatement pst;
			pst = connect.prepareStatement("SELECT NAME FROM USER WHERE NAME = ?");
			pst.setString(1, username);
			System.out.print("输入密码：");
			password = sc.next();
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				System.out.println("用户名已存在，请重新输入。");
				SignUp();
			}
			else
			{
				pst = connect.prepareStatement("INSERT INTO USER VALUES(?,?)");
				pst.setString(1,username);
				pst.setString(2, password);
				pst.executeUpdate();
				System.out.println("注册成功,自动登录。");
				pst.close();
				sc.close();
				rs.close();
			}
		}
		catch(Exception e)
		{
			System.out.println("注册失败");
		}
	}
	public void Operate() throws SQLException
	{
		try
		{
			System.out.println("输入想要进行的操作：1.增加数据  2.删除数据  3.查找数据 4.修改数据 5.退出登录");
			int n = 0;
			n = sc.nextInt();
			switch(n)
			{
			case 1:addData();break;
			case 2:deleteData();break;
			case 3:searchData();break;
			case 4:modifyData();break;
			case 5:login();break;
			default:
			{
				System.out.println("无效数字，请重新输入。");
				Operate();
				break;
			}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("操作失败（1）。");
		}
	}
	public void addData() throws SQLException
	{
		try
		{
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sp1?useSSL=false","root","kuangjunhao29");
			System.out.println("请输入想添加数据的表格：1.零件表 2.供应商表 3.供应货表  4.返回上一层");
			int n = 0;
			n = sc.nextInt();
			PreparedStatement pst;
			while(n != 1 && n != 2 && n != 3 && n !=4 )
			{
				System.out.println("无效数字，请重新输入。");
				addData();
			}
			switch(n)
			{
			case 1:
			{
				try
				{
					pst = connect.prepareStatement("INSERT INTO P VALUES(?,?,?,?,?)");
					String PNO, PNAME, COLOR, CITY;
					float WEIGHT;
					System.out.print("请输入零件编号PNO:");
					PNO = sc.next();
					System.out.print("请输入零件名称PNAME：");
					PNAME = sc.next();
					System.out.print("请输入零件重量WEIGHT：");
					WEIGHT = sc.nextFloat();
					System.out.print("请输入零件颜色COLOR：");
					COLOR = sc.next();
					System.out.print("请输入零件所属城市CITY：");
					CITY = sc.next();
					pst.setString(1, PNO);
					pst.setString(2, PNAME);
					pst.setFloat(3, WEIGHT);
					pst.setString(4, COLOR);
					pst.setString(5, CITY);
					pst.executeUpdate();
					System.out.println("添加成功,添加后表的数据如下：");
					Statement statement = connect.createStatement();
					ResultSet result = statement.executeQuery("SELECT * FROM P");
					System.out.println("---------------------------");
					System.out.println("PNO  "+"PNAME  "+"WEIGHT "+"COLOR "+ "CITY");
					System.out.println("---------------------------");
					while(result.next())
					{
					    System.out.print(result.getString("PNO")+"   ");
					    System.out.print(result.getString("PNAME")+"     ");
					    System.out.print(result.getFloat("WEIGHT")+"    ");
					    System.out.print(result.getString("COLOR")+"     ");
					    System.out.println(result.getString("CITY"));
					}
					System.out.println("---------------------------");
					result.close();
					pst.close();
					Operate();
					break;
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("添加失败（1）");
					Operate();
				}
			}
			case 2:
			{
				try
				{
					pst = connect.prepareStatement("INSERT INTO S VALUES(?,?,?,?)");
					String SNO,SNAME,CITY,STATUS;
					System.out.print("输入供应商编号SNO：");
					SNO = sc.next();
					System.out.print("请输入供应商名称SNAME：");
					SNAME = sc.next();
					System.out.print("请输入供应商所在城市CITY：");
					CITY = sc.next();
					System.out.print("请输入供应商状态STATUS（没有则填写\"NULL\"）：");
					STATUS =sc.next();
					pst.setString(1, SNO);
					pst.setString(2, SNAME);
					pst.setString(3, CITY);
					pst.setString(4, STATUS);
					pst.executeUpdate();
					System.out.println("添加成功，添加后表数据如下：");
					Statement statement = connect.createStatement();
					ResultSet result = statement.executeQuery("SELECT * FROM S");
					System.out.println("---------------------------");
					System.out.println("SNO  "+"SNAME  "+"CITY "+"STATUS ");
					System.out.println("---------------------------");
					while(result.next())
					{
					    System.out.print(result.getString("SNO")+"   ");
					    System.out.print(result.getString("SNAME")+"     ");
					    System.out.print(result.getString("CITY")+"    ");
					    System.out.println(result.getString("STATUS"));
					}
					System.out.println("---------------------------");
					result.close();
					pst.close();
					Operate();
					break;
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("添加失败（2）。");
					Operate();
				}
			}
			case 3:
			{
				try
				{
					pst = connect.prepareStatement("INSERT INTO SP VALUES(?,?,?)");
					String SNO,PNO;
					float QTY;
					System.out.print("输入供应商编号SNO：");
					SNO = sc.next();
					System.out.print("请输入零件编号PNO：");
					PNO = sc.next();
					System.out.print("请输入零件数量QTY：");
					QTY = sc.nextFloat();
					pst.setString(1, SNO);
					pst.setString(2, PNO);
					pst.setFloat(3, QTY);
					pst.executeUpdate();
					System.out.println("添加成功，添加后表数据如下：");
					Statement statement = connect.createStatement();
					ResultSet result = statement.executeQuery("SELECT * FROM SP");
					System.out.println("---------------------------");
					System.out.println("SNO  "+"PNO  "+"QTY ");
					System.out.println("---------------------------");
					while(result.next())
					{
					    System.out.print(result.getString("SNO")+"   ");
					    System.out.print(result.getString("PNO")+"     ");
					    System.out.println(result.getString("QTY"));
					}
					System.out.println("---------------------------");
					result.close();
					pst.close();
					Operate();
					break;
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("添加失败（3）");
					Operate();
				}
			}
			case 4:
			{
				Operate();
				break;
			}
			}
			connect.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("操作失败（0），返回上一层。");
			Operate();
		}
	}
	public void deleteData() throws SQLException
	{
		try
		{
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sp1?useSSL=false","root","kuangjunhao29");
			PreparedStatement pst;
			System.out.println("请选择你需要删除数据的表1.零件表  2.供应商表  3.供应货物表：");
			int n = sc.nextInt();
			while(n != 1 && n != 2 && n != 3 && n != 4)
			{
				System.out.println("输入无效数字，请重新输入。");
				deleteData();
			}
			switch(n)
			{
			case 1:
			{
				try
				{
					pst = connect.prepareStatement("DELETE FROM P WHERE ? = ?");
					String n1, n2;
					System.out.print("第一条项目：");
					n1 = sc.next();
					System.out.print("第二条项目：");
					n2 = sc.next();
					pst.setString(1, n1);
					pst.setString(2, n2);
					pst.executeUpdate();
					System.out.println("删除成功，删除后表格数据如下：");
					Statement statement = connect.createStatement();
					ResultSet result = statement.executeQuery("SELECT * FROM P");
					System.out.println("---------------------------");
					System.out.println("PNO  "+"PNAME  "+"WEIGHT "+"COLOR "+ "CITY");
					System.out.println("---------------------------");
					while(result.next())
					{
					    System.out.print(result.getString("PNO")+"   ");
					    System.out.print(result.getString("PNAME")+"     ");
					    System.out.print(result.getFloat("WEIGHT")+"    ");
					    System.out.print(result.getString("COLOR")+"     ");
					    System.out.println(result.getString("CITY"));
					}
					System.out.println("---------------------------");
					result.close();
					pst.close();
					Operate();
					break;
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("删除失败（1）");
				}
			}
			case 2:
			case 3:
			case 4:
			}
			pst = connect.prepareStatement("");
			connect.close();
			pst.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("操作失败（5），自动返回上一层。");
			Operate();
		}
	}
	public void searchData() throws SQLException
	{
		try
		{
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sp1?useSSL=false","root","kuangjunhao29");
			PreparedStatement pst;
			pst = connect.prepareStatement("");
			connect.close();
			pst.close();
			
		}
		catch(Exception e)
		{
			
		}
	}
	public void modifyData() throws SQLException
	{
		try
		{
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sp1?useSSL=false","root","kuangjunhao29");
			connect.close();
			PreparedStatement pst;
			pst = connect.prepareStatement("");
			pst.close();
		}
		catch(Exception e)
		{
			
		}
	}
}