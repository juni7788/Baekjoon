import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 16675 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st = new StringTokenizer (br.readLine());
		
		char m1 = st.nextToken().charAt(0);
		char m2 = st.nextToken().charAt(0);
		char t1 = st.nextToken().charAt(0);
		char t2 = st.nextToken().charAt(0);
		
		if (m1 == m2) {
			if (t1 == t2) {
				int num = RSP (m1, t1);

				if (num == 0)
					System.out.println("MS");
				else if (num == 1)
					System.out.println("TK");
				else
					System.out.println("?");
			}
			else {
				int num1 = RSP (m1, t1);
				int num2 = RSP (m1, t2);
				
				if (num1 == 1 || num2 == 1)
					System.out.println("TK");
				else
					System.out.println("?");
			}
		}
		else if (t1 == t2){
			int num1 = RSP (m1, t1);
			int num2 = RSP (m2, t1);

			if (num1 == 0 || num2 == 0)
				System.out.println("MS");
			else
				System.out.println("?");
		}
		else
			System.out.println("?");
	}
	
	static int RSP (char m, char t) {
		if (m == 'R') {
			if (t == 'P')
				return 1;
			else if (t == 'S')
				return 0;
		}
		else if (m == 'S') {
			if (t == 'R')
				return 1;
			else if (t == 'P')
				return 0;
		}
		else if (m == 'P') {
			if (t == 'S')
				return 1;
			else if (t == 'R')
				return 0;
		}
		
		return 2;
	}
}
