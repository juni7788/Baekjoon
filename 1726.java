import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 1726 {
	
	static int N, M, answer;
	static boolean [][][] visited;
	static int [][] map;
	static int [][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		N = scanner.nextInt();
		M = scanner.nextInt();
		map = new int [N][M];
		visited = new boolean [N][M][4];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				map[i][j] = scanner.nextInt();
		}
		
		int start_x = scanner.nextInt()-1;
		int start_y = scanner.nextInt()-1;
		int start_dir = scanner.nextInt()-1;
		int end_x = scanner.nextInt()-1;
		int end_y = scanner.nextInt()-1;
		int end_dir = scanner.nextInt()-1;
		
		Queue <Robot> queue = new LinkedList <Robot> ();
		queue.offer(new Robot (start_x, start_y, start_dir, 0));
		visited[start_x][start_y][start_dir] = true;
		
		while (!queue.isEmpty()) {
			Robot robot = queue.poll();
			int now_x = robot.x;
			int now_y = robot.y;
			int dir = robot.dir;
			int count = robot.count;

			if (now_x == end_x && now_y == end_y && dir == end_dir) {
				answer = count;
				break;
			}

			if (dir == 0 || dir == 1) {
				if (!visited[now_x][now_y][2])
					queue.offer(new Robot(now_x, now_y, 2, count+1));
				if (!visited[now_x][now_y][3])
					queue.offer(new Robot(now_x, now_y, 3, count+1));
				visited[now_x][now_y][2] = visited[now_x][now_y][3] = true;
			}
			else {
				if (!visited[now_x][now_y][0])
					queue.offer(new Robot(now_x, now_y, 0, count+1));
				if (!visited[now_x][now_y][1])
					queue.offer(new Robot(now_x, now_y, 1, count+1));
				visited[now_x][now_y][0] = visited[now_x][now_y][1] = true;
			}
			
			int x = now_x + move[dir][0];
			int y = now_y + move[dir][1];
			if (!Is_border(x, y))
				continue;
			if (map[x][y] == 1)
				continue;
			if (!visited[x][y][dir]) {
				visited[x][y][dir] = true;
				queue.offer(new Robot(x, y, dir, count+1));
			}
			x += move[dir][0];
			y += move[dir][1];
			if (!Is_border(x, y))
				continue;
			if (map[x][y] == 1)
				continue;
			if (!visited[x][y][dir]) {
				visited[x][y][dir] = true;
				queue.offer(new Robot(x, y, dir, count+1));
			}
			x += move[dir][0];
			y += move[dir][1];
			if (!Is_border(x, y))
				continue;
			if (map[x][y] == 1)
				continue;
			if (!visited[x][y][dir]) {
				visited[x][y][dir] = true;
				queue.offer(new Robot(x, y, dir, count+1));
			}
		}
		
		System.out.println(answer);
	}
	
	static boolean Is_border (int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < M)
			return true;
		return false;
	}
}

class Robot {
	int x, y, dir, count;
	
	Robot (int x, int y, int dir, int count) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.count = count;
	}
}
