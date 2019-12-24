package forth_lab;
import java.util.Scanner;

public class element_select {
	private static int count;
	private static void QuickSort(int[] num, int left, int right,int index[]) {		
		//���left����right��������ֻ��һ��Ԫ�أ�ֱ�ӷ���		
		if(left>=right) {			
			return;		
		}		
		//���õ�һ����Ԫ��Ϊ����		
		int key=num[left];		
		//�����б�keyС�ķ�����ߣ���key��ķ����ұߣ�keyֵ�±�Ϊi		
		int i=left;		
		int j=right;	
		while(i<j){			
			//j�����ƣ�ֱ��������keyС��ֵ		
			while(num[j]>=key && i<j){			
				j--;		
			}			
			//i�����ƣ�ֱ��������key���ֵ		
			while(num[i]<=key && i<j){			
				i++;		
			}			
			//i��jָ���Ԫ�ؽ���			
			if(i<j){			
				int temp=num[i];
				int temp_index =index[i];
				num[i]=num[j];			
				index[i]=index[j];
				num[j]=temp;	
				index[j]=temp_index;
			}	
		}		
		num[left]=num[i];		
		num[i]=key;		
		count++;	
		QuickSort(num,left,i-1,index);	
		QuickSort(num,i+1,right,index);	
	}

	public static void main(String[] args) {
		int length=0;    //�����С
		Scanner scanner=new Scanner(System.in);
		System.out.println("���������С��");
		length=scanner.nextInt();
		int[] num = new int[length];    //��ʼ����
		int[] index = new int[length];  //��ʼ���е�λ�ñ�ʶ
		for(int i=0;i<length;i++)
			index[i]=i;
		System.out.println("�������飺");
		for(int i=0;i<length;i++)
			num[i]=scanner.nextInt();
		System.out.println("����k��ֵ��");	
		int k=scanner.nextInt();        

		System.out.println();		
		QuickSort(num,0,num.length-1,index);	
		System.out.println("��k�����Ϊ��"+num[k-1]);	
		System.out.println("λ��Ϊ��"+(index[k-1]+1));		
	}
}
