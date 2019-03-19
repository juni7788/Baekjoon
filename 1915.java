import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 1915 {
	
	static int N, M, MAX = 0;
	static int [][] map;
	static int [][] dp;
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		
		String [] cmds = br.readLine().split(" ");
		N = Integer.parseInt(cmds[0]);
		M = Integer.parseInt(cmds[1]);
		map = new int [N+1][M+1];
		dp = new int [N+1][M+1];
		
		for (int i = 1; i <= N; i++) {
			String cmd = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = cmd.charAt(j-1) - '0';
				if (map[i][j] == 1)
					dp[i][j] = 1;
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j] == 0)
					continue;
				dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
				MAX = Math.max(MAX, dp[i][j]);
			}
		}
		
		System.out.println(MAX*MAX);
	}
}
