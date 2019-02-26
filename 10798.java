import java.util.Scanner;

public class 10798 {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		String [] arr = new String [5];
		StringBuilder answer = new StringBuilder ("");
		int MAX = -1;
		
		for (int i = 0; i < 5; i++) {
			arr[i] = scanner.nextLine();
			MAX = Math.max(MAX, arr[i].length());
		}
	
		for (int j = 0; j < MAX; j++) {
			for (int i = 0; i < 5; i++) {
				if (arr[i].length() > j)
					answer.append(arr[i].charAt(j));
			}
		}
		
		System.out.println(answer);
	}
}
