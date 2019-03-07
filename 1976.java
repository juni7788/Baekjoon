import java.util.Scanner;

public class 1976 {
	
	static int N, M;
	static int [] rank, root;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);

		N = scanner.nextInt();
		M = scanner.nextInt();
		rank = new int [N];
		root = new int [N];
		
		for (int i = 0; i < N; i++)
			root[i] = i;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int num = scanner.nextInt();
				if (num == 1)
					join(i, j);
			}
		}

		int num = root[scanner.nextInt()-1];
		boolean flag = true;
		
		for (int i = 1; i < M; i++) {
			int now = scanner.nextInt()-1;
			if (num != root[now]) {
				flag = false;
				break;
			}
		}
		
		if (flag)
			System.out.println("YES");
		else
			System.out.println("NO");
	}
	
	static int find (int x) {
		if (x == root[x])
			return x;
		return root[x] = find(root[x]);
	}
	
	static void join (int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x == y)
			return ;
		
		if (rank[x] > rank[y]) {
			int temp = x;
			x = y;
			y = temp;
		}
		
		root[x] = y;
		
		if (rank[x] == rank[y])
			rank[y] += 1;
	}
}
