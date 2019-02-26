import java.util.Scanner;

public class1547 {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		
		boolean [] ball = new boolean [4];
		ball[1] = true;
		
		int N = scanner.nextInt();
		int answer = 1;
		
		for (int i = 0; i < N; i++) {
			int num1 = scanner.nextInt();
			int num2 = scanner.nextInt();
			
			if (ball[num1]) {
				ball[num1] = false;
				ball[num2] = true;
				answer = num2;
			}
			else if (ball[num2]) {
				ball[num2] = false;
				ball[num1] = true;
				answer = num1;
			}
		}
		
		System.out.println(answer);
	}
}
