import java.util.Scanner;

public class 11947 {
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner (System.in);
		StringBuilder builder = new StringBuilder ("");
		
		int Test = scanner.nextInt();
		
		String str = scanner.nextLine();
		for (int T = 0; T < Test; T++) {
			str = scanner.nextLine();
			String [] splits = str.split("");
			String median = "5";
			
			for (int i = 1; i < splits.length; i++)
				median += "0";
			
			long answer;
			if (Long.parseLong(str) > Long.parseLong(median))
				answer = get_reverse(median);
			else
				answer = get_reverse(str);
			
			builder.append(answer + "\n");
		}
		System.out.println(builder);
	}
	
	static long get_reverse (String now) {
		String [] splits = now.split("");
		String temp = "";
		
		for (int i = 0; i < now.length(); i++)
			temp += 9-Long.parseLong(splits[i]);
		
		return Long.parseLong(now) * Long.parseLong(temp);
	}
}
