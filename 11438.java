import java.util.LinkedList;
import java.util.Scanner;

public class 11438 {

	static LinkedList <Integer> [] adj;
	static int [] depth;
	static int [][] anc;
	static int N;
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner (System.in);
		StringBuilder builder = new StringBuilder ("");
		
		N = scanner.nextInt();
		depth = new int [N];
		anc = new int [N][log2(N)+1];
		adj = new LinkedList [N];
		
		for (int i = 0; i < N; i++)
			adj[i] = new LinkedList <Integer> ();
		
		for (int i = 1; i < N; i++) {
			depth[i] = -1;
			int num1 = scanner.nextInt()-1;
			int num2 = scanner.nextInt()-1;
			adj[num1].add(num2);
			adj[num2].add(num1);
		}
		depth[0] = 0;
		anc[0][0] = 0; // exception
		
		get_tree(0);
		
		int M = scanner.nextInt();
		for (int m = 0; m < M; m++) {
			int num1 = scanner.nextInt()-1;
			int num2 = scanner.nextInt()-1;
			
			// make depth[num1] = depth[num2]
			if (depth[num1] != depth[num2]) {
				if (depth[num1] < depth[num2]){
					int temp = num1;
					num1 = num2;
					num2 = temp;
				}
				
				int diff = depth[num1] - depth[num2];
				while (diff != 0) {
					int level_diff = log2(diff);
					num1 = anc[num1][level_diff];
					diff -= Math.pow(2, level_diff);
				}
			}	
			if (num1 == num2) {
				builder.append(String.format("%d\n", num1+1));
				continue;
			}

			int level = log2(depth[num1]), answer = 0;
			for (int i = level; i >= 0; i--) {
				if (anc[num1][i] != anc[num2][i]) {
					num1 = anc[num1][i];
					num2 = anc[num2][i];
					continue;
				}
				answer = anc[num1][i];
			}
			builder.append(String.format("%d\n", answer+1));
		}
		
		System.out.println(builder);
	}
	
	static int log2(int X) {
		return (int) (Math.log(X)/Math.log(2));
	}
	
	static void get_tree(int index) {
		for (Integer i: adj[index]) {
			if (depth[i] == -1) {
				depth[i] = depth[index]+1;
				anc[i][0] = index;
				dp(i);
				get_tree (i);
			}
		}
	}
	
	static void dp(int index) {
		int level = log2(depth[index]);

		for (int i = 1; i <= level; i++)
			anc[index][i] = anc[anc[index][i-1]][i-1];
	} 
}
