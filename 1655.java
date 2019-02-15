import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 1655 {
	
	static PriorityQueue <Integer> max, min;
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner (System.in);
		StringBuilder builder = new StringBuilder ("");
		
		min = new PriorityQueue <Integer> ();
		max = new PriorityQueue <Integer> (new Comparator <Integer> () {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
			
		});
		
		int N = scanner.nextInt();
		
		for (int i = 0; i < N ; i++) {
			int now = scanner.nextInt();
			
			if (max.size() == min.size()) {
				max.offer(now);

				if (min.size() != 0)
					check();
			}
			else {
				min.offer(now);
				check();
			}
			
			builder.append(String.format("%d\n", max.peek()));
		}
		
		System.out.println(builder);
	}
	
	static void check () {
		int x = max.peek();
		int y = min.peek();

		if (x > y) {
			max.poll();
			min.poll();
			max.offer(y);
			min.offer(x);
		}
	}
}
