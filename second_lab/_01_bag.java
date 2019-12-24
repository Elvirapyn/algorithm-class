package second_homework;

public class _01_bag {
	private static int obj_num =3;
	private static int w[]= {20, 15, 10};      //每个物体的重量
	private static int v[]= {20, 30, 25};  //每个物体的价值
	private static int state[]= {0,0,0};
	private static int max_state[]= {0,0,0};
	private static int temp_weight =0;
	static private int limited_weight=25;
	static private int total_value =0;
	static private int max_value =0;
	static void printResult() {
		for(int i=0;i<obj_num;i++)
			System.out.print(state[i]);
		System.out.print("\t"+temp_weight);
		for(int i=0;i<obj_num;i++)
			if(state[i]==1)
				total_value+=v[i];
		System.out.print("\t"+total_value);
		if(total_value>max_value) {
			max_value = total_value;
			for(int i=0;i<obj_num;i++)
				max_state[i]=state[i];
		}
		total_value = 0;
	}
	static void backtrack(int i) {
		//递归结束的条件
		//不可行的情况
		if(i>obj_num-1) {
			printResult();
			System.out.println();
			return;
		}
		//先加入当前物品
		temp_weight += w[i];
		state[i]=1;
		//如果加入该物品后没有超重则可以进入左子树继续搜索
		if(temp_weight<=limited_weight) {
			backtrack(i+1);}
		//否则进入右子树，这是相当于剪枝操作
		state[i]=0;
		temp_weight -= w[i];
		backtrack(i+1);
	}
	public static void main(String[] args) {
		System.out.println("所有可能的情况为：");
		System.out.println("状态\t总重量\t总价值");
		backtrack(0);
		System.out.print("最大价值的组合为：");
		for(int i=0;i<obj_num;i++)
			if(max_state[i]==1)
				System.out.print("物品"+(i+1)+"  ");
		System.out.println("\n总重量为："+max_value);

	}

}
