import java.util.LinkedList;
import java.util.Scanner;

public class 11657 {
	
	static int N, M, MAX = 987654321;
	static int [] dist;
	static int [][] arr;
	static boolean flag = false;
	static LinkedList <Pair> edge = new LinkedList <Pair> ();
	
	public static void main(String[] args) {	
		Scanner scanner = new Scanner(System.in);

		N = scanner.nextInt();
		M = scanner.nextInt();
		dist = new int [N];
		arr = new int [N][N];
		
		for (int i = 0; i < N; i++) {
			dist[i] = MAX;
			for (int j = 0; j < N; j++)
				arr[i][j] = MAX;
		}
		
		for (int i = 0; i < M; i++) {
			int num1 = scanner.nextInt()-1;
			int num2 = scanner.nextInt()-1;
			int time = scanner.nextInt();

			arr[num1][num2] = Math.min(time, arr[num1][num2]);
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] != MAX)
					edge.offer(new Pair (i, j, arr[i][j]));
			}
		}
		dist[0] = 0;
		
		for (int i = 0; i < N-1; i++) {
			for (Pair pair: edge) {
				int from = pair.from;
				int to   = pair.to;
				int dd   = dist[from] + pair.time;

				if (dist[from] != MAX) {
					if (dist[to] > dd)
						dist[to] = dd;
				}
			}
		}
		
		boolean flag = false;
		for (Pair pair: edge) {
			int from = pair.from;
			int to   = pair.to;
			int dd   = dist[from] + pair.time;
			
			if (dist[from] != MAX) {
				if (dist[to] > dd) {
					flag = true;
					break;
				}
			}
		}
	
		if (flag)
			System.out.println(-1);
		else {
			StringBuilder builder = new StringBuilder ("");
			for (int i = 1; i < N; i++) {
				if (dist[i] == MAX)
					builder.append(-1 + "\n");
				else
					builder.append(dist[i] + "\n");
			}
			
			System.out.println(builder);
		}
	}
}

class Pair{
	int from, to, time;
	
	Pair (int from, int to, int time) {
		this.from = from;
		this.to = to;
		this.time = time;
	}
}
