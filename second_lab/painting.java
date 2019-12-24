package second_homework;


public class painting {
	public static int vertex_num = 5;              //顶点数
	public static int color_num=3;                 //颜色数
	public static int vertex[]= {0,0,0,0,0,0};       //顶点的涂色状态
	public static int Edge[][]=new int[50][50]; //创建边的集合
	public static int sum=0;
	public static void main(String[] args) {
		Edge[1][2]= Edge[2][1]=1;
		Edge[1][3]= Edge[3][1]=1;
		Edge[2][3]= Edge[3][2]=1;
		Edge[2][4]= Edge[4][2]=1;
		Edge[2][5]= Edge[5][2]=1;
		Edge[3][5]= Edge[5][3]=1;
		Edge[4][5]= Edge[5][4]=1;
		search(1);
		System.out.print("总方案数为："+sum);
	}
	public static void search(int x) { 
		if(x>vertex_num) {
			sum++;
			for(int i=1;i<=vertex_num;i++)
				System.out.print(vertex[i]);
			System.out.println();
		}
		else
			for(int i=1; i<=color_num; i++) { //每一个有1-m种情况可以涂
				if(ok(x,i)) { //第x个涂第i种颜色
					vertex[x]=i;
					search(x+1);
					vertex[x]=0;//回溯
				}
			}
	}
	public static boolean ok(int v,int c){ 
		for(int k=0;k<vertex_num ;k++)  
		{     
			//如果与它相连并且与它将要涂的颜色一样
			if(Edge[v][k]==1&&vertex[k]==c)      
			{          
				return false;  //返回假
			}   
		}  
		return true;  //否则真
	}

}
