package forth_lab;

public class biggest_sum {
	public static int begin=-1;
	public static int end=-1;
	public static int MaxSum(int a[ ], int left, int right)
	{
		int sum=0;       //���εݹ����С�Ӷκ�
		int center;      //���л���ʱ������λ��
		int leftsum;     //���1������Ӷκ�
		int rightsum;    //���2������Ӷκ�
		//������г���Ϊ1��ֱ�����
		if (left==right) { 
			if (a[left]>0) 
				sum=a[left];
			else sum=0;
			}
		else {
			center=(left+right)/2;                //���㻮��λ��
			leftsum=MaxSum(a, left, center);      //���1���ݹ����
			rightsum=MaxSum(a, center+1, right);  //���2���ݹ����	
			//���3
			int s1=0;       //�����s1
			int lefts=0;
			for (int i=center; i>=left; i--) {
				lefts+=a[i];
				if (lefts>s1) { 
					s1=lefts;
					begin=i;
				}
			}
			int s2=0;       //�����s2
			int rights=0; 
			for (int j=center+1; j<=right; j++)
			{ 
				rights+=a[j];
				if (rights>s2) {
					s2=rights;
					end=j;
				}
			}
			sum=s1+s2;   //�������3������Ӷκ�
			if (sum<leftsum) sum=leftsum; 
			//�ϲ�����sum��leftsum��rightsum��ȡ�ϴ���
			if (sum<rightsum) sum=rightsum;
		}
		return sum;
	}
	public static void main(String[] args) {
		int[] array= {-20, 11, -4, 13, -5, -2};
		System.out.println("����Ӷκ�Ϊ��"+MaxSum(array, 0, 5));
		System.out.print("����Ӷ�Ϊ��");
		for(int i=begin;i<=end;i++)
			System.out.print(array[i]+" ");
	}
}
