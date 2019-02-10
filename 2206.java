import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 2206 {

	static int N, M;
	static int [][] map;
	static int [][][] forward;
	static int MIN = 987654321;
	static int MAX = 987654321;
	static int [][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		M = scanner.nextInt();
		map = new int [N][M];
		forward = new int [N][M][2];
		
		String str = scanner.nextLine();
		for (int i = 0; i < N; i++) {
			str = scanner.nextLine();
			for (int j = 0; j < M; j++)
				map[i][j] = str.charAt(j) - '0';
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				forward[i][j][0] = MAX;
				forward[i][j][1] = MAX;
			}
		}

		Queue <Point> queue = new LinkedList <Point> ();
		queue.offer(new Point (0, 0, 0, 1));
		
		while (!queue.isEmpty()) {
			Point point = queue.poll();
			int now_x = point.x;
			int now_y = point.y;
			int count = point.count;
			int dst = point.dst + 1;
			
			if (now_x == N-1 && now_y == M-1) {
				MIN = dst-1;
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int x = now_x + move[i][0];
				int y = now_y + move[i][1];
				
				if (0 > x || x >= N || 0 > y || y >= M)
					continue;
				
				if (map[x][y] == 1) {
					if (count == 1)
						continue;
					if (dst < forward[x][y][1]) {
						forward[x][y][1] = dst;
						queue.offer(new Point (x, y, 1, dst));
					}
					continue;
				}
				
				// map[x][y] = 0
				if (count == 0) {
					if (dst < forward[x][y][0]) {
						forward[x][y][0] = dst;
						queue.offer(new Point (x, y, 0, dst));
					}
				}
				else {
					if (dst < forward[x][y][1] && dst < forward[x][y][0]) {
						forward[x][y][1] = count;
						queue.offer(new Point (x, y, 1, dst));
					}
				}
			}
		}
		
		if (MIN == MAX)
			System.out.println(-1);
		else
			System.out.println(MIN);
	}
}

class Point {
	int x, y, count, dst;
	
	Point (int x, int y, int count, int dst) {
		this.x = x;
		this.y = y;
		this.count = count;
		this.dst = dst;
	}
}