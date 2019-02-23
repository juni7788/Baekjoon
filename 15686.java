import java.util.ArrayList;
import java.util.Scanner;

public class 15686 {
	
	static int N, M, num, answer = 987654321;
	static int [][] map;
	static ArrayList <Pair> house = new ArrayList <Pair> ();
	static ArrayList <Pair> norang = new ArrayList <Pair> ();
	static int [] comb;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);

		N = scanner.nextInt();
		M = scanner.nextInt();
		map = new int [N][N];
		comb = new int [M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = scanner.nextInt();
				if (map[i][j] == 1)
					house.add(new Pair (i, j));
				else if (map[i][j] == 2)
					norang.add(new Pair (i, j));
			}
		}
		num = norang.size();
		dfs (0, 0);
		
		System.out.println(answer);
	}
	
	static void dfs (int index, int count) {
		if (count == M) {
			int total = 0;
			for (Pair pair: house) {
				int x = pair.x;
				int y = pair.y;
				int MIN = 987654321;
				
				for (int i = 0; i < M; i++) {
					Pair chick = norang.get(comb[i]);
					int now_x = chick.x;
					int now_y = chick.y;
					int dist = Math.abs(x-now_x) + Math.abs(y-now_y);
					MIN = Math.min(MIN, dist);
				}
				total += MIN;
			}
			answer = Math.min(total, answer);
			return ;
		}
		if (index == num)
			return ;
		
		dfs (index+1, count);
		comb[count] = index;
		dfs (index+1, count+1);
	}
}

class Pair {
	int x, y;
	
	Pair (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
