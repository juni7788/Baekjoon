import java.util.Scanner;

public class 1799 {

	static int N, answer;
	static int [][] map;
	static int [][] dig;
	static boolean [] visited;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		
		N = scanner.nextInt();
		map = new int [N][N];
		dig = new int [N][N];
		visited = new boolean [2*N-1];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = scanner.nextInt();
				dig[i][j] = i-j+N-1;
			}
		}
		
		solve (0, 0, 0);
		int answer1 = answer;
		answer = 0;
		solve (0, 1, 0);
		int answer2 = answer;
		System.out.println(answer1+answer2);
	}
	
	static void solve (int x, int y, int count) {
		if (x >= N || y >= N) {
			answer = Math.max(answer, count);
			return ;
		}

		int next_x = x, next_y = y;
		if (next_y < N-2)
			next_y = y+2;
		else if (next_y == N-2) {
			next_x = 1;
			next_y = N-1;
		}
		else if (next_y == N-1)
			next_x += 2;
		
		solve (next_x, next_y, count);
		while (y >= 0 && x < N) {
			if (map[x][y] == 1 && !visited[dig[x][y]]) {
				visited[dig[x][y]] = true;
				solve (next_x, next_y, count+1);
				visited[dig[x][y]] = false;
			}
			
			x += 1;
			y -= 1;
		}
	}
}
