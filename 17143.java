import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class 17143 {

	static int N, M;
	static Shark [][] map;
	static int [][] move = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		String [] cmd = br.readLine().split(" ");

		N = Integer.parseInt(cmd[0]);
		M = Integer.parseInt(cmd[1]);
		map = new Shark [N][M];
		int K = Integer.parseInt(cmd[2]);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				map[i][j] = new Shark (0, 0, 0);
		}
		
		for (int i = 0; i < K; i++) {
			cmd = br.readLine().split(" ");
			int x = Integer.parseInt(cmd[0])-1;
			int y = Integer.parseInt(cmd[1])-1;
			int ver = Integer.parseInt(cmd[2]);
			int dir = Integer.parseInt(cmd[3])-1;
			int big = Integer.parseInt(cmd[4]);
			map[x][y] = new Shark (ver, dir, big);
		}
		
		int ans = 0;
		
		for (int j = 0; j < M; j++) {
			for (int i = 0; i < N; i++) {
				if (map[i][j].big != 0) {
					ans += map[i][j].big;
					map[i][j] = new Shark (0, 0, 0);
					break;
				}
			}
			
			Shark [][] adder = new Shark [N][M];
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < M; y++)
					adder[x][y] = new Shark (0, 0, 0);
			}
			
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < M; y++) {
					if (map[x][y].big == 0)
						continue;
					int big = map[x][y].big;
					int dir = map[x][y].dir;
					int ver = map[x][y].ver;
					int nx = x + move[dir][0]*ver;
					int ny = y + move[dir][1]*ver;
					
					while (true) {
						if (nx >= 0 && nx < N && ny >= 0 && ny < M)
							break;
						
						if (nx < 0) {
							nx = -nx;
							dir = 1;
						}
						else if (nx > N-1) {
							nx = 2*(N-1) - nx;
							dir = 0;
						}
						else if (ny < 0) {
							ny = -ny;
							dir = 2;
						}
						else if (ny > M-1) {
							ny = 2*(M-1) - ny;
							dir = 3;
						}
					}
					
					if (map[x][y].big > adder[nx][ny].big)
						adder[nx][ny] = new Shark (ver, dir, big);
				}
			}
			map = adder;
		}
		
		System.out.println(ans);
	}
}

class Shark {
	int ver, dir, big;
	
	Shark (int ver, int dir, int big) {
		this.ver = ver;
		this.dir = dir;
		this.big = big;
	}
}
