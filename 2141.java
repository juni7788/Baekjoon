import java.math.BigInteger;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 2141 {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		
		int N = scanner.nextInt();
		BigInteger sum = BigInteger.ZERO;
		PriorityQueue <Pair> prq = new PriorityQueue <Pair> ();
		
		for (int i = 0; i < N; i++) {
			long index = scanner.nextLong();
			long num   = scanner.nextLong();
			sum = sum.add(BigInteger.valueOf(num));
			prq.add(new Pair(index, num));
		}
		
		BigInteger temp = BigInteger.ZERO;
		BigInteger bord = sum.divide(BigInteger.valueOf(2));
		if (!sum.subtract(bord).equals(bord))
			bord = bord.add(BigInteger.ONE);		
		
		long answer = 0;
		while (!prq.isEmpty()) {
			Pair pair = prq.poll();
			temp = temp.add(BigInteger.valueOf(pair.num));
			
			if (temp.compareTo(bord) >= 0) {
				answer = pair.index;
				break;
			}
		}
		
		System.out.println(answer);
	}
}

class Pair implements Comparable <Pair>{
	long index, num;
	
	Pair (long index, long num) {
		this.index = index;
		this.num   = num;
	}

	@Override
	public int compareTo(Pair target) {
		// TODO Auto-generated method stub
		if (this.index < target.index)
			return -1;
		else if (this.index > target.index)
			return 1;
		else
			return 0;
	}
}
