package forth_lab;

public class _01bag {
	static private int limited_weight=50;
	public static int weights[]= {20, 30, 10};  //ÿ�����������
	public static int values[]= {60, 120, 50};  //ÿ������ļ�ֵ
	public static int v_w[]= {0,0,0};         //��Ʒ��λ�����ļ�ֵ
	public static double result[]= {0,0,0};   //���
	public static int index[]= {0,1,2};       //����ı��
	public static int total_value=0;          //�ܼ�ֵ
	public static int total_wieght=0;         //��ǰ��������
	
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
		int sort_array[]=new int[3];   //�����ĵ�λ��ֵ�������е�����
		int temp_weight = 0; 
		int[] temp_index = new int[3]; //�����ı��

		//����v/w��ֵ
		for(int i=0;i<weights.length;i++)
			v_w[i]=values[i]/weights[i];

		
		//ð������
		bubbleSort(v_w);

		//��ð��������С�������е����н��дӴ�С�ķ�������
		for(int i=0;i<weights.length;i++) {
			sort_array[i]=v_w[weights.length-i-1];
			temp_index[i]=index[weights.length-i-1];
		}

		//����Ʒ���뱳��
		int j=0;
		double C=limited_weight;
		while (weights[j]<C) {
			result[j]=1;
			C = C- weights[temp_index[j]];
			j++;		
		}
		result[j]=C/weights[temp_index[j]];

		//��ӡ���
		System.out.print("����Ľ�Ϊ��");
		for(int k=0;k<weights.length;k++)
			System.out.print(result[k]+"   ");
		System.out.print("\n�������ƷΪ��");
		for(int k=0;k<weights.length;k++)
			System.out.print(result[k]+"��Ʒ"+(temp_index[k]+1)+"  ");
		for(int k=0;k<weights.length;k++) {
			total_wieght+=weights[temp_index[k]]*(int)result[k];
			total_value+=values[temp_index[k]]*(int)result[k];
		}
		System.out.println("\n������Ϊ��"+total_wieght);
		System.out.println("�ܼ�ֵΪ��"+total_value);
		System.exit(0);
	}
}
