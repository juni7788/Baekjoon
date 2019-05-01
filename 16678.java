import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		
		int N = Integer.parseInt(br.readLine());
		int [] num = new int [N+1];
		long ans = 0;
		
		for (int i = 0; i < N; i++) {
			int now = Integer.parseInt(br.readLine());
			if (now > N) {
				ans += (now-N);
				num[N] += 1;
			}
			else
				num[now] += 1;
		}
		
		int idx = 1, cnt = 0;
		for (int i = 1; i <= N && cnt != N;) {
			if (num[i] == 0) {
				i += 1;
				continue;
			}
			cnt += 1;
			num[i] -= 1;
			if (i >= idx) {
				ans += (i - idx);
				idx += 1;
			}
		}
		
		System.out.println(ans);
	}
}
