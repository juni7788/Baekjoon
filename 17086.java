import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 17086 {
	
	static int [][] move = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, 0}, {1, -1}, {1, 1}};
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner (System.in);
		
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		int [][] map = new int [N][M];
		
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int num = scanner.nextInt();
				if (num == 1) {
					cnt += 1;
					map[i][j] = num;
				}
			}
		}
		
		int MAX = -1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1)
					continue;

				Queue <Point> queue = new LinkedList <Point> ();
				boolean [][] visited = new boolean [N][M];
				queue.offer(new Point (i, j, 0));
				visited[i][j] = true;
				
				while (!queue.isEmpty()) {
					Point point = queue.poll();
					int x = point.x;
					int y = point.y;
					cnt = point.cnt;
					
					if (map[x][y] == 1) {
						MAX = Math.max(MAX, cnt);
						break;
					}
					
					for (int k = 0; k < 8; k++) {
						int nx = x + move[k][0];
						int ny = y + move[k][1];
						
						if (0 > nx || nx == N || 0 > ny || ny == M || visited[nx][ny])
							continue;
						queue.offer(new Point (nx, ny, cnt+1));
						visited[nx][ny] = true;
					}
				}
			}
		}
		
		System.out.println(MAX);
	}
}

class Point {
	int x, y, cnt;
	
	Point (int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}
