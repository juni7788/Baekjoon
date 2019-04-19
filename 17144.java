import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class 17144 {

	static int N, M;
	static int [][] map;
	static int [] AC;
	static int [][] move = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		String [] cmds = br.readLine().split(" ");
		
		N = Integer.parseInt(cmds[0]);
		M = Integer.parseInt(cmds[1]);
		map = new int [N][M];
		AC = new int [2];
		int T = Integer.parseInt(cmds[2]);
		
		for (int i = 0; i < N; i++) {
			cmds = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(cmds[j]);
				if (num == -1) {
					if (AC[0] == 0)
						AC[0] = i; 
					else
						AC[1] = i;
				}
				map[i][j] = num;
			}
		}
		
		for (int time = 0; time < T; time++) {
			int [][] adder = new int [N][M];
			
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < M; y++) {
					if (map[x][y] <= 0)
						continue;
					int ext = map[x][y]/5;
					
					for (int k = 0; k < 4; k++) {
						int nx = x + move[k][0];
						int ny = y + move[k][1];
						
						if (0 <= nx && nx < N && 0 <= ny && ny < M) {
							if (map[nx][ny] != -1) {
								adder[nx][ny] += ext;
								map[x][y] -= ext;
							}
						}
					}
				}
			}
			
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < M; y++)
					map[x][y] += adder[x][y];
			}
			
			if (AC[0] == 0)
				continue;
			
			rotate_counter (AC[0], 1, 0);
			rotate_clock (AC[1], 1, 0);
		}
		
		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0)
					ans += map[i][j];
			}
		}
		
		System.out.println(ans);
	}
	
	static void rotate_counter (int x, int y, int tmp) {
		if (x == AC[0] && y == 0)
			return ;
		
		int ntmp = map[x][y];
		map[x][y] = tmp;
		
		if (y == 0)
			rotate_counter (x+1, y, ntmp);
		else if (x == 0)
			rotate_counter (x, y-1, ntmp);
		else if (y == M-1)
			rotate_counter (x-1, y, ntmp);
		else if (x == AC[0])
			rotate_counter (x, y+1, ntmp);
	}
	
	static void rotate_clock (int x, int y, int tmp) {
		if (x == AC[1] && y == 0)
			return ;
		
		int ntmp = map[x][y];
		map[x][y] = tmp;
		
		if (y == 0)
			rotate_clock (x-1, y, ntmp);
		else if (x == N-1)
			rotate_clock (x, y-1, ntmp);
		else if (y == M-1)
			rotate_clock (x+1, y, ntmp);
		else if (x == AC[1])
			rotate_clock (x, y+1, ntmp);
	}
}
