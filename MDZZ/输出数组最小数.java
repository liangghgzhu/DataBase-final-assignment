package MDZZ;
import java.util.*;
public class ���������С�� {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		int arr[] = {5, 7, 6, 4, 3, 8, 2, 0, 9, 1};
		//Arrays��sort������
		Arrays.sort(arr);
		System.out.println(arr[0]);
		//��ð�����򷨣�
		
			int i = 1;
			int j = 0;
			for(; i < arr.length; i++)
			{
				for(; j < arr.length - i; j++)
				{
					if(arr[j] > arr[j+1])
					{
						int temp = arr[j];
						arr[j] = arr[j+1];
						arr[j+1] = temp;
					}
				}
			}
			System.out.println(arr[0]);
		}
	

}
