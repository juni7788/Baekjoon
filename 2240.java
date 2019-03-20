import java.util.Scanner;

public class 2240 {

	static int T, W;
	static int [] arr;
	static int [][][] dp;
	
   public static void main(String args[]) {
	   Scanner scanner = new Scanner (System.in);
	   
	   T = scanner.nextInt();
	   W = scanner.nextInt();
	   arr = new int [T+1];
	   dp  = new int [T+1][2][W+1];

	   int MAX = 0;
	   for (int i = 1; i <= T; i++)
		   arr[i] = scanner.nextInt()-1;

	   get_dp (1);
	   
	   for (int i = 0; i <= W; i++)
		   MAX = Math.max(MAX, Math.max(dp[T][0][i], dp[T][1][i]));
	   
	   System.out.println(MAX);
   }
   
   static void get_dp (int time) {
	   if (time > T)
		   return ;
	   
	   int now = arr[time];
	   dp[time][0][0] = dp[time-1][0][0] + 1 - now;
	   dp[time][1][0] = 0;
	   
	   for (int i = 1; i <= Math.min(time, W); i++) {
		   dp[time][now][i] = Math.max(dp[time-1][now][i], dp[time-1][1-now][i-1]) + 1;
		   dp[time][1-now][i] = Math.max(dp[time-1][now][i-1], dp[time-1][1-now][i]);
	   }
	   
	   get_dp (time+1);
   }
}
