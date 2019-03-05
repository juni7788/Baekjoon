import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 15955 {
	
	static PriorityQueue <ADJ> points = new PriorityQueue <ADJ> ();
	static ArrayList <Point> x_sort = new ArrayList <Point> ();
	static ArrayList <Point> y_sort = new ArrayList <Point> ();
	static Point [] checkpoint;
	static String [] answer;
	static int [] parent, rank;
	static int N, Q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		StringBuilder builder = new StringBuilder ("");
		
		String [] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		Q = Integer.parseInt(str[1]);
		
		parent = new int [N];
		rank   = new int [N];
		checkpoint = new Point  [N];
		answer     = new String [Q];

		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			int x = Integer.parseInt(str[0]);
			int y = Integer.parseInt(str[1]);
			Point point = new Point (i, x, y);
			checkpoint[i] = point;
			x_sort.add(point);
			y_sort.add(point);
			parent[i] = i;
		}
		
		Sorting();
		
		PriorityQueue <Query> prq_query = new PriorityQueue <Query> ();
		
		for (int i = 0; i < Q; i++) {
			str = br.readLine().split(" ");
			int one = Integer.parseInt(str[0])-1;
			int target = Integer.parseInt(str[1])-1;
			int hp = Integer.parseInt(str[2]);
			prq_query.add(new Query(i, one, target, hp));
		}
		
		while (!prq_query.isEmpty()) {
			Query query = prq_query.poll();
			int index = query.index;
			int one = query.one;
			int target = query.target;
			int hp = query.hp;
			
			if (parent[one] == parent[target]) {
				answer[index] = "YES";
				continue;
			}
			
			while (!points.isEmpty() && points.peek().diff <= hp) {
				ADJ adj = points.poll();
				int now1 = adj.one;
				int now2 = adj.target;
				
				merge (now1, now2);
			}
			
			if (find(one) == find(target))
				answer[index] = "YES";
			else
				answer[index] = "NO";
		}
		
		for (int i = 0; i < Q; i++)
			builder.append(answer[i] + "\n");
		
		System.out.println(builder);
	}
	
	static int find (int index) {
		if (index == parent[index])
			return index;
		
		return parent[index] = find(parent[index]);
	}
	
	static void merge (int one, int target) {
		one = find (one);
		target = find (target);
		
		if (one == target)
			return ;
		
		if (rank[one] > rank[target]) {
			int temp = one;
			one = target;
			target = temp;
		}
		
		parent[one] = target;
		
		if (rank[one] == rank[target])
			rank[target] += 1;
	}
	
	static void Sorting () {
		x_sort.sort(new Comparator <Point> () {

			@Override
			public int compare(Point one, Point target) {
				// TODO Auto-generated method stub
				return one.x - target.x;
			}
			
		});

		y_sort.sort(new Comparator <Point> () {

			@Override
			public int compare(Point one, Point target) {
				// TODO Auto-generated method stub
				return one.y - target.y;
			}
			
		});
		
		for (int i = 0; i < N-1; i++) {
			Point one = x_sort.get(i);
			Point target = x_sort.get(i+1);
			points.add(new ADJ (one.index, target.index, target.x-one.x));
			
			one = y_sort.get(i);
			target = y_sort.get(i+1);
			points.add(new ADJ (one.index, target.index, target.y-one.y));
		}
	}
}

class Point {
	int index, x, y;
	
	Point (int index, int x, int y) {
		this.index = index;
		this.x = x;
		this.y = y;
	}
}

class ADJ implements Comparable <ADJ>{
	int one, target, diff;

	ADJ (int one, int target, int diff) {
		this.one = one;
		this.target = target;
		this.diff = diff;
	}
	
	@Override
	public int compareTo(ADJ target) {
		// TODO Auto-generated method stub
		return this.diff - target.diff;
	}
}

class Query implements Comparable <Query>{
	int index, one, target, hp;

	Query (int index, int one, int target, int hp) {
		this.index = index;
		this.one = one;
		this.target = target;
		this.hp = hp;
	}
	
	@Override
	public int compareTo(Query target) {
		// TODO Auto-generated method stub
		return this.hp - target.hp;
	}
}
