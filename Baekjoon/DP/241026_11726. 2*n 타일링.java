package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

    static int N;
    static BigInteger[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new BigInteger[1001];
        arr[1] = BigInteger.valueOf(1);
        arr[2] = BigInteger.valueOf(2);

        for (int i=3; i<=N; i++) {
            arr[i] = arr[i-1].add(arr[i-2]);
        }

        System.out.println(arr[N].remainder(BigInteger.valueOf(10007)));

        br.close();
    }

}
