import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 1941 {

	static boolean [][] arr = new boolean [5][5];
	static boolean [][] visited = new boolean [5][5];
	static int [][] move = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	static int answer = 0;
	
	static int [] comb = new int [7];
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);

		for (int i = 0; i < 5; i++) {
			String str = scanner.nextLine();
			String [] splits = str.split("");
			for (int j = 0; j < 5; j++) {
				if (splits[j].equals("S"))
					arr[i][j] = true;
				else
					arr[i][j] = false;
			}
		}

		combination (0, 0);
		System.out.println(answer);
	}
	
	static void check () {
		Pair [] pairs = new Pair [7];
		boolean [] visited = new boolean [7];
		int count = 0;
		
		for (int i = 0; i < 7 ; i++){
			pairs[i] = new Pair (comb[i]/5, comb[i]%5);
			if (arr[pairs[i].x][pairs[i].y])
				count += 1;
		}

		if (count < 4)
			return ;
		
		Queue <Pair> queue = new LinkedList <Pair> ();
		count = 1;
		visited[0] = true;
		queue.add(pairs[0]);
		
		while (!queue.isEmpty()) {
			Pair pair = queue.poll();
			
			for (int i = 0; i < 7; i++) {
				if (visited[i])
					continue;
				
				if (adjacent(pair, pairs[i])) {
					count += 1;
					visited[i] = true;
					queue.add(pairs[i]);
				}
			}
		}
		
		if (count == 7)
			answer += 1;
	}
	
	static boolean adjacent (Pair pair1, Pair pair2) {
		int distance = Math.abs(pair1.x-pair2.x) + Math.abs(pair1.y-pair2.y);
		
		if (distance == 1)
			return true;
		else
			return false;
	}
	
	static void combination (int now, int count) {
		if (count == 7) {
			check();
			return ;
		}
		if (now == 25)
			return ;
		
		comb[count] = now;
		combination(now+1, count+1);
		combination(now+1, count);
	}
}

class Pair {
	int x, y;
	
	Pair (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
