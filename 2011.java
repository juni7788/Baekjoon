import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 2011 {
	
	static final int DIV = 1000000;
	static long [] dp;
	static int [] pass;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
		
		char [] cmd = br.readLine().toCharArray();
		pass = new int [cmd.length];
		dp = new long [cmd.length];
		
		for (int i = 0; i < cmd.length; i++)
			pass[i] = cmd[i] - '0';
		
		if (pass[0] == 0) {
			System.out.println(0);
			return ;
		}
		if (pass.length == 1) {
			System.out.println(1);
			return ;
		}
		
		dp[0] = 1;
		if (pass[1] == 0) {
			if (pass[0] == 1 || pass[0] == 2)
				dp[1] = 1;
			else {
				System.out.println(0);
				return ;
			}
		}
		else {
			if (check (1))
				dp[1] = 2;
			else
				dp[1] = 1;
		}
		
		for (int i = 2; i < pass.length; i++) {
			if (pass[i] == 0) {
				if (pass[i-1] == 1 || pass[i-1] == 2)
					dp[i] = dp[i-2];
				else {
					System.out.println(0);
					return ;
				}
			}
			else {
				if (check (i)) {
					dp[i] = (dp[i-1] + dp[i-2]) % DIV;
				}
				else
					dp[i] = dp[i-1];
			}
		}
		
		System.out.println(dp[pass.length-1]);
	}
	
	static boolean check (int idx) {
		int num1 = pass[idx-1];
		int num2 = pass[idx];
		int chk = 10*num1 + num2;
		
		if (11 <= chk && chk <= 26)
			return true;
		return false;
	}
}
