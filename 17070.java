import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 17070 {

	static boolean [][] map;
	static int [][][] dp; // right down dia

	public static void main(String args[]) throws IOException {
	   BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
	   int N = Integer.parseInt(br.readLine());
	   map = new boolean [N][N];
	   dp = new int [N][N][3];

	   for (int i = 0; i < N; i++) {
		   String [] cmds = br.readLine().split(" ");
		   for (int j = 0; j < N; j++)
			   if (Integer.parseInt(cmds[j]) == 0)
				   map[i][j] = true;
			   else
				   map[i][j] = false;
	   }

	   for (int j = 1; j < N; j++) {
		   if (map[0][j])
			   dp[0][j][0] = 1;
		   else
			   break;
	   }
	   
	   for (int i = 1; i < N; i++) {
		   for (int j = 1; j < N; j++) {
			   if (!map[i][j] || (i == 1 && j == 1))
				   continue;
			   if (map[i-1][j] && map[i][j-1])
				   dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
			   dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];
			   dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];
		   }
	   }
	   
	   System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);
   }
}
