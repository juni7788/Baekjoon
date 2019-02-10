import java.util.LinkedList;
import java.util.Scanner;

public class 5397 {

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);

		StringBuilder builder = new StringBuilder ("");
		int Testcase = scanner.nextInt();
		
		scanner.nextLine();
		for (int test = 0; test < Testcase; test++) {
			LinkedList <String> left = new LinkedList <String> ();
			LinkedList <String> right = new LinkedList <String> ();
			String str = scanner.nextLine();
			String [] splits = str.split("");
			
			for (int i = 0; i < splits.length; i++) {
				str = splits[i];
				if (str.equals("<")) {
					if (!left.isEmpty())
						right.addFirst(left.pollLast());
					continue;
				}
				if (str.equals(">")) {
					if (!right.isEmpty())
						left.addLast(right.pollFirst());
					continue;
				}
				if (str.equals("-")) {
					if (!left.isEmpty())
						left.removeLast();
					continue;
				}
				left.addLast(str);
			}
			while (!left.isEmpty())
				builder.append(left.pollFirst());
			while (!right.isEmpty())
				builder.append(right.pollFirst());
			builder.append("\n");
		}
		
		System.out.println(builder);
	}
}