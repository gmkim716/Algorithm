package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t=0; t<T; t++) {
            String[] input = br.readLine().split(" ");
            int sum = 0;
            for (int i=0; i<10; i++) {
                sum += Integer.parseInt(input[i]);
            }

            System.out.println("#" + (t+1) + " " + Math.round(sum/10.0));
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
