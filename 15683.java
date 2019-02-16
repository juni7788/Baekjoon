import java.util.LinkedList;
import java.util.Scanner;

public class 15683 {
	
	static int N, M, count, MAX;
	static int [] perm;
	static int [][] map;
	static boolean [][] safety;
	static LinkedList <CCTV> adder = new LinkedList <CCTV> ();
	static LinkedList <CCTV> list = new LinkedList <CCTV> ();
	static int [][] move = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner (System.in);
		
		N = scanner.nextInt();
		M = scanner.nextInt();
		map = new int [N][M];
		safety = new boolean [N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int num = scanner.nextInt();
				map[i][j] = num;

				if (1 <= num && num <= 5)
					list.offer(new CCTV (i, j, num));
				if (num == 6) {
					safety[i][j] = true;
					count += 1;
				}
			}
		}
		
		perm = new int [list.size()];
		dfs(0);
		
		System.out.println(N*M - MAX);
	}
	
	static void dfs (int index) {
		if (index == list.size()) {
			monitor();
			return ;
		}
		for (int i = 0; i < 4; i++) {
			perm[index] = i;
			dfs (index+1);
		}
	}
	
	static void monitor() {
		adder = new LinkedList <CCTV> ();
		
		for (int index = 0; index < list.size(); index++) {
			CCTV cctv = list.get(index);
			int dir = perm[index];
			int x = cctv.x;
			int y = cctv.y;
			int level = cctv.level;
			
			if (level == 1)
				moving (x, y, dir);
			if (level == 2) {
				if (dir == 0 || dir == 2) {
					moving (x, y, 0);
					moving (x, y, 2);
				}
				if (dir == 1 || dir == 3) {
					moving (x, y, 1);
					moving (x, y, 3);
				}
			}
			if (level == 3) {
				if (dir == 3) {
					moving (x, y, 3);
					moving (x, y, 0);
				}
				else {
					moving (x, y, dir);
					moving (x, y, dir+1);
				}
			}
			if (level == 4) {
				for (int j = 0; j < 4; j++) {
					if (j == dir)
						continue;
					moving (x, y, j);
				}
			}
			if (level == 5) {
				for (int j = 0; j < 4; j++)
					moving (x, y, j);
			}
		}
		
		MAX = Math.max(MAX, count + adder.size());
		
		while (!adder.isEmpty()) {
			CCTV pair = adder.poll();
			safety[pair.x][pair.y] = false;
		}
	}
	
	static void moving (int x, int y, int index) {
		while (0 <= x && x < N && 0 <= y && y < M) {
			if (map[x][y] == 6)
				break;
			if (safety[x][y]) {
				x += move[index][0];
				y += move[index][1];
				continue;
			}
			safety[x][y] = true;
			adder.add(new CCTV (x, y, -1));
			
			x += move[index][0];
			y += move[index][1];
		}
	}
}

class CCTV {
	int x, y, level;
	
	CCTV (int x, int y, int level) {
		this.x = x;
		this.y = y;
		this.level = level;
	}
}
