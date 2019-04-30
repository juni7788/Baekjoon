import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int MAX = -98654321, MIN = 987654321, size, N;
	static int [] num, opr;
	static boolean [] vst;
	static ArrayList <Integer> list = new ArrayList <Integer> ();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer (br.readLine());
		
		num = new int [N];
		opr = new int [4];
		
		for (int i = 0; i < N; i++)
			num[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer (br.readLine());

		for (int i = 0; i < 4; i++)
			opr[i] = Integer.parseInt(st.nextToken());
		
		dfs (1, num[0]);
		
		System.out.println(MAX + "\n" + MIN);
	}
	
	static void dfs (int idx, int now) {
		if (idx == N) {
			MAX = Math.max(MAX, now);
			MIN = Math.min(MIN, now);
			
			return ;
		}
		
		for (int i = 0; i < 4; i++) {
			if (opr[i] == 0)
				continue;
			
			opr[i] -= 1;
			if (i == 0)
				dfs (idx+1, now + num[idx]);
			else if (i == 1)
				dfs (idx+1, now - num[idx]);
			else if (i == 2)
				dfs (idx+1, now * num[idx]);
			else if (i == 3)
				dfs (idx+1, now / num[idx]);
			opr[i] += 1;
		}
	}
}
