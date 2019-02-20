import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 1926 {
	
	static int M, N, MAX, total;
	static boolean [][] map;
	static boolean [][] check;
	static int [][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner (System.in);
	
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		map = new boolean [N][M];
		check = new boolean [N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int num = scanner.nextInt();
				if (num == 1)
					map[i][j] = true;
				else
					check[i][j] = true;
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (check[i][j])
					continue;
				int count = 1;
				total += 1;
				Queue <Pair> queue = new LinkedList <Pair> ();
				check[i][j] = true;
				queue.offer(new Pair (i, j));
				
				while (!queue.isEmpty()) {
					Pair pair = queue.poll();
					int now_x = pair.x;
					int now_y = pair.y;
					
					for (int k = 0; k < 4; k++) {
						int x = now_x + move[k][0];
						int y = now_y + move[k][1];
						
						if (0 > x || x >= N || 0 > y || y >= M)
							continue;
						if (check[x][y])
							continue;
						count += 1;
						check[x][y] = true;
						queue.offer(new Pair(x, y));
					}
				}
				
				MAX = Math.max(count, MAX);
			}
		}
		
		System.out.println(total);
		System.out.println(MAX);
	}
}

class Pair {
	int x, y;
	Pair (int x, int y){
		this.x = x;
		this.y = y;
	}
}
