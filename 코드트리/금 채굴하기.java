import java.util.Scanner;

public class Main {
    public static final int MAX_N = 20;
    public static final int MAX_M = 10; 

    public static int n, m;

    public static int[][] grid = new int[MAX_N][MAX_N];

    // 채굴에 들어가는 비용
    public static int getArea(int k) {
        return k*k + (k+1)*(k+1);  
    }

    // 주어진 k에 대해 채굴 가능한 금의 개수
    public static int getNumOfGold(int x, int y, int k) {
        int numOfGold = 0;

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                // **k보다 작은 마름모 범위에서 금의 개수 확인
                if (Math.abs(i-x) + Math.abs(j-y) <= k) { 
                    numOfGold += grid[i][j];
                }
            }
        }
        return numOfGold;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();  // 금의 가격, 채굴 비용: k*k + (k+1)*(k+1)

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        int ans = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                // *2*(n-1) 범위: (0, 0)~(n-1, n-1)를 포함하기 위한 k값 설정
                // *등호 주의
                for (int k=0; k <= 2*(n-1); k++) {  
                    int numOfGold = getNumOfGold(i, j, k);

                    if (numOfGold*m >= getArea(k)) {  // 금의 가치 >= 채굴비용일 때, 비교
                        ans = Math.max(ans, numOfGold);
                    }
                }
            }
        }
        
        System.out.println(ans); 
    }
}