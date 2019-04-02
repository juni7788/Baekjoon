import java.util.Scanner;

public class Main {

	static StringBuilder builder = new StringBuilder ("");
    
    public static void main(String args[]) {
		Scanner scanner = new Scanner (System.in);
		int N = scanner.nextInt();
		
		move (N, 1, 2, 3);
		
		System.out.println((1<<N)-1);
		System.out.println(builder);
	}
	
	static void move (int num, int from, int by, int to) {
        if (num == 1)
			builder.append(from + " " + to + "\n");
		else {
			move (num-1, from, to, by);
			builder.append(from + " " + to + "\n");
			move (num-1, by, from, to);
		}
	}
}
