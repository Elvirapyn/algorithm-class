package second_homework;
import java.util.Scanner;


public class guard {
	static final int MAX = 1000;
	static int d[][] = { {0,0,0}, {0,0,0}, {0,0,-1}, {0,-1,0}, {0,0,1}, {0,1,0} };
	static int [][]x=new int[MAX][MAX];
	static int [][]y=new int[MAX][MAX]; 
	static int [][]bestx=new int[MAX][MAX];   
	static int n, m, best, k = 0, t = 0;   
	static int t1, t2, more;               //�ж��½��֦����������
	boolean p;

	/**
	 * �����������й����⣨���ݷ���
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("����m��n:");
		System.out.print("m: ");
		Scanner sc1=new Scanner(System.in);
		m=Integer.parseInt(sc1.next());
		System.out.print("n: ");
		sc1=new Scanner(System.in);
		n=Integer.parseInt(sc1.next());
	    compute(); //����
	    System.out.println("������Ҫ"+best+"������");
	    for (int i = 1; i <= n; i++) {
	        for (int j = 1; j <= m; j++) 
	        	System.out.print( bestx[i][j]+" ");
	        System.out.println();
	    }

	}
	//��(i, j)������һ�����������ı�����Χ�ܼ�����
	static void change(int i, int j) {    
	    x[i][j] = 1;
	    k++;
	    //���Լ������������������ط������ܿ�
	    for (int r = 1; r <= 5; r++) {   
	        int p = i + d[r][1];
	        int q = j + d[r][2];
	        y[p][q]++;
	        if (y[p][q] == 1)
	            t++;
	    }
	}
	 //������(i, j)�����õľ��������ı�����Χ�ܼ�����
	static void restore(int i, int j) {   
	    x[i][j] = 0;
	    k--;
	    for (int r = 1; r <= 5; r++) {
	        int p = i + d[r][1];
	        int q = j + d[r][2];
	        y[p][q]--;
	        if (y[p][q] == 0)
	            t--;
	    }
	}
	static void search(int i, int j) {   //��������
	    do {                           
	        j++;
	        if (j > m) {
	            i++;
	            j = 1;
	        }
	    } while (!((y[i][j] == 0) || (i > n)));
	    if (i > n) {
	        if (k < best) {            //ˢ�¾���ֵ
	            best = k;
	            for (int p = 1; p <= n; p++)
	                for (int q = 1; q <= m; q++)
	                    bestx[p][q] = x[p][q];
	            return;
	        }
	    }
	    //�������½� = �������õ����پ����� + ���еľ�����
	    if (k + (t1 - t)/5 >= best)    return;    
	    //��������ž�������Ļ����ͼ�ȥ��һ��֦
	    if ((i < n - 1) && (k + (t2 - t)/5 >= best))    return;  
	    if (i < n) {                //���p
	        change(i + 1, j);
	        search(i, j);            //�ݹ�������һ����
	        restore(i + 1,j);        //�ָ�
	    }
	    if (y[i][j + 1] == 0) {        //���q
	        change(i, j);
	        search(i, j);
	        restore(i, j);
	    }
	    if ((j < m) && ((y[i][j + 1] == 0) || (y[i][j + 2] == 0))) {    //���r
	        change(i, j + 1);
	        search(i, j);
	        restore(i, j + 1);
	    }
	}

	static void compute() {
	    more = m/4 + 1;
	    if (m % 4 == 3)
	        more++;
	    else if (m % 4 == 2)
	        more += 2;
	    t2 = m * n + more + 4;
	    t1 = m * n + 4;
	    best = 65536;
	    if (m == 1 && n == 1) {
	    	System.out.println(1);
	    	System.out.println(1);
	    }
	    for (int i = 0; i <= m + 1; i++) {    
	        y[0][i] = 1;
	        y[n + 1][i] = 1;
	    }
	    for (int i = 0; i <= n + 1; i++) {
	        y[i][0] = 1;
	        y[i][m + 1] = 1;
	    }
	    search(1, 0);
	}


}