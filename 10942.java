import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 10942 {
	
	static int [][] dp;
	static int [] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		StringBuilder builder = new StringBuilder ("");

		int N = Integer.parseInt(br.readLine());
		
		arr = new int [N];
		dp = new int [N][N];
		
		String [] cmds = br.readLine().split(" ");
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(cmds[i]);
			dp[i][i] = 2;
		}
		
		for (int i = 0; i < N-1; i++) {
			for (int j = i; j < N; j++) {
				if (dp[i][j] == 0)
					get_dp (i, j);
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < M; i++) {
			cmds = br.readLine().split(" ");
			int num1 = Integer.parseInt(cmds[0])-1;
			int num2 = Integer.parseInt(cmds[1])-1;
			
			builder.append(dp[num1][num2]-1 + "\n");
		}
		
		System.out.println(builder);
	}
	
	static int get_dp (int i, int j) {
		if (dp[i][j] != 0)
			return dp[i][j];
		
		if (arr[i] != arr[j]) {
			dp[i][j] = 1;
			return 1;
		}
		
		if (j-i == 1) {
			dp[i][j] = 2;
			return 2;
		}
		
		dp[i][j] = get_dp (i+1, j-1);
		return dp[i][j];
	}
}
