import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 2411 {
	
	static ArrayList <Pair> list = new ArrayList <Pair> ();
	static int move [][] = {{0, 1}, {1, 0}};
	static int [][] map;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		int A = scanner.nextInt();
		int B = scanner.nextInt();

		map = new int [N][M];
		
		for (int i = 0; i < A; i++) {
			int x = scanner.nextInt()-1;
			int y = scanner.nextInt()-1;
			
			map[x][y] = 1;
		}
		for (int i = 0; i < B; i++) {
			int x = scanner.nextInt()-1;
			int y = scanner.nextInt()-1;
			
			map[x][y] = 2;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1)
					list.add(new Pair (i, j));
			}	
		}
		
		int answer = 1;
		answer *= bfs (0, 0, list.get(0).x, list.get(0).y);
		answer *= bfs (list.get(list.size()-1).x, list.get(list.size()-1).y, N-1, M-1);
		
		for (int i = 0; i < list.size()-1; i++) {
			int now_x = list.get(i).x;
			int now_y = list.get(i).y;
			int end_x = list.get(i+1).x;
			int end_y = list.get(i+1).y;

			answer *= bfs (now_x, now_y, end_x, end_y);
		}
		
		System.out.println(answer);
	}
	
	static int bfs (int start_x, int start_y, int end_x, int end_y) {
		int total = 0;
		Queue <Pair> queue = new LinkedList <Pair> ();
		
		queue.offer(new Pair (start_x, start_y));

		while (!queue.isEmpty()) {
			Pair pair = queue.poll();
			int now_x = pair.x;
			int now_y = pair.y;
			
			if (now_x == end_x && now_y == end_y) {
				total += 1;
				continue;
			}

			for (int i = 0; i < 2; i++) {
				int x = now_x + move[i][0];
				int y = now_y + move[i][1];
				
				if (x > end_x || y > end_y)
					continue;
				if (map[x][y] == 2)
					continue;
			
				queue.offer(new Pair (x, y));
			}
		}
		return total;
	}
}

class Pair{
	int x, y;
	
	Pair (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
