import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class 1764 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder ("");

		String [] cmds = br.readLine().split(" ");
		int N = Integer.parseInt(cmds[0]);
		int M = Integer.parseInt(cmds[1]);

		Set <String> set = new HashSet <String> ();
		Set <String> cnt = new HashSet <String> ();
		
		for (int i = 0; i < N; i++)
			set.add(br.readLine());
		
		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			
			if (set.contains(str))
				cnt.add(str);
		}
		
		Iterator <String> itr = cnt.iterator();
		PriorityQueue <String> prq = new PriorityQueue <String> ();
		
		while (itr.hasNext())
			prq.offer(itr.next());

		while (!prq.isEmpty())
			builder.append(prq.poll() + "\n");
		
		System.out.println(cnt.size());
		System.out.println(builder);
	}
}
