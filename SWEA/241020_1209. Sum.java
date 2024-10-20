import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t=0; t<10; t++) {
            int T = Integer.parseInt(br.readLine());

            int[][] arr = new int[100][100];
            for (int i=0; i<100; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j=0; j<100; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int max = 0;
            for (int i=0; i<100; i++) {
                int sum = 0;
                for (int j=0; j<100; j++) {
                    sum += arr[i][j];
                }
                max = Math.max(max, sum);
            }

            for (int i=0; i<100; i++) {
                int sum = 0;
                for (int j=0; j<100; j++) {
                    sum += arr[j][i];
                }
                max = Math.max(max, sum);
            }

            int sum1 = 0;
            for (int i=0; i<100; i++) {
                sum1 += arr[i][i];
            }
            max = Math.max(max, sum1);

            int sum2 = 0;
            for (int i=0; i<100; i++) {
                sum2 += arr[i][100-i-1];
            }
            max = Math.max(max, sum2);

            System.out.println("#" + T + " " + max);
        }
    }
}
