// 난이도 쉬움
// tip. 정답 타입이 long 타입이 될 수 있음을 고려해야 함 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t=0; t<T; t++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[N];
            for (int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int maxNum = -1;
            long profit = 0;
            for (int i=N-1; i>=0; i--) {
                if (maxNum < arr[i]) {
                    maxNum = arr[i];
                } else {
                    profit += maxNum - arr[i];
                }
            }

            System.out.println("#" + (t+1) + " " + profit);

        }
    }
}
