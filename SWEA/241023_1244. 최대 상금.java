import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int[] arr;
    static int ans, n, k;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t=0; t<T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String strNum = st.nextToken();
            k = Integer.parseInt(st.nextToken());

            n = strNum.length();
            arr = new int[n];

            for (int i=0; i<n; i++) {
                arr[i] = strNum.charAt(i) - '0';
            }

            ans = 0;
            dfs(0, 0);


            System.out.println("#" + (t+1) + " " + ans);
        }
        br.close();
    }


    static void dfs(int start, int cnt) {
        if (cnt == k) {
            int value = 0;
            for (int num : arr) {
                value = value * 10 + num;
            }
            ans = Math.max(ans, value);  // 최댓값을 갱신한다
            return;
        }

        for (int i=start; i<n-1; i++) {
            for (int j=i+1; j<n; j++) {
                swap(i, j);
                dfs(i, cnt+1);
                swap(i, j);
            }
        }
    }

    static void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
