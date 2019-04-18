import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class 1916 {

	static final int INF = 987654321;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
	
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int [][] adj = new int [N][N];
		
		for (int i = 0; i < N; i++)
			Arrays.fill(adj[i], INF);
		
		String [] cmd = {};
		for (int i = 0; i < M; i++) {
			cmd = br.readLine().split(" ");
			int s = Integer.parseInt(cmd[0])-1;
			int t = Integer.parseInt(cmd[1])-1;
			int d = Integer.parseInt(cmd[2]);
			
			adj[s][t] = Math.min(adj[s][t], d);
		}
		
		cmd = br.readLine().split(" ");
		int st = Integer.parseInt(cmd[0])-1;
		int end = Integer.parseInt(cmd[1])-1;
		
		int [] dist = new int [N];
		boolean [] vst = new boolean [N];
		Arrays.fill(dist, INF);
		dist[st] = 0;
		
		PriorityQueue <Pair> prq = new PriorityQueue <Pair> ();
		
		for (int i = 0; i < N; i++)
			prq.offer(new Pair (i, dist[i]));
		
		while (!prq.isEmpty()) {
			Pair pair = prq.poll();
			int idx = pair.idx;
			int dst = pair.tot;
			if (dist[idx] < dst)
				continue;
			vst[idx] = true;
			
			for (int i = 0; i < N; i++) {
				if (dist[idx] + adj[idx][i] < dist[i]) {
					dist[i] = dist[idx] + adj[idx][i];
					prq.offer(new Pair (i, dist[i]));
				}
			}
		}
		
		System.out.println(dist[end]);
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
