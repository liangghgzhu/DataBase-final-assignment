package MDZZ;

public class ������ʽ�ж��ֻ����Ƿ�Ϸ� {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		String standard = "\\d{11}";
		String text = "13512345678";
		if(text.matches(standard))
		{
			System.out.println(text + "��һ���Ϸ��ֻ��š�");
		}
		else
		{
			System.out.println(text + "����һ���Ϸ����ֻ��š�");
		}
	}

}
