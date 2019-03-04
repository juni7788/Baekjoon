import java.util.Scanner;

public class 15954 {
	
	static int [] arr;
	static int [][] dp;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		arr = new int [N];
		dp  = new int [N][N];
		
		for (int i = 0; i < N; i++) {
			int num = scanner.nextInt();
			arr[i] = num;
			dp[0][i] = num;
		}
		
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dp[i][j] = dp[i-1][j];
				if (j >= i)
					dp[i][j] += arr[j-i];
			}
		}
		
		double answer = 987654321;
		for (int i = M; i <= N; i++) {
			for (int j = i-1; j < N; j++) {
				double var = 0;
				double avg = (double)dp[i-1][j]/i;
				for (int k = j-i+1; k <= j; k++)
					var += (arr[k]-avg)*(arr[k]-avg);
				answer = Math.min(answer, Math.sqrt(var/i));
			}
		}
		
		System.out.println(answer);
		scanner.close();
	}
}
