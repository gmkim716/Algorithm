import java.util.Scanner;

public class Main {

    public static final int OFFSET = 1000; 
    public static final int MAX = 2000;

    public static int[][] graph = new int[MAX+1][MAX+1];
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        for (int i=1; i<=2; i++) {
            int x1 = sc.nextInt()+OFFSET;
            int y1 = sc.nextInt()+OFFSET;
            int x2 = sc.nextInt()+OFFSET;
            int y2 = sc.nextInt()+OFFSET;

            for (int r=x1; r<x2; r++) {
                for (int c=y1; c<y2; c++) {
                    graph[r][c] = i; 
                }
            }
        }

        // 최솟값을 구하는 과정에서 초기값을 최대값을 설정해야 함
        int minX = MAX;  // *오류를 방지하기 위해 문제에서의 최댓값을 정확하게 추가해주자 
        int minY = MAX;
        int maxX = 0;
        int maxY = 0;

        boolean flag = false; 

        for (int i=0; i<=MAX; i++) {
            for (int j=0; j<=MAX; j++) {
                if (graph[i][j] == 1) {
                    flag = true; 
                    minX = Math.min(minX, i);
                    minY = Math.min(minY, j);
                    maxX = Math.max(maxX, i);
                    maxY = Math.max(maxY, j);
                }
            }
        }

        int ans;
        if (!flag) {
            ans = 0;
        } else {
            ans = (maxX-minX+1)*(maxY-minY+1);
        }

        System.out.println(ans);
    }
}