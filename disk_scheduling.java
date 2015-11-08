import java.util.*;
import java.io.*;

public class disk_scheduler
{
	static int rnd[];
	static int min,b,k,i = 0;
	static int max;
	static int flag,total_fcfs = 0;
	static int total_sstf,next = 0;
	static int min_sstf = 1000;
	static int some[];
	
	public static void random_gen(int a)
	{	
		
		max = 100;
		Random rand = new Random();
		while(i<a)
		{
			rnd[i] = rand.nextInt((max-min)+1)+min;
			System.out.println(rnd[i]);
			++i;
		}
	}
	
	public static void fcfs(int[] g)
	{
		flag = k;
		int seek_fcfs[] = new int[b];
		for(i = 0;i<b;++i)
		{		
			 if(rnd[i]>flag)
				 seek_fcfs[i] = rnd[i]-flag; 
			 else
			 	seek_fcfs[i] = flag - rnd[i];
			 flag = rnd[i];
		}
		for(i = 0;i<b;++i)
			total_fcfs += seek_fcfs[i];
		System.out.println("FCFS total seek time : "+total_fcfs);
	}
	
	public static void sstf(int[] g)
	{
		int clo = 0;		
		flag = k;
		int seek_sstf[] = new int[b];
		int strt = closest_guy(flag);
		int start = rnd[strt];		
		total_sstf = Math.abs(flag-start);
		for(i = strt;i<b;++i)
		{
			clo = closest_guy(rnd[i]);
			clo = rnd[clo];
			seek_sstf[i] = Math.abs(rnd[i] - clo);
		}
		if(strt != 0)
			{
				for(i = 0;i<strt;++i)
				{
					clo = closest_guy(rnd[i]);
					clo = rnd[clo];
					seek_sstf[i] = Math.abs(rnd[i] - clo);
				}
			}
		for(i=0;i<b;++i)
			total_sstf += seek_sstf[i];
		System.out.println("SSTF total seek time : "+total_sstf);
	}

	public static int closest_guy(int y)
	{
		some = new int[b];
		flag = y;
		for(i = 0;i<b;++i)
			some[i] = Math.abs(flag - rnd[i]);
		for(i = 0;i<b;++i)
		{
			if(min_sstf>some[i])
				min_sstf = some[i];
		}
		for(i = 0;i<b;++i)
		{
			if(min_sstf==some[i])
			{
				next = k;
				return k;			
			}
		}
		return 0;	
	}
	
	public static void main (String [] args)
	{
	
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number of tracks: \n");
		b = in.nextInt();
		rnd = new int[b];		
		System.out.println("Enter the head: \n");
		k = in.nextInt();	
		random_gen(b);
		fcfs(rnd);
		sstf(rnd);
	}
}