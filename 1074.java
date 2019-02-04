import java.util.Scanner;

public class 1074 {
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int r = scanner.nextInt();
		int c = scanner.nextInt();
		
		int x, y;
		long answer = 0;
		x = y = (int)Math.pow(2, N-1);
		
		while (N >= 1) {
			int target = (int) Math.pow(2, N-1);
			long adder = target*target;
			// side 1
			if (r < x && c < y) {
				x -= target/2;
				y -= target/2;
			}
			// side 2
			else if (r < x && c >= y) {
				x -= target/2;
				y += target/2;
				answer += adder;
			}
			// side 3
			else if (r >= x && c < y) {
				x += target/2;
				y -= target/2;
				answer += 2 * adder;
			}
			// side 4
			else if (r >= x && c >= y) {
				x += target/2;
				y += target/2;
				answer += 3 * adder;
			}
			else
				System.out.println("error");
			N--;
		}

		System.out.println(answer);
	}
}