import java.util.Scanner;

public class 14980 {
	
	static int N, r, answer;
	static int [][] map;
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner (System.in);

		N = scanner.nextInt();
		r = scanner.nextInt();
		map = new int [N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				map[i][j] = scanner.nextInt();
		}
		
		// down
		for (int j = 0; j < N; j++) {
			int former = map[0][j];
			int count = 0;
			boolean possible = true;
			for (int i = 0; i < N;) {
				int now = map[i][j];
				if (now == former) {
					i += 1;
					count += 1;
				}
				else if (now == former + 1) {
					if (count < r) {
						possible = false;
						break;
					}
					former = now;
					i += 1;
					count = 1;
				}
				else if (now == former - 1) {
					boolean flag = true;
					for (int k = 0; k < r; k++) {
						if (i+k == N || map[i+k][j] != now) {
							flag = false;
							break;
						}
					}
					if (!flag) {
						possible = false;
						break;
					}
					former = now;
					i += r;
					count = 0;
				}
				else {
					possible = false;
					break;
				}
			}
			if (possible)
				answer += 1;
		}
		
		// right
		for (int i = 0; i < N; i++) {
			int former = map[i][0];
			int count = 0;
			boolean possible = true;
			for (int j = 0; j < N;) {
				int now = map[i][j];
				if (now == former) {
					j += 1;
					count += 1;
				}
				else if (now == former + 1) {
					if (count < r) {
						possible = false;
						break;
					}
					former = now;
					j += 1;
					count = 1;
				}
				else if (now == former - 1) {
					boolean flag = true;
					for (int k = 0; k < r; k++) {
						if (j+k == N || map[i][j+k] != now) {
							flag = false;
							break;
						}
					}
					if (!flag) {
						possible = false;
						break;
					}
					former = now;
					j += r;
					count = 0;
				}
				else {
					possible = false;
					break;
				}
			}
			if (possible)
				answer += 1;
		}
		
		System.out.println(answer);
	}
}
