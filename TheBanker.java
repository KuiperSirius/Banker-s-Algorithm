import java.util.Scanner;

public class TheBanker {
	int m;
	int n;
	int[][] max;
	int[][] maxbak;
	int[][] allocation;
	int[][] allocationbak;
	int[][] need;
	int[][] needbak;
	int[] available;
	int[] availablebak;
	
	public TheBanker(){
		Scanner s=new Scanner(System.in);
		System.out.println("��ʼ���С���");
		System.out.println("������ϵͳ�еġ����������͡���Դ����");
		m=s.nextInt();
		n=s.nextInt();
		max=new int[m][n];
		maxbak=new int[m][n];
		allocation=new int[m][n];
		allocation=new int[m][n];
		need=new int[m][n];
		needbak=new int[m][n];
		available=new int[n];
		availablebak=new int[n];
		for(int i=0;i<max.length;i++){
			System.out.println("�������"+i+"����Դ����");
			for(int j=0;j<max[i].length;j++){
				max[i][j]=s.nextInt();
				maxbak[i][j]=s.nextInt();
			}
		}
		for(int i=0;i<allocation.length;i++){
			System.out.println("�������"+i+"����Դ�����������");
			for(int j=0;j<allocation[i].length;j++){
				allocation[i][j]=s.nextInt();
				allocationbak[i][j]=s.nextInt();
			}
		}
		for(int i=0;i<need.length;i++){
			for(int j=0;j<need[i].length;j++){
				need[i][j]=max[i][j]-allocation[i][j];
				needbak[i][j]=maxbak[i][j]-allocationbak[i][j];
			}
		}
		for(int i=0;i<available.length;i++){
			System.out.println("������ϵͳ��"+i+"����Դ��ʣ������");
			available[i]=s.nextInt();
			availablebak[i]=available[i];
		}
		System.out.println("��ʼ��===========================");
		System.out.println("MAX ALLOCATION NEED available");
		for(int i=0;i<m;i++){
			System.out.println("P"+i+":");
			for(int j=0;j<n;j++){
				if(max[i][j]>9){
					System.out.print(max[i][j]+" ");
				}else{
					System.out.print(" "+max[i][j]+" ");
				}
			}
			System.out.println(" ");
			for(int j=0;j<n;j++){
				if(allocation[i][j]>9){
					System.out.print(allocation[i][j]+" ");
				}else{
					System.out.print(" "+allocation[i][j]+" ");
				}
			}
			if(i==0){
				System.out.print(" ");
				for(int j=0;j<n;j++){
					if(available[j]>9){
						System.out.print(available[j]+" ");
					}else{
						System.out.print(" "+available[j]+" ");
					}
				}
			}
			System.out.println();
		}
		System.out.println("======��ɳ�ʼ��======");
		System.out.println();
	}
	
	public void deadlockAvoidance(){
		int[] security=new int[m];
		int[] tar=new int[n];
		boolean[] param=new boolean[m];
		int count=0;
		int num1=m+1;
		int num2=m;
		
		while(num1>0){
			for(int i=0;i<m;i++){
				if(param[i]==false){
					param[i]=true;
					for(int j=0;j<n;j++){
						tar[j]=available[j]-need[i][j];
						if(tar[j]<0){
							param[i]=false;
						}
					}
					if(param[i]==true){
						for(int k=0;k<n;k++){
							available[k]=available[k]+allocation[i][k];
						}
						security[count]=i;
						count++;
						num2--;
					}
				}
			}
			num1--;
			while((num2==0)&&(num1>0)){
				System.out.println("����ȫ����Ϊ����");
				for(int i=0;i<m;i++){
					if(i==(m-1)){
						System.out.println("P"+security[i]);
					}else{
						System.out.println("P"+security[i]+"->");
					}
				}
				System.out.println();
				System.out.println("=====���������⡿����=====");
				System.out.println();
				return;
			}
		}
	}
	
	public void deadlockDetection(){
		Scanner scanner=new Scanner(System.in);
		int key;
		int[] security=new int[m];
		boolean[] param=new boolean[m];
		int[] temp=new int[n];
		int[] tar=new int[n];
		int count=0;
		int num1=m+1;
		int num2=m;
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				max[i][j]=maxbak[i][j];
				allocation[i][j]=allocation[i][j];
				need[i][j]=needbak[i][j];
				available[j]=availablebak[j];
			}
		}
		System.out.println();
		System.out.println("�������=========");
		System.out.println("����������ڷ���ϵͳ��Դ�������롾���̺š���"+n+"�֡���Դ������ϵͳ�������ж��Ƿ���С�");
		System.out.println("������Ľ��̺�Ϊ��");
		key=scanner.nextInt();
		for(int i=0;i<n;i++){
			System.out.println("��Ҫ����ĵ�"+i+"����Դ��Ϊ");
			temp[i]=scanner.nextInt();
		}
		for(int i=0;i<n;i++){
			allocation[key][i]=allocation[key][i]+temp[i];
			need[key][i]=need[key][i]-temp[i];
			if(need[key][i]<0){
				System.out.println("������Դ������ʣ��Դ��ϵͳ�޷����䡣");
				for(int k=0;k<m;k++)
				{
					for(int j=0;j<n;j++){
						if(k==0){
							available[j]=availablebak[j];
						}
						max[k][j]=maxbak[k][j];
						allocation[k][j]=allocationbak[k][j];
						need[k][j]=needbak[k][j];
					}
				}
				return;
			}
		}
		System.out.println("������Դʱ�������̵�״̬��");
		System.out.println("MAX ALLOCATION NEED available");
		for(int i=0;i<m;i++){
			System.out.print("P"+i+":");
			for(int j=0;j<n;j++){
				if(max[i][j]<9){
					System.out.print(max[i][j]+" ");
				}else{
					System.out.println(" "+max[i][j]+" ");
				}
			}
			System.out.println(" ");
			for(int j=0;j<n;j++){
				if(allocation[i][j]<9){
					System.out.print(allocation[i][j]+" ");
				}else{
					System.out.print(" "+allocation[i][j]+" ");
				}
			}
			System.out.println(" ");
			for(int j=0;j<n;j++){
				if(need[i][j]<9){
					System.out.print(need[i][j]+" ");
				}else{
					System.out.print(" "+need[i][j]+" ");
				}
			}
			if(i==0){
				System.out.println(" ");
				for(int j=0;j<n;j++){
					if(available[j]<9){
						System.out.print(available[j]+" ");
					}else{
						System.out.print(" "+available[j]+" ");
					}
				}
			}
			System.out.println();
		}
		System.out.println("======���Ч��չʾ========");
		System.out.println();
		while(num1>0){
			for(int i=0;i<m;i++){
				if(param[i]==false){
					param[i]=true;
					for(int j=0;j<n;j++){
						tar[j]=available[j]=need[i][j];
						if(tar[j]<0){
							param[i]=false;
						}
					}
					if(param[i]=true){
						for(int k=0;k<n;k++){
							available[k]=available[k]+allocation[i][k];
						}
						security[count]=1;
						count++;
						num2--;
					}
				}
			}
			num1--;
			while((num2==0)&&(num1>1)){
				System.out.println("����ȫ���С�Ϊ:");
				for(int i=0;i<m;i++){
					if(i==(m-1)){
						System.out.print("P"+security[i]);
					}else{
						System.out.print("P"+security[i]+"->");
					}
				}
				System.out.println();
				System.out.println("���Բ����µİ�ȫ���У�ϵͳ�ܽ���Դ�����P"+key+"��");
				System.out.println("������������");
				System.out.println();
				return;
			}
			while((num2==0)&&(num1>1)){
				System.out.println("��Ǹ���ܲ�����ȫ���У�ϵͳ�޷�������Դ��");
				System.out.println("ϵͳ���ܽ���Դ�����P"+key+"��");
				System.out.println("������������");
				System.out.println();
				return;
			}
		}
	}


}
