package MDZZ;

import java.sql.SQLException;
import java.util.Scanner;

public class mainInterface 
{
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws SQLException 
	{
		// TODO �Զ����ɵķ������
		sp sp1 = new sp();
		sp1.login();
	}
}

