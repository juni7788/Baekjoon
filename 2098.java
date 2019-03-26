import java.util.Scanner;

public class 2098 {
	
	static int N, check, MAX = 987654321;
	static int [][] dp;
	static int [][] map;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		
		N = scanner.nextInt();
		check = (1 << N)-1;
		dp = new int [N][check+1];
		map = new int [N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				map[i][j] = scanner.nextInt();
		} scanner.close();

		System.out.println(TSP (0, 1));
	}
	
	static int TSP (int cur, int visited) {
		if (visited == check) {
			if (map[cur][0] != 0)
				return map[cur][0];
			else
				return MAX;
		}
	
		if (dp[cur][visited] != 0)
			return dp[cur][visited];
		
		int min = MAX;
		
		for (int i = 1; i < N; i++) {
			if (((1<<i) & visited) >= 1 || map[cur][i] == 0)
				continue;

			min = Math.min(min, map[cur][i] + TSP (i, visited|(1<<i)));
		}
		
		return dp[cur][visited] = min;
	}
}
