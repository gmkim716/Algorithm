import java.util.Scanner;

public class Main {
    public static final int MAX_NUM = 20;

    public static int n, m;

    public static int[][] grid = new int[MAX_NUM][MAX_NUM];

    public static boolean checkPositive(int x1, int y1, int x2, int y2) {
        for (int i=x1; i<=x2; i++) {
            for (int j=y1; j<=y2; j++) {
                if (grid[i][j] <= 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int getCntOfRect(int x1, int y1, int x2, int y2) {
        int cntOfRect = 0;
        for (int i=x1; i<=x2; i++){
            for (int j=y1; j<=y2; j++) {
                cntOfRect ++;
            }
        }
        return cntOfRect;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        int ans = -1;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                for (int k=i; k<n; k++) {
                    for (int l=j; l<m; l++) {
                        if (checkPositive(i, j, k, l)) {
                            ans = Math.max(ans, getCntOfRect(i, j, k, l));
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }
    
}