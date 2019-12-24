package third_lab;
import java.util.ArrayList;

public class tsp {
	public static double[][] cost_matrix= {
			{Double.POSITIVE_INFINITY,3,1,5,8},
			{3,Double.POSITIVE_INFINITY,6,7,9},
			{1,6,Double.POSITIVE_INFINITY,4,2},
			{5,7,4,Double.POSITIVE_INFINITY,3},
			{8,9,2,3,Double.POSITIVE_INFINITY}};
	public static char[] symbol= {'a','b','c','d','e'};
	public static double least_cost=Double.POSITIVE_INFINITY;
	public static double up_bound=16;
	public static double low_bound=14;
	public static ArrayList<Integer> list=new ArrayList<Integer>();
	public static double[][] candidate_option= {{1,3},{3,6},{1,2},{3,4},{2,3}};
	
	//计算下界的函数
	public static double calculate_lb(int start,int end,double[][]candidate_option) {
		double lowbound=0;
		lowbound = 2*cost_matrix[start][end];
		if(cost_matrix[start][end]>candidate_option[start][0]) {
			lowbound += candidate_option[start][0];
		}
		if(cost_matrix[start][end]==candidate_option[start][0]) {
			lowbound += candidate_option[start][1];
		}
		if(cost_matrix[start][end]>candidate_option[end][0]) {
			lowbound += candidate_option[end][0];
		}
		if(cost_matrix[start][end]==candidate_option[end][0]) {
			lowbound += candidate_option[end][1];
		}
		//加上剩余没有选过的行的最小的两个元素
		for(int i=0;i<cost_matrix.length;i++)
			if(i!=start&&i!=end) {
				lowbound += candidate_option[i][0]+candidate_option[i][1];
			}
		return lowbound/2;
	}
	
	//递归搜索
	public static void search(int root) {
		double lb=Double.POSITIVE_INFINITY;
		list.add(root);
		//遍历没有走过的地点
		for(int i=0;i<cost_matrix.length;i++) {
			if(!list.contains(i)) {
				//取上界
				lb = Math.ceil(calculate_lb(root, i,candidate_option));
				if((lb<up_bound&&lb>=low_bound)||(list.size()==4&&lb==up_bound)) {
					//递归结束条件，当走过5个地点的时候结束递归
					if(list.size()==4) {
						list.add(i);
						least_cost=lb;
						throw new RuntimeException();
					}
					double temp1=Double.POSITIVE_INFINITY,temp2=Double.POSITIVE_INFINITY;
					if(cost_matrix[root][i]>candidate_option[root][0]) { 
						temp1=candidate_option[root][1];
						candidate_option[root][1]=cost_matrix[root][i];
					}
					if(cost_matrix[root][i]>candidate_option[i][0]) {
						temp2=candidate_option[i][1];
						candidate_option[i][1]=cost_matrix[root][i];
					}
					//深搜
					search(i);
					//回溯
					list.remove(Integer.valueOf(i));
					if(temp1!=Double.POSITIVE_INFINITY) 
						candidate_option[root][1]=temp1;
					if(temp2!=Double.POSITIVE_INFINITY) 
						candidate_option[i][1]=temp2;
				}
			}
		}
	}
	public static void main(String[] args) {
		try {
			search(0);
		}catch (RuntimeException e) {
			System.out.println("最终方案为：");
			for (int i = 0; i < list.size(); i++) {
				System.out.print(symbol[list.get(i)]+"->");
			}
			System.out.print(symbol[list.get(0)]);
			System.out.print("\n消费为："+least_cost);
		}
	}
}
//节点
class tsp_node{
	int start;
	int end;
	int lb;
	public tsp_node(int start,int end) {
		this.start=start;
		this.end=end;
	}
	public int getLb() {
		return lb;
	}
	public void setLb(int lb) {
		this.lb = lb;
	}
	public int getStart() {
		return start;
	}
	public int getEnd() {
		return end;
	}
}
