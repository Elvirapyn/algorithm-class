package forth_lab;
import java.util.Scanner;
import java.util.Arrays;

public class interval {

	public static void main(String[] args) {
		int start=-1;
		int end=-1;
		int curTime=0;   //当前时间
		int count=0;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("输入总任务数：");
		int num=scanner.nextInt();		
		Job[] jobs = new Job[num];       //创建的工作
		int[] isExecute = new int[num];  //此工作是否执行的标记
		int i = 0;
		System.out.println("输入每个任务的开始时间和结束时间：");
		while(i<num){
			start = scanner.nextInt();
			end = scanner.nextInt();
			jobs[i]=new Job(start,end);
			i++;
		}
		Arrays.sort(jobs);       //对工作的结束时间进行排序
		for(i=0;i<num;i++)       
			if(curTime < jobs[i].getStart()) {
				count ++;
				isExecute[i]=1;
				curTime = jobs[i].getEnd();
			}
		System.out.println("可执行的任务数为:"+count);
		System.out.print("执行的任务为： ");
		for(i=0;i<num;i++)
			if(isExecute[i]==1) {
				System.out.print("任务"+(i+1)+" ");
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
