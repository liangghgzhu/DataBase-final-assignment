package MDZZ;

public class 正则表达式判断手机号是否合法 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		String standard = "\\d{11}";
		String text = "13512345678";
		if(text.matches(standard))
		{
			System.out.println(text + "是一个合法手机号。");
		}
		else
		{
			System.out.println(text + "不是一个合法的手机号。");
		}
	}

}
