import java.util.Scanner;

public class 16917 {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		
		int yang = scanner.nextInt();
		int who = scanner.nextInt();
		int half   = scanner.nextInt();
		int A = scanner.nextInt();
		int B = scanner.nextInt();
		
		int max = Math.max(A, B);
		int answer = 987654321;
		for (int i = 0; i <= max; i++) {
			int count = 2*i;
			int total = 0;
			
			total += half * count;
			total += yang * Math.max(A - i, 0);
			total += who  * Math.max(B - i, 0);
			answer = Math.min(answer, total);
		}
		
		System.out.println(answer);
	}
}
