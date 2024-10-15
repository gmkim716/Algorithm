package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().strip());
        for (int t=0; t<T; t++) {
            String[] input = br.readLine().split(" ");

            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int N = Integer.parseInt(input[2]);

            int cnt = 0;
            int A, B;
            if (a < b) {
                A = a;
                B = b;
            } else {
                A = b;
                B = a;
            }

            while (A < N) {
                A += B;
                cnt ++;
            }

            System.out.println("#" + (t+1) + " " + cnt);
        }

        br.close();
    }
}

/** 입력 값
 * 5
 * 1 2 2
 * 1 2 3
 * 1 2 4
 * 1 2 5
 * 10 7 1293
 */
