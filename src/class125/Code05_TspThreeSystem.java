package class125;

// https://acm.hdu.edu.cn/showproblem.php?pid=3001
// https://acm.hdu.edu.cn/viewcode.php?rid=39366218

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Code05_TspThreeSystem {

	public static int MAXN = 10;

	public static int MAXS = (int) Math.pow(3, MAXN);

	public static int[][] graph = new int[MAXN][MAXN];

	public static int[] bit = new int[MAXN];

	public static int[][] tri = new int[MAXS][MAXN];

	public static int[][] dp = new int[MAXN][MAXS];

	public static int n;

	public static int m;

	public static int maxs;

	public static void build() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				graph[i][j] = Integer.MAX_VALUE;
			}
		}
		for (int i = 0, cur = 1; i < n; i++, cur *= 3) {
			bit[i] = cur;
		}
		maxs = (int) Math.pow(3, n);
		for (int s = 0; s < maxs; s++) {
			for (int i = 0, t = s; i < n; i++, t /= 3) {
				tri[s][i] = t % 3;
			}
		}
		for (int i = 0; i < n; i++) {
			for (int s = 0; s < maxs; s++) {
				dp[i][s] = Integer.MAX_VALUE;
			}
		}
	}

	public static int dp() {
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			dp[i][bit[i]] = 0;
		}
		for (int s = 0; s < maxs; s++) {
			boolean visitAll = true;
			for (int i = 0; i < n; i++) {
				if (tri[s][i] == 0) {
					visitAll = false;
					continue;
				}
				for (int k = 0; k < n; k++) {
					if (dp[k][s - bit[i]] != Integer.MAX_VALUE && graph[k][i] != Integer.MAX_VALUE) {
						dp[i][s] = Math.min(dp[i][s], dp[k][s - bit[i]] + graph[k][i]);
					}
				}
			}
			if (visitAll) {
				for (int i = 0; i < n; i++) {
					ans = Math.min(ans, dp[i][s]);
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			n = (int) in.nval;
			in.nextToken();
			m = (int) in.nval;
			build();
			for (int i = 1, u, v, w; i <= m; i++) {
				in.nextToken();
				u = (int) in.nval - 1;
				in.nextToken();
				v = (int) in.nval - 1;
				in.nextToken();
				w = (int) in.nval;
				if (w < graph[u][v]) {
					graph[u][v] = graph[v][u] = w;
				}
			}
			int ans = dp();
			out.println(ans == Integer.MAX_VALUE ? -1 : ans);
		}
		out.flush();
		out.close();
		br.close();
	}

}