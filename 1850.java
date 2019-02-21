import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 1850 {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));

		String str = br.readLine();
		String [] num = str.split(" ");
		
		long check = get_gcd (Long.parseLong(num[0]), Long.parseLong(num[1]));
		StringBuilder answer = new StringBuilder("");
		for (int i = 0; i < check; i++)
			answer.append("1");
		
		System.out.println(answer);
	}
	
	static long get_gcd (long A, long B) {
		long r = A%B;
		
		while (r != 0) {
			A = B;
			B = r;
			r = A % B;
		}
		return B;
	}
}
