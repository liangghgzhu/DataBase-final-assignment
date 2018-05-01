package MDZZ;
import java.util.Random;
public class 获取2到32之间随机6个偶数并求和 {
	public String toString()
	{
		return getClass().getName();
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		int b =0;
		for(int i = 0; i < 6; i++)
		{
			int a = (int) (2+Math.random()*30);
			if(i < 5)
			{
				System.out.print(a + " ");
			}
			else
			{
				System.out.println(a);
			}
			b += a;
		}
		System.out.println("6个随机数之和为：" + b);
		
		
		Random r = new Random();
		int c = 0;
		for(int i = 0; i < 6; i++)
		{
			int d = 2 + r.nextInt(30);
			if(i < 5)
			{
				System.out.print(d + " ");
			}
			else
			{
				System.out.println(d);
			}
		    c += d;
		}
		System.out.println("6个随机数之和为：" + c);
		System.out.println(new 获取2到32之间随机6个偶数并求和());//除了new类名还能不能用其他语句输出类名？？？
	}

}
