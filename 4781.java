import java.util.Scanner;

public class 4781 {
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner (System.in);
		StringBuilder builder = new StringBuilder ("");

		while (true) {
			int N = scanner.nextInt();
			int possible = (int) (scanner.nextDouble() * 100);
			
			if (N == 0 && possible == 0)
				break;
			
			int [] dp = new int [possible+1];
			
			for (int i = 0; i <= possible; i++)
				dp[i] = -1;
			dp[0] = 0;
			
			for (int i = 0; i < N; i++) {
				int cal = scanner.nextInt();
				int price = (int) (scanner.nextDouble() * 100);
				
				for (int j = 0; j <= possible; j++) {
					if (dp[j] != -1 && j+price <= possible)
						dp[j+price] = Math.max(dp[j+price], dp[j]+cal);
				}
			}
			
			int total = 0;
			for (int i = 0; i <= possible; i++)
				total = Math.max(total, dp[i]);
			builder.append(total + "\n");
		}
		
		System.out.println(builder);
	}
}
