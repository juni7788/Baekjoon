import java.util.ArrayList;
import java.util.Scanner;

public class 1315 {

	static int answer = 0;
	static int N, END = 1000;
	static int [][] dp;
	static boolean [] visited;
	static ArrayList <Quest> list = new ArrayList <Quest> ();
	
	public static void main(String[] args) {	
		Scanner scanner = new Scanner(System.in);

		N = scanner.nextInt();
		dp = new int [END][END];
		visited = new boolean [N];
		
		for (int i = 0; i < N; i++) {
			int str   = scanner.nextInt();
			int intel = scanner.nextInt();
			int point = scanner.nextInt();
			list.add(new Quest (i, str, intel, point));
		}

		System.out.println(RPG (1, 1));
	}
	
	static int RPG (int x, int y) {
		if (dp[x][y] != 0)
			return dp[x][y];
		
		ArrayList <Integer> adder = new ArrayList <Integer> ();
		int reward = 0, count = 0;
		
		for (Quest quest: list) {
			int index = quest.index;
			int str = quest.str;
			int intel = quest.intel;
			int point = quest.point;
			
			if (str <= x || intel <= y) {
				if (visited[index]) {
					count += 1;
					continue;
				}
				visited[index] = true;
				count += 1;
				reward += point;
				adder.add(index);
			}
		}
		
		int max = count;
		if (reward > 0) {
			for (int point = 0; point <= reward; point++) {
				int a = Math.min(END-1, x+point);
				int b = Math.min(END-1, y + reward - point);
					max = Math.max(max, RPG(a, b));
			}
		}
		
		for (Integer index: adder)
			visited[index] = false;
		dp[x][y] = max;
		return dp[x][y];
	}
}
	
class Quest {
	int index, str, intel, point;
	
	Quest (int index, int str, int intel, int point) {
		this.index = index;
		this.str = str;
		this.intel = intel;
		this.point = point;
	}
}
