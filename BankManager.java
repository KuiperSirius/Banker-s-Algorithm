import java.util.Scanner;

public class BankManager {
	public static void main(String[] args){
		Scanner scanner=new Scanner(System.in);
		TheBanker tb = new TheBanker();
		tb.deadlockAvoidance();//��������
		int gate = 1;
		while(gate!=0){ 
			tb.deadlockDetection();//�������
			System.out.println("�����Ҫ����������Դ������\"1\"���˳�������\"0\"");
			System.out.print("�������ֵΪ��");
			gate = scanner.nextInt();
			System.out.println();
		}
		System.out.println("ʹ����죡�ڴ����´�ʹ�ã�");
	}

}
