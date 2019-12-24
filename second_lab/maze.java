package second_homework;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

public class maze {
	public static Stack<Position> stack=new Stack<Position>();
	public static Stack<Position> stack2=new Stack<Position>();
	//�Թ���	ͼ,1����ǽ�ڣ�0����ͨ·
	public static char[][] map = {                           
			{'1','1','1','1','1','1','1','1','1','1'},
			{'1','0','0','1','0','0','0','1','0','1'},
			{'1','0','0','1','0','0','0','1','0','1'},
			{'1','0','0','0','0','1','1','0','0','1'},
			{'1','0','1','1','1','0','0','0','0','1'},
			{'1','0','0','0','1','0','0','0','0','1'},
			{'1','0','1','0','0','0','1','0','0','1'},
			{'1','0','1','1','1','0','1','1','0','1'},
			{'1','1','0','0','0','0','0','0','0','1'},
			{'1','1','1','1','1','1','1','1','1','1'}
	};
	public static void main(String[] args) {	
		stack.push(new Position(1, 1));  //�������
		map[1][1]='*';
		try {
			move(1, 1);
        } catch (RuntimeException e) {
            while(!stack.empty()) {
            	Position temp = stack.peek();
            	stack2.push(temp);
            	stack.pop();
            } 
            System.out.println("·��Ϊ��");
            System.out.print("("+stack2.peek().x+","+stack2.peek().y+")");
            stack2.pop();
            int num=1;
            while(!stack2.empty()) {
                System.out.print("->"+"("+stack2.peek().x+","+stack2.peek().y+")");
                num++;
                if(num%5==0)
                	System.out.println();
            	stack2.pop();
            } 
            System.out.println();
            System.out.println("��ͼ�б�ʾ·����* �����߹���·");
            for(int i=0;i<10;i++) {
            	for(int j=0;j<10;j++)
            		 System.out.print(map[i][j]);
            	 System.out.println();
            
            }
        }	
	}

	//�ƶ�����
	public static void move(int x,int y) {
		 // �������еݹ�
		if(x==8&&y==8) 
            throw new RuntimeException();

		//�����ƶ�
		if(map[x+1][y]!='1'&&map[x+1][y]!='*') {
			stack.push(new Position(x+1,y));
			map[x+1][y]='*';
			move(x+1, y);
			map[x+1][y]='0';
			stack.pop();
		}
		//�����ƶ�
		if(map[x][y+1]!='1'&&map[x][y+1]!='*') {
			stack.push(new Position(x,y+1));
			map[x][y+1]='*';
			move(x, y+1);
			map[x][y+1]='0';
			stack.pop();
		}
		//�����ƶ�
		if(map[x-1][y]!='1'&&map[x-1][y]!='*') {
			stack.push(new Position(x-1,y));
			map[x-1][y]='*';
			move(x-1, y);
			map[x-1][y]='0';
			stack.pop();
		}

		//�����ƶ�
		if(map[x][y-1]!='1'&&map[x][y-1]!='*') {
			stack.push(new Position(x,y-1));
			map[x][y-1]='*';
			move(x, y-1);
			map[x][y-1]='0';
			stack.pop();
		}
	}
}
//��ʾ�������
class Position{
	int x;
	int y;
	public Position(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}