import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class 1238 {

	static final int INF = 987654321;
	static boolean [] vst;
	static int [][] adj;
	static int N, st;
	static PriorityQueue <Pair> prq = new PriorityQueue <Pair> ();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));

		String [] cmd = br.readLine().split(" ");
	
		N = Integer.parseInt(cmd[0]);
		int M = Integer.parseInt(cmd[1]);
		st = Integer.parseInt(cmd[2])-1;
		adj = new int [N][N];
		int [] fwd = new int [N];
		int [] bwd = new int [N];
		
		for (int i = 0; i < N; i++)
			Arrays.fill(adj[i], INF);
		
		for (int i = 0; i < M; i++) {
			cmd = br.readLine().split(" ");
			int s = Integer.parseInt(cmd[0])-1;
			int t = Integer.parseInt(cmd[1])-1;
			int d = Integer.parseInt(cmd[2]);
			
			adj[s][t] = Math.min(adj[s][t], d);
		}
		
		for (int s = 0; s < N; s++) {
			if (s == st) {
				fwd[st] = 0;
				continue;
			}
			
			int [] dist = new int [N];
			dija (true, s, dist);
			
			fwd[s] = dist[st];
		}
		
		dija (false, st, bwd);
		
		int max = 0;
		for (int i = 0; i < N; i++)
			max = Math.max(fwd[i] + bwd[i], max);
	
		System.out.println(max);
	}
	
	static void dija (boolean flag, int s, int [] dist) {
		vst = new boolean [N];
		Arrays.fill(dist, INF);
		dist[s] = 0;

		PriorityQueue <Pair> prq = new PriorityQueue <Pair> ();
		prq.offer(new Pair (s, 0));
		
		while (!prq.isEmpty()) {
			Pair pair = prq.poll();
			int idx = pair.idx;
			int tot = pair.tot;
			if (tot > dist[idx])
				continue;
			vst[idx] = true;
			if (flag && vst[st])
				break;
			
			for (int i = 0; i < N; i++) {
				if (i == idx)
					continue;
				if (!vst[i] && dist[i] > tot + adj[idx][i]) {
					dist[i] = dist[idx] + adj[idx][i];
					prq.offer(new Pair (i, dist[i]));
				}
			}
		}
	}
}

class Pair implements Comparable <Pair> {
	int idx, tot;
	
	Pair (int idx, int tot) {
		this.idx = idx;
		this.tot = tot;
	}

	@Override
	public int compareTo(Pair tar) {
		// TODO Auto-generated method stub
		return this.tot - tar.tot;
	}
}
