import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 1743 {
	
	static int [][] map;
	static boolean [][] check;
	static int [][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int MAX = 0;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		int K = scanner.nextInt();
		map = new int[N][M];
		check = new boolean[N][M];
		
		ArrayList <Pair> list = new ArrayList <Pair> ();
			
		for (int i = 0; i < K; i++) {
			int x = scanner.nextInt()-1;
			int y = scanner.nextInt()-1;
			map[x][y] = 1;
			list.add(new Pair (x, y));
		}
		
		for (Pair pair: list) {
			int now_x = pair.x;
			int now_y = pair.y;
			int size = 0;
			
			if (check[now_x][now_y])
				continue;
			Queue <Pair> queue = new LinkedList <Pair> ();
			check[now_x][now_y] = true;
			queue.offer(new Pair (now_x, now_y));
			
			while (!queue.isEmpty()) {
				Pair now = queue.poll();
				now_x = now.x;
				now_y = now.y;
				size += 1;
				
				for (int i = 0; i < 4; i++) {
					int x = now_x + move[i][0];
					int y = now_y + move[i][1];
					
					if (0 > x || x >= N || 0 > y || y >= M)
						continue;
					if (check[x][y] || map[x][y] == 0)
						continue;
					
					check[x][y] = true;
					queue.offer(new Pair (x, y));
				}
			}
			
			MAX = Math.max(size, MAX);
		}
		
		System.out.println(MAX);
	}
}

class Pair {
	int x, y;
	
	Pair (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
