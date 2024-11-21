package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 투 포인터로 풀이한다
public class Main {

    static int N, M, cnt;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int result = cntSumIsM();

        System.out.println(result);
    }

    static int cntSumIsM() {
        int ret = 0;

        int left = 0, right = 0, sum = A[0];
        while (left < N) {
            if (sum == M) {
                ret++;
                if (right+1 == N) {
                    break;
                } else {
                    right++;
                    sum += A[right];
                }
            } else if (sum < M) {
                if (right+1 == N) {
                    break;
                } else {
                    right++;
                    sum += A[right];
                }
            } else if (sum > M) {
                sum -= A[left];
                left++;
            }
        }

        return ret;
    }
}
