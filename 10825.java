import java.util.PriorityQueue;
import java.util.Scanner;

public class 10825.java {

	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		StringBuilder builder = new StringBuilder ("");
		
		int N = scanner.nextInt();
		scanner.nextLine();
		
		PriorityQueue <Test> prq = new PriorityQueue <Test> ();
		for (int i = 0; i < N; i++) {
			String[] str = scanner.nextLine().split(" ");
			prq.offer(new Test (str[0], str[1], str[2], str[3]));
		}
		
		while (!prq.isEmpty())
			builder.append(prq.poll().name + "\n");
		
		System.out.println(builder);
	}
}

class Test implements Comparable <Test>{
	String name;
	int korean, english, math;
	
	Test (String name, String korean, String english, String math) {
		this.name = name;
		this.korean = Integer.parseInt(korean);
		this.english = Integer.parseInt(english);
		this.math = Integer.parseInt(math);
	}

	@Override
	public int compareTo(Test target) {
		// TODO Auto-generated method stub
		if (this.korean > target.korean)
			return -1;
		else if (this.korean < target.korean)
			return 1;
		else {
			if (this.english < target.english)
				return -1;
			else if (this.english > target.english)
				return 1;
			else {
				if (this.math > target.math)
					return -1;
				else if (this.math < target.math)
					return 1;
				else
					return this.name.compareTo(target.name);
			}
		}
	}
}
