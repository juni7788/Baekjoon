import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class 16235 {
	
	static Scanner scanner = new Scanner(System.in);
	static int [] move_x = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int [] move_y = {-1, 0, 1, -1, 1, -1, 0, 1};
	static int N, M, K;
	static int [][] flourish, adder;
	static LinkedList <Integer> [][] tree;
	
	public static void main(String args[]) {
		N = scanner.nextInt();
		M = scanner.nextInt();
		K = scanner.nextInt();
		
		flourish = new int [N][N];
		adder = new int [N][N];
		tree = new LinkedList [N][N];

		set_default();
		
		for (int year = 0; year < K; year++) {
			// spring & summer
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					LinkedList <Integer> new_tree = new LinkedList <Integer> ();
					LinkedList <Integer> cur = tree[i][j];
				
					int death = 0;
					
					for (int index = 0; index < cur.size(); index++) {
						int age = cur.get(index);
						if (age <= flourish[i][j]) {
							new_tree.add(age+1);
							flourish[i][j] -= age;
						} // summer
						else
							death += age/2;
					}
					flourish[i][j] += death;
					
					tree[i][j].clear();
					for (Integer age: new_tree)
						tree[i][j].add(age);
				}
			}
		
			// autumn & winter
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					flourish[i][j] += adder[i][j];
					int count = 0;
					for (Integer age: tree[i][j]) {
						if (age % 5 == 0)
							count += 1;
					}
					
					if (count == 0)
						continue;
					
					for (int k = 0; k < 8; k++) {
						int x = i + move_x[k];
						int y = j + move_y[k];
						
						if (0 <= x && x < N && 0 <= y && y < N) {
							for (int c = 0; c < count; c++) {
								tree[x][y].addFirst(1);
							}
						}
					}
				}
			}
		}

		int total = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				total += tree[i][j].size();
		}
		
		System.out.println(total);
	}
	
	static void set_default () {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				adder[i][j] = scanner.nextInt();
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				flourish[i][j] = 5;
				tree[i][j] = new LinkedList <Integer> ();
			}
		}
	
		for (int i = 0; i < M; i++) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			int age = scanner.nextInt();
			tree[x-1][y-1].add(age);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				Collections.sort(tree[i][j]);
		}
	}
}