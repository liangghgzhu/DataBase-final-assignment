package MDZZ;
import java.util.*;
public class 输出数组最小数 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		int arr[] = {5, 7, 6, 4, 3, 8, 2, 0, 9, 1};
		//Arrays的sort方法：
		Arrays.sort(arr);
		System.out.println(arr[0]);
		//用冒泡排序法：
		
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
