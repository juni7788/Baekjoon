import java.util.LinkedList;
import java.util.Scanner;

public class 3190 {

	static int N, K, answer = 1;
	static int [][] map, move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int [] dir = new int [20000];
	
	public static void main(String args[]) {
	   Scanner scanner = new Scanner (System.in);

	   N = scanner.nextInt();
	   K = scanner.nextInt();
	   map = new int [N][N];
	   
	   for (int i = 0; i < K; i++) {
		   int x = scanner.nextInt();
		   int y = scanner.nextInt();
		   map[x-1][y-1] = 1;
	   }
	   
	   K = scanner.nextInt();
	   
	   scanner.nextLine();
	   for (int i = 0; i < K; i++) {
		   String [] cmds = scanner.nextLine().split(" ");
		   int time = Integer.parseInt(cmds[0]);
		   
		   if (cmds[1].charAt(0) == 'D')
			   dir[time] = 1;
		   else
			   dir[time] = 3;
	   }
	   
	   LinkedList <Point> snail = new LinkedList <Point> ();
	   snail.add(new Point (0, 0));
	   map[0][0] = 9;
	   int go = 0;
	   
	   for ( ; ; answer++) {
		   Point head = snail.getLast();
		   Point tail = snail.getFirst();
		   
		   int head_x = head.x;
		   int head_y = head.y;
		   head_x += move[go][0];
		   head_y += move[go][1];
		   
		   if (check(head_x, head_y))
			   break;
		   
		   if (map[head_x][head_y] != 1) {
			   map[tail.x][tail.y] = 0;
			   snail.removeFirst();
		   }
		   
		   snail.addLast(new Point (head_x, head_y));
		   map[head_x][head_y] = 9;
		   
		   if (dir[answer] == 0)
			   continue;
		   else
			   go = (go+dir[answer])%4;
	   }
	   
	   System.out.println(answer);
   }
	
	static boolean check (int x, int y) {
		if (0 > x || x >= N || 0 > y || y >= N || map[x][y] == 9)
			return true;
		return false;
	}
}

class Point {
	int x, y;
	
	Point (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
