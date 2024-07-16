package class133;

// 课上讲述高斯消元细节的测试

public class Test {

	public static int MAXN = 101;

	public static double[][] mat = new double[MAXN][MAXN];

	public static int n;

	public static double sml = 1e-7;

	public static void gauss(int n) {
		for (int i = 1; i <= n; i++) {
			// 如果想严格区分矛盾、多解、唯一解，一定要这么写
			int max = i;
			for (int j = 1; j <= n; j++) {
				if (j < i && Math.abs(mat[j][j]) >= sml) {
					continue;
				}
				if (Math.abs(mat[j][i]) > Math.abs(mat[max][i])) {
					max = j;
				}
			}
			swap(i, max);
			if (Math.abs(mat[i][i]) >= sml) {
				double tmp = mat[i][i];
				for (int j = i; j <= n + 1; j++) {
					mat[i][j] /= tmp;
				}
				for (int j = 1; j <= n; j++) {
					if (i != j) {
						double rate = mat[j][i] / mat[i][i];
						for (int k = i; k <= n + 1; k++) {
							mat[j][k] -= mat[i][k] * rate;
						}
					}
				}
			}
		}
	}

	public static void swap(int a, int b) {
		double[] tmp = mat[a];
		mat[a] = mat[b];
		mat[b] = tmp;
	}

	public static void print(int n) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n + 1; j++) {
				System.out.printf("%.2f  ", mat[i][j]);
			}
			System.out.println();
		}
		System.out.println("========================");
	}

	public static void main(String[] args) {
		// 唯一解
		// 1*x1 + 2*x2 - 1*x3 = 9
		// 2*x1 - 1*x2 + 2*x3 = 7
		// 1*x1 - 2*x2 + 2*x3 = 0
		System.out.println("唯一解");
		mat[1][1] = 1; mat[1][2] = 2; mat[1][3] = -1; mat[1][4] = 9;
		mat[2][1] = 2; mat[2][2] = -1; mat[2][3] = 2; mat[2][4] = 7;
		mat[3][1] = 1; mat[3][2] = -2; mat[3][3] = 2; mat[3][4] = 0;
		gauss(3);
		print(3);
		
		// 多解
		// 1*x1 + 1*x2 = 3
		// 2*x1 + 2*x2 = 6
		System.out.println("多解");
		mat[1][1] = 1; mat[1][2] = 1; mat[1][3] = 3;
		mat[2][1] = 2; mat[2][2] = 2; mat[2][3] = 6;
		gauss(2);
		print(2);
		
		// 矛盾
		// 1*x1 + 1*x2 = 3
		// 2*x1 + 2*x2 = 7
		System.out.println("矛盾");
		mat[1][1] = 1; mat[1][2] = 1; mat[1][3] = 3;
		mat[2][1] = 2; mat[2][2] = 2; mat[2][3] = 7;
		gauss(2);
		print(2);
		
		// 表达式冗余，补充变量个数
		// 1*x1 + 2*x2 - 1*x3 = 9
		// 2*x1 + 4*x2 - 2*x3 = 18
		// 2*x1 - 1*x2 + 2*x3 = 7
		// 1*x1 - 2*x2 + 2*x3 = 0
		System.out.println("表达式冗余");
		mat[1][1] = 1; mat[1][2] = 2; mat[1][3] = -1; mat[1][4] = 0; mat[1][5] = 9;
		mat[2][1] = 2; mat[2][2] = 4; mat[2][3] = -2; mat[2][4] = 0; mat[2][5] = 18;
		mat[3][1] = 2; mat[3][2] = -1; mat[3][3] = 2; mat[3][4] = 0; mat[3][5] = 7;
		mat[4][1] = 1; mat[4][2] = -2; mat[4][3] = 2; mat[4][4] = 0; mat[4][5] = 0;
		gauss(4);
		print(4);
		
		// 表达式不足，补充表达式
		// 1*x1 + 2*x2 - 1*x3 = 5
		// 2*x1 + 2*x2 - 1*x3 = 8
		System.out.println("表达式不足");
		mat[1][1] = 1; mat[1][2] = 2; mat[1][3] = -1; mat[1][4] = 5;
		mat[2][1] = 2; mat[2][2] = 2; mat[2][3] = -1; mat[2][4] = 8;
		mat[3][1] = 0; mat[3][2] = 0; mat[3][3] = 0;  mat[3][4] = 0;
		gauss(3);
		print(3);
		
		// 区分矛盾、多解、唯一解
		// 0*x1 + 2*x2 = 3
		// 0*x1 + 0*x2 = 0
		System.out.println("正确区分矛盾、多解、唯一解");
		mat[1][1] = 0; mat[1][2] = 2; mat[1][3] = 3;
		mat[2][1] = 0; mat[2][2] = 0; mat[2][3] = 0;
		gauss(2);
		print(2);
	}

}