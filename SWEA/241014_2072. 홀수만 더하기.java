import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t=0; t<T; t++) {
            String[] input = br.readLine().split(" ");
            int[] arr = new int[10];

            for (int i=0; i<10; i++) {
                arr[i] = Integer.parseInt(input[i]);
            }

            Arrays.sort(arr);

            int ans = 0;
            for (int i=0; i<10; i++) {
                if (arr[i] % 2 == 1) {
                    ans += arr[i];
                }
            }

            System.out.println("#" + (t+1) + " " + ans);
        }

        br.close();
    }
}

/** 입력 값
 * 3
 * 3 17 1 39 8 41 2 32 99 2
 * 22 8 5 123 7 2 63 7 3 46
 * 6 63 2 3 58 76 21 33 8 1
 */

/** 출력 값
 * #1 200
 * #2 208
 * #3 121
 */