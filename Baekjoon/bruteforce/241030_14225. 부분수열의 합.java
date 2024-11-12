import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static List<Integer> lst = new ArrayList<>();

    // 왜 2000001로 설정했는지 이해가 안됨?: 100000 * 20 = 2000000이므로 2000001로 설정(0부터 시작하므로)
    static boolean[] contains = new boolean[2000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        makeSubSet(0, 0);
        lst.remove(0);  // 공집합인 경우 제외

        for (Integer num : lst) {
            contains[num] = true;
        }

        for (int i=1; i<2000001; i++) {
            if (!contains[i]) {
                System.out.println(i);
                break;
            }
        }
    }

    static void makeSubSet(int idx, int sum) {
        if (idx == N) {
            lst.add(sum);
            return;
        }

        makeSubSet(idx+1, sum);
        makeSubSet(idx+1, sum+arr[idx]);
    }
}
