import java.util.Scanner;

public class 1929 {
	
	static boolean [] check;
	static int M, N;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		StringBuilder builder = new StringBuilder("");
		
		M = scanner.nextInt();
		N = scanner.nextInt();
		check = new boolean [N+1];
		
		get();
		
		for (int i = M; i <= N; i++) {
			if (!check[i])
				builder.append(i + "\n");
		}
		
		System.out.println(builder);
	}
	
	static void get () {
		int MAX = (int) Math.sqrt(N);
		
		check[1] = true;
		for (int i = 2; i <= MAX; i++) {
			if (check[i])
				continue;
			for (int j = i*i; j <= N; j += i)
				check[j] = true;
		}
	}
}
