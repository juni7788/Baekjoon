import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 15688 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));

		int N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N/2 && i <= 87654; i++) {
			int x = i;
			int y = N-x;
			int ori_x = x, ori_y = y;
			boolean [] vst = new boolean [10];
			boolean flag = true;
			
			while (x != 0) {
				int now = x % 10;
				if (vst[now]) {
					flag = false;
					break;
				}
				vst[now] = true;
				x /= 10;
			}
			if (!flag)
				continue;
			
			while (y != 0) {
				int now = y % 10;
				if (vst[now]) {
					flag = false;
					break;
				}
				vst[now] = true;
				y /= 10;
			}
			
			if (flag) {
				System.out.println(ori_x + " + " + ori_y);
				return ;
			}
		}
		
		System.out.println(-1);
	}
}
