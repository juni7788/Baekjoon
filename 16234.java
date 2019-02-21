import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 16234 {
	
	static int [][] union, map;
	static int N, L, R, count, limit, part;
	static int [][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static LinkedList <Pair> [] list;
	static int [] people;
	static Queue <Pair> queue;
			
	public static void main(String args[]) {
		Scanner scanner = new Scanner (System.in);
		N = scanner.nextInt();
		L = scanner.nextInt();
		R = scanner.nextInt();
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				map[i][j] = scanner.nextInt();
		}
		
		while (true) {
			set_default();
			part = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (union[i][j] != 0)
						continue;
					set_start (i, j, ++part);
					
					while (!queue.isEmpty()) {
						Pair pair = queue.poll();
						int now_x = pair.x;
						int now_y = pair.y;
						
						for (int k = 0; k < 4; k++) {
							int x = now_x + move[k][0];
							int y = now_y + move[k][1];
							
							if (0 > x || x >= N || 0 > y || y >= N)
								continue;
							if (union[x][y] != 0)
								continue;
							
							int diff = Math.abs(map[x][y] - map[now_x][now_y]);
							if (L <= diff && diff <= R) {
								union[x][y] = part;
								queue.offer(new Pair (x, y));
								list[part].offer(new Pair(x, y));
								people[part] += map[x][y];
							}
						}
					}
				}
			}

			if (part == N*N) // There is no union
				break;
			
			for (int i = 1; i <= limit; i++) {
				int num = list[i].size();
				if (num == 1)
					continue;
				int avg = people[i]/num;
				
				while (!list[i].isEmpty()) {
					Pair pair = list[i].poll();
					int x = pair.x;
					int y = pair.y;
					
					map[x][y] = avg;
				}
			}
			count += 1;
		}
		
		System.out.println(count);
	}
	
	static void set_default() {
		list = new LinkedList [N*N+2];
		people = new int [N*N+2];
		union = new int [N][N];
		
		for (int i = 0; i < N*N+2; i++)
			list[i] = new LinkedList <Pair> ();
	}
	
	static void set_start(int x, int y, int num) {
		queue = new LinkedList <Pair> ();
		queue.offer(new Pair (x, y));
		people[num] = map[x][y];
		list[num].add(new Pair (x, y));
		union[x][y] = num;
		limit = num;
	}
}

class Pair {
	int x, y;
	
	Pair (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
