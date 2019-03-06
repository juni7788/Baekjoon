import java.util.ArrayList;
import java.util.Scanner;

public class 4948 {
	
	static boolean [] check;
	static int MAX = 0;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		StringBuilder builder = new StringBuilder ("");
		
		ArrayList <Integer> list = new ArrayList <Integer> ();

		while (true) {
			int num = scanner.nextInt();
			if (num == 0)
				break;
			MAX = Math.max(MAX, num);
			list.add(num);
		}
		check = new boolean [2*MAX+1];
		
		get();
		
		for (Integer num: list) {
			int count = 0;
			for (int i = num+1; i <= 2*num; i++) {
				if (!check[i])
					count += 1;
			}
			builder.append(count + "\n");
		}

		System.out.println(builder);
	}
	
	static void get () {
		int max = (int) Math.sqrt(2*MAX);
		
		check[1] = true;
		for (int i = 2; i <= max; i++) {
			if (check[i])
				continue;
			for (int j = i*i; j <= 2*MAX; j += i)
				check[j] = true;
		}
	}
}
