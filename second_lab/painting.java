package second_homework;


public class painting {
	public static int vertex_num = 5;              //������
	public static int color_num=3;                 //��ɫ��
	public static int vertex[]= {0,0,0,0,0,0};       //�����Ϳɫ״̬
	public static int Edge[][]=new int[50][50]; //�����ߵļ���
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
		System.out.print("�ܷ�����Ϊ��"+sum);
	}
	public static void search(int x) { 
		if(x>vertex_num) {
			sum++;
			for(int i=1;i<=vertex_num;i++)
				System.out.print(vertex[i]);
			System.out.println();
		}
		else
			for(int i=1; i<=color_num; i++) { //ÿһ����1-m���������Ϳ
				if(ok(x,i)) { //��x��Ϳ��i����ɫ
					vertex[x]=i;
					search(x+1);
					vertex[x]=0;//����
				}
			}
	}
	public static boolean ok(int v,int c){ 
		for(int k=0;k<vertex_num ;k++)  
		{     
			//���������������������ҪͿ����ɫһ��
			if(Edge[v][k]==1&&vertex[k]==c)      
			{          
				return false;  //���ؼ�
			}   
		}  
		return true;  //������
	}

}
