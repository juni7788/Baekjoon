import java.util.ArrayList;
import java.util.Scanner;

public class 15684 {
	
	static int N, M, H, answer = 987654321, a_flag;
	static boolean [][] ladder;
	static boolean [] visited;
	static int size;
	static ArrayList <Point> list = new ArrayList <Point> ();
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
	
		N = scanner.nextInt();
		M = scanner.nextInt();
		H = scanner.nextInt();
		
		ladder = new boolean [H][N];
		
		for (int i = 0; i < M; i++) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			
			ladder[x-1][y-1] = true;
		}
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N-1; j++) {
				if (!ladder[i][j])
					list.add(new Point (i, j));
			}
		} 
		size = list.size();
		visited = new boolean [size];
		
		if (check())
			System.out.println(0);
		else {
			dfs (0, 0);
			
			if (answer == 987654321)
				System.out.println(-1);
			else
				System.out.println(answer);
		}
	}
	
	static void dfs (int index, int count) {
		if (count == 3) {
			if (check())
				answer = Math.min(answer, count);
			return ;
		}
		
		if (index == size) {
			if (check())
				answer = Math.min(answer, count);
			return ;
		}
		
		Point point = list.get(index);
		int x = point.x;
		int y = point.y;
		
		dfs (index+1, count);

		if (y == 0) {
			if (!ladder[x][y+1]) {
				visited[index] = true;
				ladder[x][y] = true;
				dfs (index+1, count+1);
				ladder[x][y] = false;
				visited[index] = false;
			}
		}
		else {
			if (!ladder[x][y+1] && !ladder[x][y-1]) {
				visited[index] = true;
				ladder[x][y] = true;
				dfs (index+1, count+1);
				ladder[x][y] = false;
				visited[index] = false;
			}
		}
	}
	
	static boolean check () {
		for (int i = 0; i < N; i++) {
			int x = 0, y = i;
			
			while (x <= H-1) {
				if (y == 0) {
					if (ladder[x][0])
						y = 1;
					else
						y = 0;
				}
				else {
					if (ladder[x][y])
						y += 1;
					else if (ladder[x][y-1])
						y -= 1;
				}
				x += 1;
			}
			
			if (y != i)
				return false;
		}
		
		return true;
	}
}

class Point {
	int x, y;
	
	Point (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
