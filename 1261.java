import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 1261 {
	
	static int N, M, MAX = 987654321;
	static int [][] map, count;
	static int [][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner (System.in);
	
		M = scanner.nextInt();
		N = scanner.nextInt();
		map = new int [N][M];
		count = new int [N][M];
		
		String str = scanner.nextLine();
		for (int i = 0; i < N; i++) {
			str = scanner.nextLine();
			String [] splits = str.split("");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(splits[j]);
				count[i][j] = MAX;
			}
		}

		count[0][0] = 0;
		Queue <Pair> queue = new LinkedList <Pair> ();
		queue.offer(new Pair (0, 0, 0));
		
		while (!queue.isEmpty()) {
			Pair pair = queue.poll();
			int now_x = pair.x;
			int now_y = pair.y;
			int num = pair.num;
			
			if (now_x == N-1 && now_y == M-1)
				continue;
			
			for (int i = 0; i < 4; i++) {
				int x = now_x + move[i][0];
				int y = now_y + move[i][1];
				
				if (0 > x || x >= N || 0 > y || y >= M)
					continue;
				int now = 0;
				if (map[x][y] == 1)
					now = num+1;
				else
					now = num;
				
				if (now < count[x][y]) {
					count[x][y] = now;
					queue.offer(new Pair (x, y, now));
				}
			}
		}
		
		System.out.println(count[N-1][M-1]);
	}
}

class Pair {
	int x, y, num;
	
	Pair (int x, int y, int num) {
		this.x = x;
		this.y = y;
		this.num = num;
	}
}
