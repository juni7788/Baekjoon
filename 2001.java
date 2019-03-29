import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 2001 {
	
	static int N, M, K, ans;
	static int [][] map, dp;
	static boolean [] In;
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner (System.in);
		N = scanner.nextInt();
		M = scanner.nextInt();
		K = scanner.nextInt();
		map = new int[N][N];
		In = new boolean [N];
		dp = new int [N][1 << K];
		int adder = 0;
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], -100);
			Arrays.fill(dp[i], -100);
		}
		
		for (int i = 0; i < K; i++)
			In[scanner.nextInt()-1] = true;
		
		if (In[0]) {
			adder = 1;
			In[0] = false;
		}
		
		for (int i = 0; i < M; i++) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			int z = scanner.nextInt();
			map[x-1][y-1] = z;
			map[y-1][x-1] = z;
		}
		
		Queue <Pair> queue = new LinkedList <Pair> ();
		queue.offer(new Pair (0, 0, 0));
		
		while (!queue.isEmpty()) {
			Pair pair = queue.poll();
			int idx = pair.idx;
			int cnt = pair.cnt;
			int visited = pair.visited;

			if (dp[idx][visited] >= cnt)
				continue;

			dp[idx][visited] = cnt;
			if (idx == 0)
				ans = Math.max(ans, cnt);
			
			for (int i = 0; i < N; i++) {
				if (map[idx][i] >= cnt)
					queue.offer(new Pair(i, cnt, visited));
			}
			
			if (In[idx]) {
				visited = visited | (1 >> idx);
				for (int i = 0; i < N; i++) {
					if (map[idx][i] > cnt)
						queue.offer(new Pair (i, cnt+1, visited));
				}
			}
		}
		
		System.out.println(ans+adder);
	}
	
	static void dfs (int idx, int cnt, int visited) {
		if (idx == 0)
			ans = Math.max(ans, cnt);
		
		if (dp[idx][visited] >= cnt)
			return ;
		dp[idx][visited] = cnt;
		for (int i = 0; i < N; i++) {
			if (map[idx][i] >= cnt)
				dfs (i, cnt, visited);
		}
		
		if (!In[idx])
			return ;
		In[idx] = false;
		for (int i = 0; i < N; i++) {
			if (map[idx][i] > cnt)
				dfs (i, cnt+1, visited | (1 << idx));
		}
		In[idx] = true;
	}
}

class Pair {
	int idx, visited, cnt;
	
	Pair (int idx, int cnt, int visited) {
		this.idx = idx;
		this.cnt = cnt;
		this.visited = visited;
	}
}
