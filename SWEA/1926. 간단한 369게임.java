import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        for (int i=1; i<=N; i++) {
            String strNum = String.valueOf(i);

            int cnt = 0;
            for (int j=0; j<strNum.length(); j++) {
                if (strNum.charAt(j) == '3' || strNum.charAt(j) == '6' || strNum.charAt(j) == '9') {
                    cnt++;
                }
            }

            if (cnt == 0) {
                System.out.print(i + " ");
            } else {
                for (int j=0; j<cnt; j++) {
                    System.out.print("-");
                }
                System.out.print(" ");
            }
        }

        br.close();
    }
}

/** 입력 값
 * 10
 */

/**
 * 출력 값
 * 1 2 - 4 5 - 7 8 - 10
 */