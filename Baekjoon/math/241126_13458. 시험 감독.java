package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 시험장의 수
        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        // 각 시험장의 학생 수 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 총 감독관과 부감독관이 감시할 수 있는 학생 수
        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken()); // 총 감독관
        int C = Integer.parseInt(st.nextToken()); // 부감독관

        long cnt = 0; // 필요한 감독관 수

        for (int i = 0; i < N; i++) {
            // 총 감독관 배치
            arr[i] -= B;
            cnt++;

            // 부감독관 배치 (남은 학생이 있는 경우)
            if (arr[i] > 0) {
                cnt += Math.ceil((double) arr[i] / C); // 나눗셈 결과를 올림 처리
            }
        }

        // 결과 출력
        System.out.println(cnt);
    }
}
