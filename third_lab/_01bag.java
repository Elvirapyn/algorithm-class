package third_lab;

public class _01bag {
	static private int limited_weight=10;
	public static int weights[]= {4,7,5,3};      //每个物体的重量
	public static int values[]= {40,42,25,12};  //每个物体的价值
	public static int v_w[]= {0,0,0,0};
	public static int state[]= {0,0,0,0};   //物体的标号
	public static int up_bound=100;
	public static int max_value=0;          //所有子集中总价值最大的价值

	//界限函数
	public static int upbound(Node n,int k) {
		int ub= 0 ;
		for(int i=k;i<weights.length;i++) {
			if((limited_weight-n.getW())*v_w[i]>ub)
				ub=(limited_weight-n.getW())*v_w[i]+n.getV();
		}
		return ub;
	}
	//搜索函数
	public static void search(Node node,int i) {
		if(i==weights.length)   //递归结束条件
			return;
		int ub1,ub2;
		int w= weights[i];
		int v = values[i];
		//如果加入物体i
		Node new_node1 = new Node(node.getW()+w,node.getV()+v);
		ub1 = upbound(new_node1, i+1);
		//如果不加入物体i
		Node new_node2 = new Node(node.getW(),node.getV());
		ub2 = upbound(new_node2, i+1);
		//当两种情况都未将背包装满时
		if(new_node1.getW()<=limited_weight&&new_node2.getW()<=limited_weight) {
			if(ub1>ub2&&ub1<up_bound) {
				new_node1.setUb(ub1);
				state[i]=1;
				max_value=new_node1.getV();
				search(new_node1, i+1);

			}
			else if(ub1<ub2&&ub2<up_bound) {
				new_node2.setUb(ub2);
				max_value=new_node2.getV();
				search(new_node2, i+1);
			}
		}
		//当加入此物品将背包装满时
		else if(new_node1.getW()>limited_weight&&new_node2.getW()<=limited_weight) {
			new_node2.setUb(ub2);
			max_value=new_node2.getV();
			search(new_node2, i+1);
		}
	}

	public static void main(String[] args) {
		
		//计算v/w的值
		for(int i=0;i<weights.length;i++)
			v_w[i]=values[i]/weights[i];

		//计算最小边界，最大边界
		Node node1 = new Node(0,0);
		search(node1, 0);

		//打印结果
		System.out.print("最佳方案为：");
		for(int i=0;i<state.length;i++)
			if(state[i]==1)
				System.out.print("物品"+(i+1)+"  ");
		System.out.println("\n总价值为："+max_value);
	}
}
//节点
class Node{
	private int w;
	private int v;
	private int ub;
	public Node(int w,int v) {
		this.w=w;
		this.v=v;
	}
	public int getW() {
		return w;
	}
	public int getV() {
		return v;
	}
	public int getUb() {
		return ub;
	}
	public void setUb(int ub) {
		this.ub = ub;
	}
}