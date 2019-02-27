import java.util.Scanner;
import java.util.Stack;

public class 11047 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		
		int N = scanner.nextInt();
		int K = scanner.nextInt();

		Stack <Integer> stack = new Stack <Integer> ();
		for (int i = 0; i < N; i++)
			stack.push(scanner.nextInt());

		int answer = 0;
		while (K > 0) {
			int num = stack.pop();
			int div = K/num;
			K -= div * num;
			
			answer += div;
		}
		
		System.out.println(answer);
	}
}
