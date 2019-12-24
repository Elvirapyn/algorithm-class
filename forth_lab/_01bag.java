package forth_lab;

public class _01bag {
	static private int limited_weight=50;
	public static int weights[]= {20, 30, 10};  //每个物体的重量
	public static int values[]= {60, 120, 50};  //每个物体的价值
	public static int v_w[]= {0,0,0};         //物品单位质量的价值
	public static double result[]= {0,0,0};   //结果
	public static int index[]= {0,1,2};       //物体的标号
	public static int total_value=0;          //总价值
	public static int total_wieght=0;         //当前背包重量
	
	public static void bubbleSort(int[] a){
		boolean needExchange = true;
		for(int i = 0; i < a.length - 1 && needExchange; i++){
			needExchange = false;
			for(int j = 0; j < a.length - i - 1; j++){
				if(a[j] > a[j + 1]){
					int tmp = a[j];
					int tmp_index=index[j];
					a[j] = a[j + 1];
					index[j]=index[j+1];
					a[j + 1] = tmp;
					index[j+1]=tmp_index;
					needExchange = true;
				}
			}
		}
	}

	public static void main(String[] args) {
		int sort_array[]=new int[3];   //排序后的单位价值降序排列的数组
		int temp_weight = 0; 
		int[] temp_index = new int[3]; //排序后的标号

		//计算v/w的值
		for(int i=0;i<weights.length;i++)
			v_w[i]=values[i]/weights[i];

		
		//冒泡排序
		bubbleSort(v_w);

		//将冒泡排序后从小到大排列的序列进行从大到小的反序排列
		for(int i=0;i<weights.length;i++) {
			sort_array[i]=v_w[weights.length-i-1];
			temp_index[i]=index[weights.length-i-1];
		}

		//将物品放入背包
		int j=0;
		double C=limited_weight;
		while (weights[j]<C) {
			result[j]=1;
			C = C- weights[temp_index[j]];
			j++;		
		}
		result[j]=C/weights[temp_index[j]];

		//打印结果
		System.out.print("问题的解为：");
		for(int k=0;k<weights.length;k++)
			System.out.print(result[k]+"   ");
		System.out.print("\n放入的物品为：");
		for(int k=0;k<weights.length;k++)
			System.out.print(result[k]+"物品"+(temp_index[k]+1)+"  ");
		for(int k=0;k<weights.length;k++) {
			total_wieght+=weights[temp_index[k]]*(int)result[k];
			total_value+=values[temp_index[k]]*(int)result[k];
		}
		System.out.println("\n总重量为："+total_wieght);
		System.out.println("总价值为："+total_value);
		System.exit(0);
	}
}
