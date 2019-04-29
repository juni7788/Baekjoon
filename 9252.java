import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 9252 {
	
	static StringBuffer sb = new StringBuffer ("");
	static char[] str1, str2;
	static int [][] dp;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		str1 = br.readLine().toCharArray();
		str2 = br.readLine().toCharArray();
		dp = new int [str1.length][str2.length];
		
		for (int i = 0; i < str1.length; i++)
			Arrays.fill(dp[i], -1);
		
		LCS(str1.length-1, str2.length-1);
		
		int x = str1.length-1, y = str2.length-1;
		while (true) {
			if (x < 0 || y < 0)
				break;
			
			if (str1[x] == str2[y]) {
				sb.append(str1[x]);
				x -= 1;
				y -= 1;
				continue;
			}
			
			if (x > 0 && dp[x][y] == dp[x-1][y]) {
				x -= 1;
				continue;
			}
			
			if (y > 0 && dp[x][y] == dp[x][y-1]) {
				y -= 1;
				continue;
			}
			
			break;
		}

		System.out.println(dp[str1.length-1][str2.length-1] + "\n" + sb.reverse());
	}
	
	static int LCS (int i, int j) {
		if (i < 0 || j < 0)
			return 0;
		
		if (dp[i][j] != -1)
			return dp[i][j];
		
		if (str1[i] == str2[j])
			return dp[i][j] = (LCS (i-1, j-1) + 1);
		
		return dp[i][j] = Math.max(LCS (i-1, j), LCS (i, j-1));
	}
}
