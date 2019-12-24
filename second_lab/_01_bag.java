package second_homework;

public class _01_bag {
	private static int obj_num =3;
	private static int w[]= {20, 15, 10};      //ÿ�����������
	private static int v[]= {20, 30, 25};  //ÿ������ļ�ֵ
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
		//�ݹ����������
		//�����е����
		if(i>obj_num-1) {
			printResult();
			System.out.println();
			return;
		}
		//�ȼ��뵱ǰ��Ʒ
		temp_weight += w[i];
		state[i]=1;
		//����������Ʒ��û�г�������Խ�����������������
		if(temp_weight<=limited_weight) {
			backtrack(i+1);}
		//��������������������൱�ڼ�֦����
		state[i]=0;
		temp_weight -= w[i];
		backtrack(i+1);
	}
	public static void main(String[] args) {
		System.out.println("���п��ܵ����Ϊ��");
		System.out.println("״̬\t������\t�ܼ�ֵ");
		backtrack(0);
		System.out.print("����ֵ�����Ϊ��");
		for(int i=0;i<obj_num;i++)
			if(max_state[i]==1)
				System.out.print("��Ʒ"+(i+1)+"  ");
		System.out.println("\n������Ϊ��"+max_value);

	}

}
