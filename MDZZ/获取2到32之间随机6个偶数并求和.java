package MDZZ;
import java.util.Random;
public class ��ȡ2��32֮�����6��ż������� {
	public String toString()
	{
		return getClass().getName();
	}
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
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
		System.out.println("6�������֮��Ϊ��" + b);
		
		
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
		System.out.println("6�������֮��Ϊ��" + c);
		System.out.println(new ��ȡ2��32֮�����6��ż�������());//����new�������ܲ�������������������������
	}

}
