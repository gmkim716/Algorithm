
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// dfs, brute force를 이용해 풀 수 있지만, 시간초과 문제가 발생한다
// 모든 순열 중에서 다음 순열을 찾는 것이 아니라, 주어진 순열에서 그 다음 순열을 찾는 방법을 사용한다

public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (beforePermutation()) {
            printArr();
        } else {
            System.out.println(-1);
        }
    }

    static boolean beforePermutation() {
        int i = N-2;
        while (0<= i && arr[i] < arr[i+1]) {
            i--;
        }
        if (i<0) {
            return false;
        }

        int j = N-1;
        while (arr[i] <= arr[j]) {
            j--;
        }

        swap(i, j);

        reverse(i+1, N-1);

        return true;
    }

    static void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void reverse(int start, int end) {
        while (start < end) {
            swap(start++, end--);
        }
    }

    static void printArr() {
        StringBuilder sb = new StringBuilder();
        for (int num: arr) {
            sb.append(num).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
