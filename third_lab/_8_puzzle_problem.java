package third_lab;
import java.util.Stack;

public class _8_puzzle_problem {
	public static int [][]objective= {{1,2,3},{4,5,6},{7,8,0}};
	public static int[][] init= {{1,0,3},{4,2,6},{7,5,8}};
	public static int depth=0;
	public static Stack<int[][]> stack= new Stack<int[][]>();
	public static Stack<Character> stack_state= new Stack<Character>();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	//将栈中元素倒序排列
	public static Stack reverse(Stack stack_) {
		Stack tempStack=new Stack();
		while(!stack_.empty())
			tempStack.push(stack_.pop());
		return tempStack;	
	}
	//打印移动方向
	public static void print_state(char state) {
		if(state=='U')
			System.out.println("向上移动");
		if(state=='D')
			System.out.println("向下移动");
		if(state=='L')
			System.out.println("向左移动");
		if(state=='R')
			System.out.println("向右移动");
	}
	//深拷贝二维数组的元素
	public static void copy(int[][]matrix1,int [][]matrix2) {
		for(int i=0;i<matrix1.length;i++)
			for(int j=0;j<matrix1.length;j++)
				matrix1[i][j]=matrix2[i][j];
	}
	//判断二维数组是否相等
	public static boolean equals(int [][]matrix1,int [][]matrix2) {
		for(int i=0;i<matrix1.length;i++)
			for(int j=0;j<matrix1.length;j++){
				if(matrix1[i][j]!=matrix2[i][j])
					return false;
			}
		return true;
	}
	
	//计算评估函数
	public static int evaluate(int [][]matrix) {
		int num=0;
		for(int i=0;i<init.length;i++)
			for(int j=0;j<init.length;j++)
				if(matrix[i][j]!=0&&objective[i][j]!=matrix[i][j])
					num++;
		return num+depth;
	}
	//上下左右移动交换元素
	public static void swap(int [][]matrix,int x,int y,char direction) {
		int temp;
		//空格向上移动
		if(direction=='U') {
			temp=matrix[x][y];
			matrix[x][y]=matrix[x-1][y];
			matrix[x-1][y]=temp;

		}
		//空格向左移动
		else if(direction=='L') {
			temp=matrix[x][y];
			matrix[x][y]=matrix[x][y-1];
			matrix[x][y-1]=temp;
		}
		//空格向下移动
		else if(direction=='D') {
			temp=matrix[x][y];
			matrix[x][y]=matrix[x+1][y];
			matrix[x+1][y]=temp;
		}
		//空格向右移动
		else if(direction=='R') {
			temp=matrix[x][y];
			matrix[x][y]=matrix[x][y+1];
			matrix[x][y+1]=temp;
		}

	}
	//输入此时的矩阵和空格的位置，上一次的移动方向
	public static void move(int[][] matrix,int x,int y,char last_move) {
		depth++;
		if(equals(matrix,objective))
		{
			System.out.println("成功");
			throw new RuntimeException();
		}
		if(depth>40) {
			return;
		}
		double e[]= {Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,
				Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY};
		//空格向上移动
		if(x!=0&&last_move!='D') {
			swap(matrix, x, y, 'U');
			e[0]=evaluate(matrix);
			swap(matrix, x, y, 'U');
		}
		//空格向左移动
		if(y!=0&&last_move!='R') {
			swap(matrix, x, y, 'L');
			e[1]=evaluate(matrix);
			swap(matrix, x, y, 'L');
		}
		//空格向下移动
		if(x!=2&&last_move!='U') {
			swap(matrix, x, y, 'D');
			e[2]=evaluate(matrix);
			swap(matrix, x, y, 'D');
		}
		//空格向右移动
		if(y!=2&&last_move!='L') {
			swap(matrix, x, y, 'R');
			e[3]=evaluate(matrix);
			swap(matrix, x, y, 'R');
		}
		
		//选出和目标方阵种不一样数字的个数的最小值
		double least_evaluate=e[0];
		for(int i=0;i<4;i++)
			if(e[i]<=least_evaluate) {
				least_evaluate=e[i];
			}
		
		//标记需要深搜的节点
		int []flag= {-1,-1,-1,-1};
		for(int i=0;i<4;i++)
			if(e[i]==least_evaluate) 
				flag[i]=i;
		
		for(int j=0;j<4;j++) {
			if(flag[j]==0) {
				swap(matrix, x, y, 'U');
				int [][]temp=new int[init.length][init.length];
				copy(temp,matrix);
				stack_state.push('U');
				stack.push(temp);
				move(matrix, x-1, y, 'U');   //深搜
				stack.pop();                 //回溯
				stack_state.pop();
				depth--;
			}
			else if(flag[j]==1) {
				swap(matrix, x, y, 'L');
				int [][]temp=new int[init.length][init.length];
				copy(temp,matrix);
				stack_state.push('L');
				stack.push(temp);
				move(matrix, x, y-1, 'L');    //深搜
				stack.pop();                  //回溯
				stack_state.pop();
				depth--;
			}
			else if(flag[j]==2) {
				swap(matrix, x, y, 'D');
				int [][]temp=new int[init.length][init.length];
				copy(temp,matrix);
				stack_state.push('D');
				stack.push(temp);
				move(matrix, x+1, y, 'D');      //深搜
				stack.pop();                    //回溯
				stack_state.pop();
				depth--;
			}
			else if(flag[j]==3) {
				swap(matrix, x, y, 'R');
				int [][]temp=new int[init.length][init.length];
				copy(temp,matrix);
				stack_state.push('R');
				stack.push(temp);
				move(matrix, x, y+1, 'R');       //深搜
				stack.pop();                    //回溯
				stack_state.pop();
				depth--;
			}
		}
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		int[][] matrix=new int[init.length][init.length];
		int step=1;
		copy(matrix, init);
		try {
			for(int i=0;i<init.length;i++)
				for(int j=0;j<init.length;j++)
					if(init[i][j]==0)
						move(init,i,j,'S');
		}catch (RuntimeException e) {
			//将栈倒序过来
			stack_state=reverse(stack_state);
			stack=reverse(stack);

			//打印初始方阵
			System.out.println("\n初始状态");
			for(int i=0;i<init.length;i++) {
				for(int j=0;j<init.length;j++)
					System.out.print(matrix[i][j]+" ");
				System.out.println();
			}
			System.out.println();

			//打印栈里的东西
			while (!stack.empty()) {
				//打印状态
				System.out.print("第"+step+"步：");
				print_state(stack_state.pop());
				matrix=stack.peek();
				for(int i=0;i<init.length;i++) {
					for(int j=0;j<init.length;j++)
						System.out.print(matrix[i][j]+" ");
					System.out.println();
				}
				step++;
				stack.pop();
				System.out.println();
			}
		}
	}
}
