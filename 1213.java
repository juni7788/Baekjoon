import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 1213 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		StringBuilder sb = new StringBuilder ("");
		
		int [] arr = new int [26];
		char [] chs = br.readLine().toCharArray();
		char [] ans = new char [chs.length];
		
		for (int i = 0; i < chs.length; i++)
			arr[chs[i]-'A'] += 1;

		int odd = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i]%2 != 0) {
				arr[i] -= 1;
				ans[(ans.length/2)] = (char) (i + 'A');
				odd += 1;
			}
			if (odd > 1) {
				System.out.println("I'm Sorry Hansoo");
				return ;
			}
		}

		int idx = 0, st = 0, end = ans.length-1;
		while (idx < 26) {
			if (arr[idx] == 0) {
				idx += 1;
				continue;
			}
			
			ans[end] = ans[st] = (char) (idx + 'A');
			arr[idx] -= 2;
			st += 1;
			end -= 1;
		}
		
		for (int i = 0; i < ans.length; i++)
			sb.append(ans[i]);
		
		System.out.println(sb);
	}
}
