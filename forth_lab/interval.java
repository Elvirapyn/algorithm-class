package forth_lab;
import java.util.Scanner;
import java.util.Arrays;

public class interval {

	public static void main(String[] args) {
		int start=-1;
		int end=-1;
		int curTime=0;   //��ǰʱ��
		int count=0;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("��������������");
		int num=scanner.nextInt();		
		Job[] jobs = new Job[num];       //�����Ĺ���
		int[] isExecute = new int[num];  //�˹����Ƿ�ִ�еı��
		int i = 0;
		System.out.println("����ÿ������Ŀ�ʼʱ��ͽ���ʱ�䣺");
		while(i<num){
			start = scanner.nextInt();
			end = scanner.nextInt();
			jobs[i]=new Job(start,end);
			i++;
		}
		Arrays.sort(jobs);       //�Թ����Ľ���ʱ���������
		for(i=0;i<num;i++)       
			if(curTime < jobs[i].getStart()) {
				count ++;
				isExecute[i]=1;
				curTime = jobs[i].getEnd();
			}
		System.out.println("��ִ�е�������Ϊ:"+count);
		System.out.print("ִ�е�����Ϊ�� ");
		for(i=0;i<num;i++)
			if(isExecute[i]==1) {
				System.out.print("����"+(i+1)+" ");
			}
	}
}
class Job implements Comparable{	
	private int start;	
	private int end; 
	public Job(int start, int end) {	
		this.start = start;	
		this.end = end;	
	} 	
	public int getStart() {	
		return start;	
	} 
	public int getEnd() {	
		return end;
	} 
	@Override	
	public int compareTo(Object o) {	
		Job job = (Job) o;		
		if (this.end > job.getEnd())	
			return 1;		
		else if (this.end == job.getEnd())		
			return 0;		
		else			
			return -1;	
	}
}
