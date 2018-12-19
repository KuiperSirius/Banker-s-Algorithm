import java.util.Scanner;

public class BankManager {
	public static void main(String[] args){
		Scanner scanner=new Scanner(System.in);
		TheBanker tb = new TheBanker();
		tb.deadlockAvoidance();//死锁避免
		int gate = 1;
		while(gate!=0){ 
			tb.deadlockDetection();//死锁检测
			System.out.println("如果您要继续分配资源请输入\"1\"，退出请输入\"0\"");
			System.out.print("您输入的值为：");
			gate = scanner.nextInt();
			System.out.println();
		}
		System.out.println("使用愉快！期待您下次使用！");
	}

}
