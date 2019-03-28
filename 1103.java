import java.util.Scanner;

public class 1103 {
	
	static int N, M, answer, hole = 10;
	static boolean inf;
	static int [][] map, dp;
	static boolean [][] visited;
	static int [][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		
		N = scanner.nextInt();
		M = scanner.nextInt();
		map = new int [N][M];
		dp = new int [N][M];
		visited = new boolean [N][M];
		
		scanner.nextLine();
		for (int i = 0; i < N; i++) {
			String cmds = scanner.nextLine();
			for (int j = 0; j < M; j++) {
				char ch = cmds.charAt(j);
				
				if (ch == 'H')
					map[i][j] = hole;
				else
					map[i][j] = cmds.charAt(j) - '0';
			}
		}
		
		visited[0][0] = true;
		dfs (0, 0, 1);
		
		if (inf)
			System.out.println(-1);
		else
			System.out.println(answer);
	}
	
	static void dfs (int x, int y, int count) {
		if (inf || dp[x][y] >= count)
			return ;
		
		int num = map[x][y];
		
		for (int i = 0; i < 4; i++) {
			int nx = x + num * move[i][0];
			int ny = y + num * move[i][1];
			
			if (check_out(nx, ny)) {
				answer = Math.max(answer, count);
				continue;
			}
			
			dp[x][y] = count;
			if (visited[nx][ny] || num == map[nx][ny]) {
				inf = true;
				break;
			}
			
			visited[nx][ny] = true;
			dfs (nx, ny, count+1);
			visited[nx][ny] = false;
		}
	}
	
	static boolean check_out (int x, int y) {
		if (x >= N || y >= M || x < 0 || y < 0 || map[x][y] == hole)
			return true;
		else
			return false;
	}
}
