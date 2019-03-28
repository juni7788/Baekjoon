import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int R, C, ans;
	static char [][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		String [] str = br.readLine().split(" ");
		
		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		map = new char [R][C];
		
		for (int i = 0; i < R; i++)
			map[i] = br.readLine().toCharArray();
		
		for (int i = 0; i < R; i++)
			ans += dfs (i, 0);
		
		System.out.println(ans);
	}
	
	static int dfs (int x, int y) {
		if (0 > x || x >= R || map[x][y] == 'x')
			return 0;
		map[x][y] = 'x';
		if (y == C-1)
			return 1;
		
		if (dfs (x-1, y+1) == 1)
			return 1;
		if (dfs (x, y+1) == 1)
			return 1;
		if (dfs (x+1, y+1) == 1)
			return 1;
		return 0;
	}
}
