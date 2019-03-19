import java.util.Scanner;

public class 2169 {
	
	static int [][] move = {{1, 0}, {0, 1}, {0, -1}};
	static int [][] map;
	static int [][][] dp;
	static int N, M, MIN = -987654321;
	
	public static void main(String args[]){
		Scanner scanner = new Scanner (System.in);

		N = scanner.nextInt();
		M = scanner.nextInt();
		map = new int [N][M]; 
		dp  = new int [N][M][3];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = scanner.nextInt();
				dp[i][j][0] = dp[i][j][1] = dp[i][j][2] = MIN;
			}
		}
		
		dp[0][0][0] = dp[0][0][1] = dp[0][0][2] = map[0][0];
		
		System.out.println(Math.max(get_dp(N-1, M-1, 0), get_dp(N-1, M-1, 1)));
	}
	
	static int get_dp (int x, int y, int dir) {
		int nx = x - move[dir][0];
		int ny = y - move[dir][1];
		
		if (dp[x][y][dir] != MIN || (0 > nx || nx >= N || 0 > ny || ny >= M))
			return dp[x][y][dir];
		
		if (dir == 0) {
			dp[x][y][0] = Math.max(get_dp(nx, ny, 0), get_dp(nx, ny, 1));
			dp[x][y][0] = map[x][y] + Math.max(get_dp(nx, ny, 2), dp[x][y][0]);
		}
		else if (dir == 1)
			dp[x][y][1] = map[x][y] + Math.max(get_dp(nx, ny, 0), get_dp(nx, ny, 1));
		else
			dp[x][y][2] = map[x][y] + Math.max(get_dp(nx, ny, 0), get_dp(nx, ny, 2));

		return dp[x][y][dir];
	}
}
