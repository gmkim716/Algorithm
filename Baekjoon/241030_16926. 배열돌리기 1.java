import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;
    static int N, M, R;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (R > 0) {
            rotate();
            R--;
        }

        printArr();
    }

    static void rotate() {
        int layers = Math.min(N, M) / 2;  // 회전하는 층의 수

        for (int l=0; l<layers; l++) {
            int top = l;
            int bottom = N-1-l;
            int left = l;
            int right = M-1-l;

            int temp = arr[top][left];

            // 위쪽 행의 이동
            for (int i = left; i < right; i++) {
                arr[top][i] = arr[top][i + 1];
            }

            // 왼쪽 열의 이동
            for (int i = top; i < bottom; i++) {
                arr[i][right] = arr[i + 1][right];
            }

            // 아래쪽 행의 이동
            for (int i = right; i > left; i--) {
                arr[bottom][i] = arr[bottom][i - 1];
            }

            // 오른쪽 열의 이동
            for (int i = bottom; i > top; i--) {
                arr[i][left] = arr[i - 1][left];
            }

            arr[top + 1][left] = temp;
        }

    }

    static void printArr() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
