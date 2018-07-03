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
		
	}
	public void UI()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("�ɹ��������ݿ�");
		}
		catch(Exception e){
			System.out.println("�������ݿ�ʧ�ܡ�");
			e.printStackTrace();
		}
		try{
			int n = 0;
			System.out.print("��������Ž��в�����1.��¼  2.ע�� ");
			if(sc.hasNext())
			{
				n = sc.nextInt();
				while(n != 1 && n != 2)
				{
					System.out.println("��Ч���֣����������롣");
					System.out.print("��������Ž��в�����1.��¼  2.ע��");
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
			System.out.println("�������ݿ�ʧ�ܡ�");
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
			System.out.println("��¼ʧ�ܡ�");
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
			System.out.println("ע��ʧ�ܡ�");
		}
	}
	public void Operate() throws SQLException
	{
		try
		{
			System.out.println("������Ҫ���еĲ����� 1.��������   2.ɾ������   3.��������  4.�޸�����  5.�˳���¼");
			int n = 0;
			n = sc.nextInt();
			switch(n)
			{
			case 1:addData();break;
			case 2:deleteData();break;
			case 3:searchData();break;
			case 4:updateData();break;
			case 5:UI();break;
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
			System.out.println("��������������ݵı��1.�����  2.��Ӧ�̱�  3.��Ӧ����   4.������һ��");
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
					System.out.println("���ʧ�ܣ�1����");
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
					    System.out.println(result.getFloat("QTY"));
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
					System.out.println("���ʧ�ܣ�3����");
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
			System.out.println("��ѡ������Ҫɾ�����ݵı�1.�����  2.��Ӧ�̱�  3.��Ӧ�����  4.�����ϲ㣺");
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
					int m = 0;
					System.out.println("��������Ҫ��������  1.������  2.�������  3.�������  4.�����ɫ  5.����������� ");
					m = sc.nextInt();
					if(m == 1)
					{
						pst = connect.prepareStatement("DELETE FROM P WHERE PNO = ?");
						String n1;
						System.out.print("PNO = ");
						n1 = sc.next();
						pst.setString(1, n1);
						pst.executeUpdate();
					}
					if(m == 2)
					{
						pst = connect.prepareStatement("DELETE FROM P WHERE PNAME = ?");
						String n1;
						System.out.print("PNAME = ");
						n1 = sc.next();
						pst.setString(1, n1);
						pst.executeUpdate();
					}
					if(m == 3)
					{
						pst = connect.prepareStatement("DELETE FROM P WHERE WEIGHT = ?");
						float n1;
						System.out.print("WEIGHT = ");
						n1 = sc.nextFloat();
						pst.setFloat(1, n1);
						pst.executeUpdate();
					}
					if(m == 4)
					{
						pst = connect.prepareStatement("DELETE FROM P WHERE COLOR = ?");
						String n1;
						System.out.print("COLOR:");
						n1 = sc.next();
						pst.setString(1, n1);
						pst.executeUpdate();
					}
					if(m == 5)
					{
						pst = connect.prepareStatement("DELETE FROM P WHERE CITY = ?");
						String n1;
						System.out.print("CITY:");
						n1 = sc.next();
						pst.setString(1, n1);
						pst.executeUpdate();
					}
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
			{
				try
				{
					int m = 0;
					System.out.println("��������Ҫ��������  1.��Ӧ�̱��  2.��Ӧ����  3.��Ӧ����������");
					m = sc.nextInt();
					if(m == 1)
					{
						pst = connect.prepareStatement("DELETE FROM S WHERE SNO = ?");
						String n1;
						System.out.print("��Ӧ�̱��Ϊ��");
						n1 = sc.next();
						pst.setString(1, n1);
						pst.executeUpdate();
					}
					if(m == 2)
					{
						pst = connect.prepareStatement("DELETE FROM S WHERE SNAME = ?");
						String n1;
						System.out.print("��Ӧ������Ϊ��");
						n1 = sc.next();
						pst.setString(1, n1);
						pst.executeUpdate();
					}
					if(m == 3)
					{
						pst = connect.prepareStatement("DELETE FROM S WHERE CITY = ?");
						String n1;
						System.out.print("��Ӧ����������Ϊ��");
						n1 = sc.next();
						pst.setString(1, n1);
						pst.executeUpdate();
					}
					System.out.println("ɾ���ɹ���ɾ�������������£�");
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
					Operate();
					break;
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("ɾ��ʧ�ܣ�2����");
				}
			}
			case 3:
			{
				try
				{
					int m = 0;
					System.out.print("��������Ҫ��������  1.��Ӧ�̱��  2.������  3.�������  ");
					m = sc.nextInt();
					if(m == 1)
					{
						pst = connect.prepareStatement("DELETE FROM SP WHERE SNO = ?");
						String n1;
						System.out.print("SNO = ");
						n1 = sc.next();
						pst.setString(1, n1);
						pst.executeUpdate();
					}
					if(m == 2)
					{
						pst = connect.prepareStatement("DELETE FROM SP WHERE PNO = ?");
						String n1;
						System.out.print("PNO = ");
						n1 = sc.next();
						pst.setString(1, n1);
						pst.executeUpdate();
					}
					if(m == 3)
					{
						pst = connect.prepareStatement("DELETE FROM SP WHERE QTY = ?");
						float n1;
						System.out.print("QTY = ");
						n1 = sc.nextFloat();
						pst.setFloat(1, n1);
						pst.executeUpdate();
					}
					System.out.println("ɾ���ɹ���ɾ�������������£�");
					Statement statement = connect.createStatement();
					ResultSet result = statement.executeQuery("SELECT * FROM SP");
					System.out.println("---------------------------");
					System.out.println("SNO  PNO  QTY");
					System.out.println("---------------------------");
					while(result.next())
					{
					    System.out.print(result.getString("SNO")+"   ");
					    System.out.print(result.getString("PNO")+"     ");
					    System.out.println(result.getFloat("QTY"));
					}
					System.out.println("---------------------------");
					result.close();
					Operate();
					break;
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("ɾ��ʧ�ܣ�3����");
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
			System.out.println("����ʧ�ܣ�5�����Զ�������һ�㡣");
			Operate();
		}
	}
	public void searchData() throws SQLException
	{
		try
		{
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sp1?useSSL=false","root","kuangjunhao29");
			int n = 0;
			System.out.println("��������Ҫ�鿴�ı��  1.�����  2.��Ӧ�̱�  3.��Ӧ�����");
			n = sc.nextInt();
			switch(n)
			{
			case 1:
			{
				try
				{
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
					Operate();
					break;
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("��ѯʧ�ܣ�1����������һ�㡣");
					searchData();
				}
			}
			case 2:
			{
				try
				{
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
					Operate();
					break;
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("��ѯʧ�ܣ�2����������һ�㡣");
					searchData();
				}
			}
			case 3:
			{
				try
				{
					Statement statement = connect.createStatement();
					ResultSet result = statement.executeQuery("SELECT * FROM SP");
					System.out.println("---------------------------");
					System.out.println("SNO  PNO  QTY");
					System.out.println("---------------------------");
					while(result.next())
					{
					    System.out.print(result.getString("SNO")+"   ");
					    System.out.print(result.getString("PNO")+"     ");
					    System.out.println(result.getFloat("QTY"));
					}
					System.out.println("---------------------------");
					result.close();
					Operate();
					break;
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("��ѯʧ�ܣ�3����������һ�㡣");
					searchData();
				}
			}
				default:
				{
					System.out.println("����������������롣");
					searchData();
				}
			}
			connect.close();		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("����ʧ�ܣ�6�����Զ�������һ�㡣");
			Operate();
		}
	}
	
	
	
	@SuppressWarnings("resource")
	public void updateData() throws SQLException
	{
		try
		{
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sp1?useSSL=false","root","kuangjunhao29");
			PreparedStatement pst;
			int n = 0;
			System.out.println("��������Ҫ�޸ĵı�  1.��Ӧ�����  2.�����  3.��Ӧ�̱�");
			n = sc.nextInt();
			while(n != 1 && n != 2 && n != 3)
			{
				System.out.println("������Ч���֣����������롣");
				updateData();
			}
			switch(n)
			{
			case 1:
			{
				try
				{
					int m = 0;
					String SNO,PNO;
					float QTY;
					System.out.println("������Ҫ���ĵ���  1.��Ӧ�̱��  2.�����ţ�3.�������");
					m = sc.nextInt();
					if( m == 1)
					{
						int b = 0;
						System.out.println("����1.������  2.������� �����޸�");
						b = sc.nextInt();
						if(b == 1)
						{
							pst = connect.prepareStatement("UPDATE SP SET SNO = ? WHERE PNO = ?");
							System.out.print("�����Ŷ�λ��");
							PNO = sc.next();
							System.out.print("ϣ����Ӧ�̱���޸�Ϊ��");
							SNO = sc.next();
							pst.setString(1, SNO);
							pst.setString(2, PNO);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
							Statement statement = connect.createStatement();
							ResultSet result = statement.executeQuery("SELECT * FROM SP");
							System.out.println("---------------------------");
							System.out.println("SNO  PNO  QTY");
							System.out.println("---------------------------");
							while(result.next())
							{
							    System.out.print(result.getString("SNO")+"   ");
							    System.out.print(result.getString("PNO")+"     ");
							    System.out.println(result.getFloat("QTY"));
							}
							System.out.println("---------------------------");
							result.close();
							Operate();
							break;
						}
						else
						{
							pst = connect.prepareStatement("UPDATE SP SET SNO = ? WHERE QTY = ?");
							System.out.print("���������λ��");
							QTY = sc.nextFloat();
							System.out.print("ϣ����Ӧ�̱���޸�Ϊ��");
							SNO = sc.next();
							pst.setString(1, SNO);
							pst.setFloat(2, QTY);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
							Statement statement = connect.createStatement();
							ResultSet result = statement.executeQuery("SELECT * FROM SP");
							System.out.println("---------------------------");
							System.out.println("SNO  PNO  QTY");
							System.out.println("---------------------------");
							while(result.next())
							{
							    System.out.print(result.getString("SNO")+"   ");
							    System.out.print(result.getString("PNO")+"     ");
							    System.out.println(result.getFloat("QTY"));
							}
							System.out.println("---------------------------");
							result.close();
							Operate();
							break;
						}
					}
					if(m == 2)
					{
						int b = 0;
						System.out.println("����  1.��Ӧ�̱��  2.�������  �����޸�");
						b = sc.nextInt();
						if(b == 1)
						{
							pst = connect.prepareStatement("UPDATE SP SET PNO = ? WHERE SNO = ?");
							System.out.print("��Ӧ�̱�Ŷ�λ��");
							SNO = sc.next();
							System.out.print("ϣ���������޸�Ϊ��");
							PNO = sc.next();
							pst.setString(1, PNO);
							pst.setString(2, SNO);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
							Statement statement = connect.createStatement();
							ResultSet result = statement.executeQuery("SELECT * FROM SP");
							System.out.println("---------------------------");
							System.out.println("SNO  PNO  QTY");
							System.out.println("---------------------------");
							while(result.next())
							{
							    System.out.print(result.getString("SNO")+"   ");
							    System.out.print(result.getString("PNO")+"     ");
							    System.out.println(result.getFloat("QTY"));
							}
							System.out.println("---------------------------");
							result.close();
							Operate();
							break;
						}
						else
						{
							pst = connect.prepareStatement("UPDATE SP SET PNO = ? WHERE QTY = ?");
							System.out.print("���������λ��");
							QTY = sc.nextFloat();
							System.out.print("ϣ���������޸�Ϊ��");
							PNO = sc.next();
							pst.setString(1, PNO);
							pst.setFloat(2, QTY);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
							Statement statement = connect.createStatement();
							ResultSet result = statement.executeQuery("SELECT * FROM SP");
							System.out.println("---------------------------");
							System.out.println("SNO  PNO  QTY");
							System.out.println("---------------------------");
							while(result.next())
							{
							    System.out.print(result.getString("SNO")+"   ");
							    System.out.print(result.getString("PNO")+"     ");
							    System.out.println(result.getFloat("QTY"));
							}
							System.out.println("---------------------------");
							result.close();
							Operate();
							break;
						}
					}
					if(m == 3)
					{
						int b = 0;
						System.out.println("����  1.��Ӧ�̱��  2.������  �����޸�");
						b = sc.nextInt();
						if(b == 1)
						{
							pst = connect.prepareStatement("UPDATE SP SET QTY = ? WHERE SNO = ?");
							System.out.print("��Ӧ�̱�Ŷ�λ��");
							SNO = sc.next();
							System.out.print("ϣ����������޸�Ϊ��");
							QTY = sc.nextFloat();
							pst.setFloat(1, QTY);
							pst.setString(2, SNO);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
							Statement statement = connect.createStatement();
							ResultSet result = statement.executeQuery("SELECT * FROM SP");
							System.out.println("---------------------------");
							System.out.println("SNO  PNO  QTY");
							System.out.println("---------------------------");
							while(result.next())
							{
							    System.out.print(result.getString("SNO")+"   ");
							    System.out.print(result.getString("PNO")+"     ");
							    System.out.println(result.getFloat("QTY"));
							}
							System.out.println("---------------------------");
							result.close();
							Operate();
							break;
						}
						else
						{
							pst = connect.prepareStatement("UPDATE SP SET QTY = ? WHERE PNO = ?");
							System.out.print("�����Ŷ�λ��");
							PNO = sc.next();
							System.out.print("ϣ����������޸�Ϊ��");
							QTY = sc.nextFloat();
							pst.setFloat(1, QTY);
							pst.setString(2, PNO);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
							Statement statement = connect.createStatement();
							ResultSet result = statement.executeQuery("SELECT * FROM SP");
							System.out.println("---------------------------");
							System.out.println("SNO  PNO  QTY");
							System.out.println("---------------------------");
							while(result.next())
							{
							    System.out.print(result.getString("SNO")+"   ");
							    System.out.print(result.getString("PNO")+"     ");
							    System.out.println(result.getFloat("QTY"));
							}
							System.out.println("---------------------------");
							result.close();
							Operate();
							break;
						}
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("�޸�ʧ�ܣ�1����������һ�㡣");
					updateData();
				}
			}
			case 2:
			{
				try
				{
					int m = 0;
					String PNO,PNAME,COLOR,CITY;
					float WEIGHT;
					System.out.println("������Ҫ���ĵ���  1.������  2.������ƣ�3.�������  4.�����ɫ  5.���������");
					m = sc.nextInt();
					if(m == 1)
					{
						int b = 0;
						System.out.println("����  1.�������  2.�������  3.�����ɫ  4.���������  �����޸�");
						b = sc.nextInt();
						if(b == 1)
						{
							pst = connect.prepareStatement("UPDATE P SET PNO = ? WHERE PNAME = ?");
							System.out.print("������ƶ�λ��");
							PNAME = sc.next();
							System.out.print("ϣ���������޸�Ϊ��");
							PNO = sc.next();
							pst.setString(1, PNO);
							pst.setString(2, PNAME);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
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
							Operate();
							pst.close();
							break;
						}
						if(b == 2)
						{
							pst = connect.prepareStatement("UPDATE P SET PNO = ? WHERE WEIGHT = ?");
							System.out.print("���������λ��");
							WEIGHT = sc.nextFloat();
							System.out.print("ϣ���������޸�Ϊ��");
							PNO = sc.next();
							pst.setString(1, PNO);
							pst.setFloat(2, WEIGHT);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
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
							Operate();
							pst.close();
							break;
						}
						if(b == 3)
						{
							pst = connect.prepareStatement("UPDATE P SET PNO = ? WHERE COLOR = ?");
							System.out.print("�����ɫ��λ��");
							COLOR = sc.next();
							System.out.print("ϣ���������޸�Ϊ��");
							PNO = sc.next();
							pst.setString(1, PNO);
							pst.setString(2, COLOR);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
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
							Operate();
							pst.close();
							break;
						}
						if(b == 4)
						{
							pst = connect.prepareStatement("UPDATE P SET PNO = ? WHERE CITY = ?");
							System.out.print("��������ض�λ��");
							CITY = sc.next();
							System.out.print("ϣ���������޸�Ϊ��");
							PNO = sc.next();
							pst.setString(1, PNO);
							pst.setString(2, CITY);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
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
							Operate();
							pst.close();
							break;
						}
					}
					if(m == 2)
					{
						int b = 0;
						System.out.println("����  1.������  2.�������  3.�����ɫ  4.���������  �����޸�");
						b = sc.nextInt();
						if(b == 1)
						{
							pst = connect.prepareStatement("UPDATE P SET PNAME = ? WHERE PNO = ?");
							System.out.print("�����Ŷ�λ��");
							PNO = sc.next();
							System.out.print("ϣ����������޸�Ϊ��");
							PNAME = sc.next();
							pst.setString(1, PNAME);
							pst.setString(2, PNO);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
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
							Operate();
							pst.close();
							break;
						}
						if(b == 2)
						{
							pst = connect.prepareStatement("UPDATE P SET PNAME = ? WHERE WEIGHT = ?");
							System.out.print("���������λ��");
							WEIGHT = sc.nextFloat();
							System.out.print("ϣ����������޸�Ϊ��");
							PNAME = sc.next();
							pst.setString(1, PNAME);
							pst.setFloat(2, WEIGHT);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
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
							Operate();
							pst.close();
							break;
						}
						if(b == 3)
						{
							pst = connect.prepareStatement("UPDATE P SET PNAME = ? WHERE COLOR = ?");
							System.out.print("�����ɫ��λ��");
							COLOR = sc.next();
							System.out.print("ϣ����������޸�Ϊ��");
							PNAME = sc.next();
							pst.setString(1, PNAME);
							pst.setString(2, COLOR);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
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
							Operate();
							pst.close();
							break;
						}
						if(b == 4)
						{
							pst = connect.prepareStatement("UPDATE P SET PNAME = ? WHERE CITY = ?");
							System.out.print("��������ض�λ��");
							CITY = sc.next();
							System.out.print("ϣ����������޸�Ϊ��");
							PNAME = sc.next();
							pst.setString(1, PNAME);
							pst.setString(2, CITY);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
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
							Operate();
							pst.close();
							break;
						}
					}
					if(m == 3)
					{
						int b = 0;
						System.out.println("����  1.������  2.�������  3.�����ɫ  4.���������  �����޸�");
						b = sc.nextInt();
						if(b == 1)
						{
							pst = connect.prepareStatement("UPDATE P SET WEIGHT = ? WHERE PNO = ?");
							System.out.print("�����Ŷ�λ��");
							PNO = sc.next();
							System.out.print("ϣ����������޸�Ϊ��");
							WEIGHT = sc.nextFloat();
							pst.setFloat(1, WEIGHT);
							pst.setString(2, PNO);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
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
							Operate();
							pst.close();
							break;
						}
						if(b == 2)
						{
							pst = connect.prepareStatement("UPDATE P SET WEIGHT = ? WHERE PNO = ?");
							System.out.print("������ƶ�λ��");
							PNAME = sc.next();
							System.out.print("ϣ����������޸�Ϊ��");
							WEIGHT = sc.nextFloat();
							pst.setFloat(1, WEIGHT);
							pst.setString(2, PNAME);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
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
							Operate();
							pst.close();
							break;
						}
						if(b == 3)
						{
							pst = connect.prepareStatement("UPDATE P SET WEIGHT = ? WHERE PNO = ?");
							System.out.print("������ƶ�λ��");
							PNAME = sc.next();
							System.out.print("ϣ����������޸�Ϊ��");
							WEIGHT = sc.nextFloat();
							pst.setFloat(1, WEIGHT);
							pst.setString(2, PNAME);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
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
							Operate();
							pst.close();
							break;
						}
						if(b == 4)
						{
							pst = connect.prepareStatement("UPDATE P SET WEIGHT = ? WHERE PNO = ?");
							System.out.print("������ƶ�λ��");
							PNAME = sc.next();
							System.out.print("ϣ����������޸�Ϊ��");
							WEIGHT = sc.nextFloat();
							pst.setFloat(1, WEIGHT);
							pst.setString(2, PNAME);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
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
							Operate();
							pst.close();
							break;
						}
					}
					if(m == 4)
					{
						int b = 0;
						System.out.println("����  1.������  2.�������  3.�������  4.���������  �����޸�");
						b = sc.nextInt();
						if(b == 1)
						{
							pst = connect.prepareStatement("UPDATE P SET COLOR = ? WHERE PNO = ?");
							System.out.print("�����Ŷ�λ��");
							PNO = sc.next();
							System.out.print("ϣ�������ɫ�޸�Ϊ��");
							COLOR = sc.next();
							pst.setString(1, COLOR);
							pst.setString(2, PNO);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
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
							Operate();
							pst.close();
							break;
						}
						if(b == 2)
						{
							pst = connect.prepareStatement("UPDATE P SET COLOR = ? WHERE PNAME = ?");
							System.out.print("�����Ŷ�λ��");
							PNAME = sc.next();
							System.out.print("ϣ�������ɫ�޸�Ϊ��");
							COLOR = sc.next();
							pst.setString(1, COLOR);
							pst.setString(2, PNAME);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
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
							Operate();
							pst.close();
							break;
						}
						if(b == 3)
						{
							pst = connect.prepareStatement("UPDATE P SET COLOR = ? WHERE WEIGHT = ?");
							System.out.print("���������λ��");
							WEIGHT = sc.nextFloat();
							System.out.print("ϣ�������ɫ�޸�Ϊ��");
							COLOR = sc.next();
							pst.setString(1, COLOR);
							pst.setFloat(2, WEIGHT);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
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
							Operate();
							pst.close();
							break;
						}
						if(b == 4)
						{
							pst = connect.prepareStatement("UPDATE P SET COLOR = ? WHERE CITY = ?");
							System.out.print("��������ض�λ��");
							CITY = sc.next();
							System.out.print("ϣ�������ɫ�޸�Ϊ��");
							COLOR = sc.next();
							pst.setString(1, COLOR);
							pst.setString(2, CITY);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
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
							Operate();
							pst.close();
							break;
						}
					}
					if(m == 5)
					{
						int b = 0;
						System.out.println("����  1.������  2.�������  3.�������  4.�����ɫ  �����޸�");
						b = sc.nextInt();
						if(b == 1)
						{
							pst = connect.prepareStatement("UPDATE P SET CITY = ? WHERE PNO = ?");
							System.out.print("�����Ŷ�λ��");
							PNO = sc.next();
							System.out.print("ϣ������������޸�Ϊ��");
							CITY = sc.next();
							pst.setString(1, CITY);
							pst.setString(2, PNO);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
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
							Operate();
							pst.close();
							break;
						}
						if(b == 2)
						{
							pst = connect.prepareStatement("UPDATE P SET CITY = ? WHERE PNAME = ?");
							System.out.print("������ƶ�λ��");
							PNAME = sc.next();
							System.out.print("ϣ������������޸�Ϊ��");
							CITY = sc.next();
							pst.setString(1, CITY);
							pst.setString(2, PNAME);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
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
							Operate();
							pst.close();
							break;
						}
						if(b == 3)
						{
							pst = connect.prepareStatement("UPDATE P SET CITY = ? WHERE WEIGHT = ?");
							System.out.print("���������λ��");
							WEIGHT = sc.nextFloat();
							System.out.print("ϣ������������޸�Ϊ��");
							CITY = sc.next();
							pst.setString(1, CITY);
							pst.setFloat(2, WEIGHT);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
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
							Operate();
							pst.close();
							break;
						}
						if(b == 4)
						{
							pst = connect.prepareStatement("UPDATE P SET CITY = ? WHERE COLOR = ?");
							System.out.print("�����ɫ��λ��");
							COLOR = sc.next();
							System.out.print("ϣ������������޸�Ϊ��");
							CITY = sc.next();
							pst.setString(1, CITY);
							pst.setString(2, COLOR);
							pst.executeUpdate();
							System.out.println("�޸ĳɹ����޸ĺ�����£�");
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
							Operate();
							pst.close();
							break;
						}
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("�޸�ʧ�ܣ�2����������һ�㡣");
					updateData();
				}
			}
			case 3:
			{
				try
				{
					int m = 0;
					String SNO,SNAME,CITY;
					System.out.println("������Ҫ���ĵ���  1.��Ӧ�̱��  2.��Ӧ������  3.��Ӧ����������");
					m = sc.nextInt();
					if(m == 1)
					{
						int b = 0;
						System.out.println("����  1.��Ӧ������  2.��Ӧ����������  �����޸�");
						b = sc.nextInt();
						if(b == 1)
						{
							pst = connect.prepareStatement("UPDATE S SET SNO = ? WHERE SNAME = ?");
							System.out.print("��Ӧ�����ƶ�λ��");
							SNAME = sc.next();
							System.out.print("ϣ����Ӧ�̱���޸�Ϊ��");
							SNO = sc.next();
							pst.setString(1, SNO);
							pst.setString(2, SNAME);
							pst.executeUpdate();
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
							Operate();
							break;
						}
						if(b == 2)
						{
							pst = connect.prepareStatement("UPDATE S SET SNO = ? WHERE CITY = ?");
							System.out.print("��Ӧ���������ж�λ��");
							CITY = sc.next();
							System.out.print("ϣ����Ӧ�̱���޸�Ϊ��");
							SNO = sc.next();
							pst.setString(1, SNO);
							pst.setString(2, CITY);
							pst.executeUpdate();
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
							Operate();
							break;
						}
					}
					if(m == 2)
					{
						int b = 0;
						System.out.println("����  1.��Ӧ�̱��  2.��Ӧ����������  �����޸�");
						b = sc.nextInt();
						if(b == 1)
						{
							pst = connect.prepareStatement("UPDATE S SET SNAME = ? WHERE SNO = ?");
							System.out.print("��Ӧ�̱�Ŷ�λ��");
							SNO = sc.next();
							System.out.print("ϣ����Ӧ�������޸�Ϊ��");
							SNAME = sc.next();
							pst.setString(1, SNAME);
							pst.setString(2, SNO);
							pst.executeUpdate();
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
							Operate();
							break;
						}
						if(b == 2)
						{
							pst = connect.prepareStatement("UPDATE S SET SNAME = ? WHERE CITY = ?");
							System.out.print("��Ӧ���������ж�λ��");
							CITY = sc.next();
							System.out.print("ϣ����Ӧ�������޸�Ϊ��");
							SNAME = sc.next();
							pst.setString(1, SNAME);
							pst.setString(2, CITY);
							pst.executeUpdate();
							Statement statement = connect.createStatement();
							ResultSet result = statement.executeQuery("SELECT * FROM S");
							System.out.println("---------------------------");
							System.out.println("SNO  SNAME  CITY STATUS ");
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
							Operate();
							break;
						}
					}
					if(m == 3)
					{
						int b = 0;
						System.out.println("����  1.��Ӧ�̱��  2.��Ӧ������  �����޸�");
						b = sc.nextInt();
						if(b == 1)
						{
							pst = connect.prepareStatement("UPDATE S SET CITY = ? WHERE SNO = ?");
							System.out.print("��Ӧ�̱�Ŷ�λ��");
							SNO = sc.next();
							System.out.print("ϣ����Ӧ�����������޸�Ϊ��");
							CITY = sc.next();
							pst.setString(1, CITY);
							pst.setString(2, SNO);
							pst.executeUpdate();
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
							Operate();
							break;
						}
						if(b == 2)
						{
							pst = connect.prepareStatement("UPDATE S SET CITY = ? WHERE SNAME = ?");
							System.out.print("��Ӧ�����ƶ�λ��");
							SNAME = sc.next();
							System.out.print("ϣ����Ӧ�����������޸�Ϊ��");
							CITY = sc.next();
							pst.setString(1, CITY);
							pst.setString(2, SNAME);
							pst.executeUpdate();
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
							Operate();
							break;
						}
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("�޸�ʧ�ܣ�3����������һ�㡣");
					updateData();
				}
			}
			}
			connect.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("�޸�ʧ�ܣ�0����������һ�㡣");
			Operate();
		}
	}
}