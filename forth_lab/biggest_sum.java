package forth_lab;

public class biggest_sum {
	public static int begin=-1;
	public static int end=-1;
	public static int MaxSum(int a[ ], int left, int right)
	{
		int sum=0;       //本次递归的最小子段和
		int center;      //进行划分时的中心位置
		int leftsum;     //情况1的最大子段和
		int rightsum;    //情况2的最大子段和
		//如果序列长度为1，直接求解
		if (left==right) { 
			if (a[left]>0) 
				sum=a[left];
			else sum=0;
			}
		else {
			center=(left+right)/2;                //计算划分位置
			leftsum=MaxSum(a, left, center);      //情况1，递归求解
			rightsum=MaxSum(a, center+1, right);  //情况2，递归求解	
			//情况3
			int s1=0;       //先求解s1
			int lefts=0;
			for (int i=center; i>=left; i--) {
				lefts+=a[i];
				if (lefts>s1) { 
					s1=lefts;
					begin=i;
				}
			}
			int s2=0;       //再求解s2
			int rights=0; 
			for (int j=center+1; j<=right; j++)
			{ 
				rights+=a[j];
				if (rights>s2) {
					s2=rights;
					end=j;
				}
			}
			sum=s1+s2;   //计算情况3的最大子段和
			if (sum<leftsum) sum=leftsum; 
			//合并，在sum、leftsum和rightsum中取较大者
			if (sum<rightsum) sum=rightsum;
		}
		return sum;
	}
	public static void main(String[] args) {
		int[] array= {-20, 11, -4, 13, -5, -2};
		System.out.println("最大子段和为："+MaxSum(array, 0, 5));
		System.out.print("最大子段为：");
		for(int i=begin;i<=end;i++)
			System.out.print(array[i]+" ");
	}
}
