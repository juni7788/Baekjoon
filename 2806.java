import java.util.Scanner;

public class 2806 {
	
	public static void main(String args[]) {

		Scanner scanner = new Scanner (System.in);
		int N = scanner.nextInt();
		scanner.nextLine();
		String str = scanner.nextLine();
		
		char [] arr = new char [N];
		int [] A = new int [N];
		int [] B = new int [N];
		
		for (int i = 0; i < N; i++)
			arr[i] = str.charAt(i);
		
		if (arr[0] == 'A')
			B[0] = 1;
		else
			A[0] = 1;
		
		for (int i = 1; i < N; i++) {
			if (arr[i] == 'A') {
				A[i] = Math.min(A[i-1], B[i-1]+1);
				B[i] = Math.min(A[i-1], B[i-1])+1;
			}
			else {
				A[i] = Math.min(A[i-1], B[i-1])+1;
				B[i] = Math.min(A[i-1]+1, B[i-1]);
			}
		}
		
		System.out.println(A[N-1]);
	}
}
