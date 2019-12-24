package others;


public class assignment {
	public static int [][]cost_matrix= {{9,2,7,8},{6,4,3,7},{5,8,1,8},{7,6,9,4}};
	public static int []cost_matrix1= {2,3,1,4};
	public static int []state= {-1,-1,-1,-1};
	public static int down_bound=10;
	public static int up_bound=14;

	public static int upbound(int i) {
		int lb=1000;
		int flag=-1;
		for(int j=0;j<4;j++) {
			//此任务没有被选过
			int temp=0;
			if(state[j]==-1) {
				state[j]= i ;
				temp += cost_matrix[i][j];
				for(int k=i+1;k<4;k++) {
					for(int m=0;m<4;m++)
						temp +=cost_matrix1[m];
				}
				state[j]= -1;
			}
			if(temp<lb) {
				lb=temp;
				flag=j;
			}
		}
		state[flag]=i;
		return lb;
	}

	public static void search(int i) {
		int lb=0;
		int j;
		for(j=0;j<4;j++) {
			//此任务没有被选过	
			lb=0;
			while(state[j]!=-1){
				lb += cost_matrix[state[j]][j];
				j++;
			}
			if(state[j]==-1) {
				state[j]= i ;
				lb += cost_matrix[i][j];
				for(int m=0;m<4;m++)
					if(m != i )
						lb +=cost_matrix1[m];
				state[j]= -1;
			}
			if(lb<up_bound&&lb>=down_bound) {
				state[j]=i;
				search(i+1);
			}
		}	
	}
	public static void main(String[] args) {
		search(0);

	}

}
