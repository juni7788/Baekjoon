import java.util.Scanner;

public class 1932.java {
	
	static int [][] map;
	static int [][] dp;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);

		int N = scanner.nextInt();
		map = new int [N][N];
		dp  = new int [N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= i; j++)
				map[i][j] = scanner.nextInt();
		}
		
		dp[0][0] = map[0][0];
		
		for (int i = 1; i < N; i++) {
			dp[i][0] = map[i][0] + dp[i-1][0];
			dp[i][i] = map[i][i] + dp[i-1][i-1];
			for (int j = 1; j < i; j++)
				dp[i][j] = map[i][j] + Math.max(dp[i-1][j-1], dp[i-1][j]);
		}
		
		int MAX = -1;
		
		for (int i = 0; i < N; i++)
			MAX = Math.max(dp[N-1][i], MAX);
		
		System.out.println(MAX);
	}
}
