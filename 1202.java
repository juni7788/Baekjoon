import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
		
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		
		String [] cmds = br.readLine().split(" ");
		int N = Integer.parseInt(cmds[0]);
		int K = Integer.parseInt(cmds[1]);
		long total = 0;
		
		PriorityQueue <Precious> prq_pr = new PriorityQueue <Precious> ();
		PriorityQueue <Integer> bag = new PriorityQueue <Integer> ();
		
		for (int i = 0; i < N; i++) {
			cmds = br.readLine().split(" ");
			prq_pr.add(new Precious(Integer.parseInt(cmds[0]), Integer.parseInt(cmds[1])));
		}
		
		for (int i = 0; i < K; i++)
			bag.add(Integer.parseInt(br.readLine()));

		PriorityQueue <Integer> prq = new PriorityQueue <Integer> (new Comparator <Integer> () {
			@Override
			public int compare(Integer one, Integer target) {
				// TODO Auto-generated method stub
				return target - one;
			}
		});
		
		while (!bag.isEmpty()) {
			int size = bag.poll();
			while (!prq_pr.isEmpty()) {
				Precious precious = prq_pr.peek();
				int weight = precious.weight;
				
				if (weight <= size)
					prq.add(prq_pr.poll().value);
				else
					break;
			}
			if (!prq.isEmpty())
				total += prq.poll();
		}
		
		System.out.println(total);
	}
}

class Precious implements Comparable <Precious>{
	int weight, value;
	
	Precious (int weight, int value) {
		this.weight = weight;
		this.value  = value;
	}

	@Override
	public int compareTo(Precious argv) {
		// TODO Auto-generated method stub
		return this.weight - argv.weight;
	}
}
