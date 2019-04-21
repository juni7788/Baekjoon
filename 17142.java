import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class 17142 {

	static ArrayList <Pair> list = new ArrayList <Pair> ();
	static int N, M, ans = 987654321, now;
	static int [][] map;
	static int [] comb;
	static int [][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		String [] cmd = br.readLine().split(" ");
		
		N = Integer.parseInt(cmd[0]);
		M = Integer.parseInt(cmd[1]);
		map = new int [N][N];
		comb = new int [M];
		
		for (int i = 0; i < N; i++) {
			cmd = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(cmd[j]);
				if (num == 2)
					list.add(new Pair (i, j));
				if (num == 0)
					now += 1;
				map[i][j] = num;
			}
		}
		
		if (now == 0) {
			System.out.println(0);
			return ;
		}
		
		dfs (0, 0, list.size());
		
		if (ans == 987654321)
			System.out.println(-1);
		else
			System.out.println(ans);
	}
	
	static void dfs (int idx, int cnt, int lmt) {
		if (cnt == M) {
			check ();
			return ;
		}
		
		if (idx + M - cnt > lmt)
			return ;
		
		comb[cnt] = idx;
		dfs (idx+1, cnt+1, lmt);
		dfs (idx+1, cnt, lmt);
	}
	
	static void check () {
		int [][] nmap = new int [N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				nmap[i][j] = map[i][j];
		}

		Queue <Pair> queue = new LinkedList <Pair> ();
		
		for (int i = 0; i < M; i++) {
			Pair pair = list.get(comb[i]);
			nmap[pair.x][pair.y] = 3;
			queue.offer(new Pair (pair.x, pair.y));
		}
		
		int time = 1, cnt = now;
		for (; ; time++) {
			Queue <Pair> adder = new LinkedList <Pair> ();
			
			while (!queue.isEmpty()) {
				Pair pair = queue.poll();
				int x = pair.x;
				int y = pair.y;
				
				for (int i = 0; i < 4; i++) {
					int nx = x + move[i][0];
					int ny = y + move[i][1];
					
					if (0 > nx || nx >= N || 0 > ny || ny >= N)
						continue;
					if (nmap[nx][ny] == 0 || nmap[nx][ny] == 2) {
						if (nmap[nx][ny] == 0)
							cnt -= 1;
						nmap[nx][ny] = 3;
						adder.offer(new Pair (nx, ny));
					}
				}
			}

			if (cnt == 0 || adder.isEmpty())
				break;
			queue = adder;
		}
		
		if (cnt == 0)
			ans = Math.min(ans, time);
	}
}

class Pair {
	int x, y;
	
	Pair (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
