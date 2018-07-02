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
		//������
	}
	public void login()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("�ɹ��������ݿ�");
		}
		catch(Exception e){
			System.out.println("�������ݿ�ʧ��");
			e.printStackTrace();
		}
		try{
			int n = 0;
			System.out.println("��������Ž��в�����1.��¼  2.ע�� ");
			if(sc.hasNext())
			{
				n = sc.nextInt();
				while(n != 1 && n != 2)
				{
					System.out.println("��Ч���֣����������롣");
					System.out.println("��������Ž��в�����1.��¼  2.ע��");
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
			System.out.print("�����˺ţ�");
			username = sc.next();
			System.out.print("�������룺");
			password = sc.next();
			PreparedStatement pst =connect.prepareStatement("SELECT NAME,PASSWORD FROM USER WHERE NAME = ?AND PASSWORD = ?");
			pst.setString(1, username);
			pst.setString(2, password);
			ResultSet result = pst.executeQuery();
			if(result.next())
			{
				System.out.println("��½�ɹ�");
			}
			else
			{
				System.out.println("��¼ʧ�ܣ��˺Ż��������");
				SignIn();
			}
			result.close();
			connect.close();
			pst.close();
		}
		catch(Exception e){
			System.out.println("��¼ʧ��");
		}
	}
	public void SignUp()throws SQLException
	{
		try
		{
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sp1?useSSL=false","root","kuangjunhao29");
			System.out.print("�����˺ţ�");
			username = sc.next();
			PreparedStatement pst;
			pst = connect.prepareStatement("SELECT NAME FROM USER WHERE NAME = ?");
			pst.setString(1, username);
			System.out.print("�������룺");
			password = sc.next();
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				System.out.println("�û����Ѵ��ڣ����������롣");
				SignUp();
			}
			else
			{
				pst = connect.prepareStatement("INSERT INTO USER VALUES(?,?)");
				pst.setString(1,username);
				pst.setString(2, password);
				pst.executeUpdate();
				System.out.println("ע��ɹ�,�Զ���¼��");
				pst.close();
				sc.close();
				rs.close();
			}
		}
		catch(Exception e)
		{
			System.out.println("ע��ʧ��");
		}
	}
	public void Operate() throws SQLException
	{
		try
		{
			System.out.println("������Ҫ���еĲ�����1.��������  2.ɾ������  3.�������� 4.�޸����� 5.�˳���¼");
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
				System.out.println("��Ч���֣����������롣");
				Operate();
				break;
			}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("����ʧ�ܣ�1����");
		}
	}
	public void addData() throws SQLException
	{
		try
		{
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sp1?useSSL=false","root","kuangjunhao29");
			System.out.println("��������������ݵı��1.����� 2.��Ӧ�̱� 3.��Ӧ����  4.������һ��");
			int n = 0;
			n = sc.nextInt();
			PreparedStatement pst;
			while(n != 1 && n != 2 && n != 3 && n !=4 )
			{
				System.out.println("��Ч���֣����������롣");
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
					System.out.print("������������PNO:");
					PNO = sc.next();
					System.out.print("�������������PNAME��");
					PNAME = sc.next();
					System.out.print("�������������WEIGHT��");
					WEIGHT = sc.nextFloat();
					System.out.print("�����������ɫCOLOR��");
					COLOR = sc.next();
					System.out.print("�����������������CITY��");
					CITY = sc.next();
					pst.setString(1, PNO);
					pst.setString(2, PNAME);
					pst.setFloat(3, WEIGHT);
					pst.setString(4, COLOR);
					pst.setString(5, CITY);
					pst.executeUpdate();
					System.out.println("��ӳɹ�,��Ӻ����������£�");
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
					System.out.println("���ʧ�ܣ�1��");
					Operate();
				}
			}
			case 2:
			{
				try
				{
					pst = connect.prepareStatement("INSERT INTO S VALUES(?,?,?,?)");
					String SNO,SNAME,CITY,STATUS;
					System.out.print("���빩Ӧ�̱��SNO��");
					SNO = sc.next();
					System.out.print("�����빩Ӧ������SNAME��");
					SNAME = sc.next();
					System.out.print("�����빩Ӧ�����ڳ���CITY��");
					CITY = sc.next();
					System.out.print("�����빩Ӧ��״̬STATUS��û������д\"NULL\"����");
					STATUS =sc.next();
					pst.setString(1, SNO);
					pst.setString(2, SNAME);
					pst.setString(3, CITY);
					pst.setString(4, STATUS);
					pst.executeUpdate();
					System.out.println("��ӳɹ�����Ӻ���������£�");
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
					System.out.println("���ʧ�ܣ�2����");
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
					System.out.print("���빩Ӧ�̱��SNO��");
					SNO = sc.next();
					System.out.print("������������PNO��");
					PNO = sc.next();
					System.out.print("�������������QTY��");
					QTY = sc.nextFloat();
					pst.setString(1, SNO);
					pst.setString(2, PNO);
					pst.setFloat(3, QTY);
					pst.executeUpdate();
					System.out.println("��ӳɹ�����Ӻ���������£�");
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
					System.out.println("���ʧ�ܣ�3��");
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
			System.out.println("����ʧ�ܣ�0����������һ�㡣");
			Operate();
		}
	}
	public void deleteData() throws SQLException
	{
		try
		{
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sp1?useSSL=false","root","kuangjunhao29");
			PreparedStatement pst;
			System.out.println("��ѡ������Ҫɾ�����ݵı�1.�����  2.��Ӧ�̱�  3.��Ӧ�����");
			int n = sc.nextInt();
			while(n != 1 && n != 2 && n != 3 && n != 4)
			{
				System.out.println("������Ч���֣����������롣");
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
					System.out.print("��һ����Ŀ��");
					n1 = sc.next();
					System.out.print("�ڶ�����Ŀ��");
					n2 = sc.next();
					pst.setString(1, n1);
					pst.setString(2, n2);
					pst.executeUpdate();
					System.out.println("ɾ���ɹ���ɾ�������������£�");
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
					System.out.println("ɾ��ʧ�ܣ�1��");
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
			System.out.println("����ʧ�ܣ�5�����Զ�������һ�㡣");
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