import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 4659 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		StringBuilder sb = new StringBuilder ("");
		
		while (true) {
			String str = br.readLine();
			
			if (str.equals("end"))
				break;
			
			char [] chs = str.toCharArray();
			boolean [] flag = new boolean [3];
			flag[1] = flag[2] = true;
			
			for (int i = 0; i < chs.length; i++) {
				if (Is_vowel (chs[i])) {
					flag[0] = true;
					if (i+2 < chs.length && Is_vowel (chs[i+1]) && Is_vowel (chs[i+2])) {
						flag[1] = false;
						break;
					}
				}
				else {
					if (i+2 < chs.length && !Is_vowel (chs[i+1]) && !Is_vowel (chs[i+2])) {
						flag[1] = false;
						break;
					}
				}
				
				if (chs[i] == 'o' || chs[i] =='e')
					continue;
				else {
					if (i+1 < chs.length && chs[i] == chs[i+1]) {
						flag[2] = false;
						break;
					}
				}
			}
			
			sb.append("<" + str + "> is ");
			if (flag[0] && flag[1] && flag[2])
				sb.append("acceptable.\n");
			else
				sb.append("not acceptable.\n");
		}
		
		System.out.println(sb);
	}
	
	static boolean Is_vowel (char ch) {
		if (ch == 'a' || ch == 'i' || ch == 'e' || ch == 'o' || ch == 'u')
			return true;
		else
			return false;
	}
}
