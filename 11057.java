import java.util.Scanner;

public class 11057 {
	
	static long [][] dp = new long [2000][2000];

	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		int N = scanner.nextInt();
		
		System.out.println(comb(9+N, 9));
	}
	
	static long comb (int n, int r) {
		if (n == r || r == 0)
			return 1;
		
		if (dp[n][r] != 0)
			return dp[n][r]%10007;
		
		dp[n][r] = (comb(n-1, r) + comb(n-1, r-1))%10007;
		
		return dp[n][r];
	}
}	
