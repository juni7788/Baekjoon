import java.util.LinkedList;
import java.util.Scanner;

public class 15685 {

	static int answer;
	static int [][] move = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
	static boolean [][] map = new boolean [101][101];
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		int N = scanner.nextInt();
		for (int i = 0 ; i < N; i++) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			int dir = scanner.nextInt();
			int gen = scanner.nextInt();
			
			curve (x, y, dir, gen);
		}
		
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1])
					answer += 1;
			}
		}
		
		System.out.println(answer);
		scanner.close();
	}
	
	static void curve (int x, int y, int dir, int gen) {
		LinkedList <Integer> list = new LinkedList <Integer> ();	
		
		map[x][y] = true;
		list.offer(dir);
		x += move[dir][0];
		y += move[dir][1];
		map[x][y] = true;
		
		for (int g = 1; g <= gen; g++) {
			int size = list.size();
			for (int i = size-1; i >= 0; i--) {
				int now = (list.get(i)+1)%4;
				list.offer(now);
				x += move[now][0];
				y += move[now][1];
				map[x][y] = true;
			}
		}
	}
}
