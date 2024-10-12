import java.util.Scanner;

public class Main {

    public static final int MAX_N = 10;
    public static final int MAX_M = 5; 

    public static int n, m, c;
    public static int ans, maxVal; 

    public static int[][] weight = new int[MAX_N][MAX_N];
    public static int[] a = new int[MAX_M];

    public static void findMaxSum(int currIdx, int currWeight, int currVal) {
        if (currIdx == m) {
            // 무게의 합이 c를 넘으면 안됨
            if (currWeight <= c) {
                maxVal = Math.max(maxVal, currVal);
            }
            return;
        }

        // a[currIdx]에 있는 숫자를 선택하지 않은 경우 
        findMaxSum(currIdx+1, currWeight, currVal);

        // a[currIdx]에 있는 숫자를 선택한 경우
        findMaxSum(currIdx+1, currWeight+a[currIdx], currVal+a[currIdx]*a[currIdx]);
    }

    public static int findMax(int sx, int sy) {
        // 행을 기준으로만 확인하면 되므로 a배열을 이용해 확인
        for (int i=sy; i<=sy+m-1; i++) {
            a[i-sy] = weight[sx][i];
        }

        // 2^m개의 조합에 대한 최적의 값을 구함
        maxVal = 0;
        findMaxSum(0, 0, 0);
        return maxVal;
    }

    // [a, b], [c, d]일 때 선분이 겹치는지 확인
    public static boolean intersect(int a, int b, int c, int d) {
        return !(b<c || d<a); 
    }

    public static boolean possible(int sx1, int sy1, int sx2, int sy2) {
        // 격자의 범위를 넘는 경우: 불가능
        if (sy1+m-1>=n || sy2+m-1>=n) {
            return false; 
        }
        
        // 행이 다르다면 겹칠 수 없음: 무조건 가능
        if (sx1 != sx2) {
            return true; 
        }

        // 두 구간끼리 겹칠 때: 불가능
        if (intersect(sy1, sy1+m-1, sy2, sy2+m-1)) {
            return false;
        }

        // 행이 같으면서 구간끼리 겹치지 않을 때: 가능
        return true; 
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        n = sc.nextInt();
        m = sc.nextInt();
        c = sc.nextInt();

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                weight[i][j] = sc.nextInt();
            }
        }

        for (int sx1=0; sx1<n; sx1++) {
            for (int sy1=0; sy1<n; sy1++) {
                for (int sx2=0; sx2<n; sx2++) {
                    for (int sy2=0; sy2<n; sy2++) {
                        if (possible(sx1, sy1, sx2, sy2)) {
                            ans = Math.max(ans, findMax(sx1, sy1)+findMax(sx2, sy2));
                        }
                    }
                }
            }
        }

        System.out.println(ans); 
    }
    
}