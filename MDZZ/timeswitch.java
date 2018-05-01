package MDZZ;

import java.util.*;

public class timeswitch {
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print(" ‰»Î√Î ˝£∫");
		//int n = new Scanner(System.in).nextInt();
		int n = scanner.nextInt();
		int a = n / 3600;
		int b = (n % 3600) / 60;
		int c = (n % 3600) / 60;
		System.out.println(n + " secs equals to");
		System.out.println(a + " hours");
		System.out.println(b + " minutes");
		System.out.println(c + " seconds");
	}

}
