package forth_lab;
import java.util.Scanner;

public class element_select {
	private static int count;
	private static void QuickSort(int[] num, int left, int right,int index[]) {		
		//如果left等于right，即数组只有一个元素，直接返回		
		if(left>=right) {			
			return;		
		}		
		//设置第一个的元素为枢轴		
		int key=num[left];		
		//数组中比key小的放在左边，比key大的放在右边，key值下标为i		
		int i=left;		
		int j=right;	
		while(i<j){			
			//j向左移，直到遇到比key小的值		
			while(num[j]>=key && i<j){			
				j--;		
			}			
			//i向右移，直到遇到比key大的值		
			while(num[i]<=key && i<j){			
				i++;		
			}			
			//i和j指向的元素交换			
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
		int length=0;    //数组大小
		Scanner scanner=new Scanner(System.in);
		System.out.println("输入数组大小：");
		length=scanner.nextInt();
		int[] num = new int[length];    //初始数列
		int[] index = new int[length];  //初始数列的位置标识
		for(int i=0;i<length;i++)
			index[i]=i;
		System.out.println("输入数组：");
		for(int i=0;i<length;i++)
			num[i]=scanner.nextInt();
		System.out.println("输入k的值：");	
		int k=scanner.nextInt();        

		System.out.println();		
		QuickSort(num,0,num.length-1,index);	
		System.out.println("第k大的数为："+num[k-1]);	
		System.out.println("位置为："+(index[k-1]+1));		
	}
}
