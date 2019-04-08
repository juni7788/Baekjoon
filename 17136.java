import java.util.ArrayList;
import java.util.Scanner;

public class 17136 {
	
	static ArrayList <Point> list = new ArrayList <Point> ();
	static int [][] map = new int [10][10];
	static int [] arr = new int [6];
	static int MIN = 200, size;
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner (System.in);
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				int num = scanner.nextInt();
				if (num == 1) {
					size += 1;
					map[i][j] = 1;
					list.add(new Point (i, j));
				}
			}
		}
		
		if (size == 0)
			System.out.println(0);
		else {
			dfs (0, 0);
			
			if (MIN == 200)
				System.out.println(-1);
			else
				System.out.println(MIN);
		}
	}
	
	static void dfs (int idx, int cnt) {
		if (cnt == MIN)
			return ;
		
		if (idx == size) {
			MIN = Math.min(MIN, cnt);
			return ;
		}
		
		Point point = list.get(idx);
		int x = point.x;
		int y = point.y;
		
		if (map[x][y] == 0)
			dfs (idx+1, cnt);
		
		for (int i = 1; i <= 5; i++) {
			if (arr[i] == 5)
				continue;
			if (Is_check (x, y, i)) {
				arr[i] += 1;
				change (x, y, i, 0);
				dfs (idx+1, cnt+1);
				change (x, y, i, 1);
				arr[i] -= 1;
			}
		}
	}
	
	static boolean Is_check (int x, int y, int len) {
		if (x + len > 10 || y + len > 10)
			return false;
		
		for (int i = x; i < len+x; i++) {
			for (int j = y; j < len+y; j++) {
				if (map[i][j] == 0)
					return false;
			}
		}
		
		
		return true;
	}
	
	static void change (int x, int y, int len, int num) {
		for (int i = x; i < len+x; i++) {
			for (int j = y; j < len+y; j++)
				map[i][j] = num;
		}
	}
}

class Point {
	int x, y;
	
	Point (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
