import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 16236 {
	
	static int [] move_x = {-1, 0, 0, 1};
	static int [] move_y = {0, -1, 1, 0};
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner (System.in);

		int N = scanner.nextInt();
		int [][] map = new int [N][N];
		
		int start_x = -1, start_y = -1;
		int total = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int num = scanner.nextInt();
				
				if (num == 9) {
					start_x = i;
					start_y = j;
				}
				else if (num != 0) {
					total += 1;
					map[i][j] = num;
				}
				else
					map[i][j] = 0;
			}
		}
		
		int time = 0;
		int state = 2;
		int count = 0;

		while (true) {
			map[start_x][start_y] = 0;
			if (count == state) {
				state += 1;
				count = 0;
			}

			Queue <Pair> queue = new LinkedList <Pair> ();
			boolean [][] visited = new boolean [N][N];
			
			boolean flag = false;
			int cur = -1;
			
			queue.offer(new Pair(start_x, start_y, 0));
			while (!queue.isEmpty()) {
				Pair pair = queue.poll();
				
				int now_x = pair.x;
				int now_y = pair.y;
				int num = pair.num;
				
				if (flag) {
					if (cur == num)
						break;
				}
				
				for (int k = 0; k < 4; k++) {
					int x = now_x + move_x[k];
					int y = now_y + move_y[k];
					
					if (0 > x || x >= N || 0 > y || y >= N)
						continue;
					if (visited[x][y])
						continue;

					if (map[x][y] > 0 && map[x][y] < state) {
						visited[x][y] = true;
						if (flag) {
							if (x < start_x) {
								start_x = x;
								start_y = y;
							}
							else if (x == start_x) {
								if (y < start_y) {
									start_x = x;
									start_y = y;
								}
							}
						}
						else {
							cur = num + 1;
							time += (num + 1);
							count += 1;
							start_x = x;
							start_y = y;
							flag = true;
						}
					}
					else if (map[x][y] == 0 || map[x][y] == state) {
						visited[x][y] = true;
						queue.offer(new Pair (x, y, num+1));
					}
				}
			}
			
			if (!flag)
				break;
		}
		
		System.out.println(time);
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