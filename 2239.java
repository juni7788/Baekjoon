import java.util.Scanner;

public class 2239 {
	
	static int [][] map = new int[9][9];
	static int [][] answer = new int[9][9];
	static boolean stop = false;
	static StringBuilder builder = new StringBuilder ("");
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);

		for (int i = 0; i < 9; i++) {
			String str = scanner.nextLine();
			for (int j = 0; j < 9; j++)
				map[i][j] = str.charAt(j) - '0';
		}

		dfs (0, 0);
	}
	
	static void dfs (int x, int y) {
		if (stop)
			return ;
		if (x == 9 && y == 0) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++)
					builder.append(map[i][j]);
				builder.append("\n");
			}
			System.out.println(builder);
			System.exit(0);
		}
		
		if (map[x][y] != 0) {
			if (y == 8)
				dfs (x+1, 0);
			else
				dfs (x, y+1);
		}
		else {
			for (int i = 1; i <= 9; i++) {
				boolean flag = true;
				for (int j = 0; j < 9; j++) {
					if (map[x][j] == i || map[j][y] == i) {
						flag = false;
						break;
					}
				}
				if (!flag)
					continue;
				
				int start_x = x/3;
				int start_y = y/3;
				start_x = 3*start_x;
				start_y = 3*start_y;

				for (int j = 0; j < 3; j++) {
					for (int k = 0; k < 3; k++) {
						if (map[start_x+j][start_y+k] == i) {
							flag = false;
							break;
						}
					}
				}
				if (!flag)
					continue;
				
				map[x][y] = i;
				if (y == 8)
					dfs (x+1, 0);
				else
					dfs (x, y+1);
				map[x][y] = 0;
			}
		}
	}
}