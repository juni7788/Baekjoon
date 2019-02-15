import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 4179 {
	
	static int answer = -1;
	static int N, M;
	static char [][] map;
	static int [][] move = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	static boolean [][] visited;
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner (System.in);
		
		N = scanner.nextInt();
		M = scanner.nextInt();
		int start_x = 0, start_y = 0;
		scanner.nextLine();
		map = new char [N][M];
		visited = new boolean [N][M];
		
		Queue <Pair> fire = new LinkedList <Pair> ();
		Queue <Pair> person = new LinkedList <Pair> ();
		
		for (int i = 0; i < N; i++) {
			String str = scanner.nextLine();
			for (int j = 0; j < M; j++) {
				char ch = str.charAt(j);
				if (ch == 'J') {
					start_x = i;
					start_y = j;
					ch = '.';
				}
				if (ch == 'F')
					fire.offer(new Pair (i, j));
				map[i][j] = ch;
			}
		}
		person.offer(new Pair (start_x, start_y));
		visited[start_x][start_y] = true;
		
		for (int time = 1;; time++) {
			boolean flag = false;
			LinkedList <Pair> list = new LinkedList <Pair> ();
			while (!fire.isEmpty()) {
				Pair pair = fire.poll();
				int now_x = pair.x;
				int now_y = pair.y;
				
				for (int i = 0; i < 4; i++) {
					int x = now_x + move[i][0];
					int y = now_y + move[i][1];
					
					if (check_border(x, y))
						continue;
					if (map[x][y] == '.') {
						map[x][y] = 'F';
						list.add(new Pair (x, y));
					}
				}
			}
			while (!list.isEmpty())
				fire.offer(list.poll());
			
			while (!person.isEmpty()) {
				Pair pair = person.poll();
				int now_x = pair.x;
				int now_y = pair.y;
				
				for (int i = 0; i < 4; i++) {
					int x = now_x + move[i][0];
					int y = now_y + move[i][1];
					
					if (check_border(x, y)) {
						flag = true;
						break;
					}
					if (visited[x][y])
						continue;
					if (map[x][y] == '.') {
						visited[x][y] = true;
						list.add(new Pair (x, y));
					}
				}
				
				if (flag)
					break;
			}
			if (flag) {
				answer = time;
				break;
			}
			if (list.isEmpty())
				break;
			while (!list.isEmpty())
				person.offer(list.poll());
		}
		
		if (answer == -1)
			System.out.println("IMPOSSIBLE");
		else
			System.out.println(answer);
		
	}
	
	static boolean check_border (int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < M)
			return false;
		else
			return true;
	}
}

class Pair {
	int x, y;
	
	Pair (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
