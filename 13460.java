import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 13460 {

	static int N, M, answer = 1000;
	static int end_x, end_y;
	static char [][] map;
	static int [][] move = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
	
		String [] cmds = br.readLine().split(" ");
		N = Integer.parseInt(cmds[0]);
		M = Integer.parseInt(cmds[1]);
		map = new char [N][M];
		
		int red_x = -1, red_y = -1, blue_x = -1, blue_y = -1;
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				char ch = str.charAt(j);
				map[i][j] = ch;
				if (ch == 'R') {
					red_x = i;
					red_y = j;
				}
				if (ch == 'B') {
					blue_x = i;
					blue_y = j;
				}
				if (ch == 'O') {
					end_x = i;
					end_y = j;
				}
			}
		}
		
		dfs (new Point (red_x, red_y), new Point (blue_x, blue_y), 1, 213);
		
		if (answer != 1000)
			System.out.println(answer);
		else
			System.out.println(-1);
	}
	
	static void dfs (Point red, Point blue, int turn, int dir) {
		if (turn > 10)
			return ;
		
		for (int i = 0; i < 4; i++) {
			if (i == dir)
				continue;
			int red_x = red.x, red_y = red.y, blue_x = blue.x, blue_y = blue.y;
			int x = red_x, y = red_y, long_red = 0, long_blue = 0;
			boolean flag_red = false, flag_blue = false;
			
			for (; ; long_red++) {
				if (x == end_x && y == end_y) {
					flag_red = true;
					break;
				}
				if (map[x][y] == '#') {
					red_x = x - move[i][0];
					red_y = y - move[i][1];
					break;
				}
				x += move[i][0];
				y += move[i][1];
			}
			
			x = blue_x; y = blue_y;
			for (; ; long_blue++) {
				if (x == end_x && y == end_y) {
					flag_blue = true;
					break;
				}
				if (map[x][y] == '#') {
					blue_x = x - move[i][0];
					blue_y = y - move[i][1];
					break;
				}
				x += move[i][0];
				y += move[i][1];
			}

			if (flag_blue);
			else if (flag_red)
				answer = Math.min(answer, turn);
			else {
				if (red_x == blue_x && red_y == blue_y) {
					if (long_red > long_blue) {
						red_x -= move[i][0];
						red_y -= move[i][1];
					}
					else {
						blue_x -= move[i][0];
						blue_y -= move[i][1];
					}
				}
				dfs (new Point (red_x, red_y), new Point (blue_x, blue_y), turn+1, i%2);
			}
		}
	}
}

class Point {
	int x, y;
	
	Point (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
