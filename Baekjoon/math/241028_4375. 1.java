package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while ((input = br.readLine()) != null) {
            int N = Integer.parseInt(input);

            int cnt = 0;
            long testNum = 0;

            while (true) {
                cnt ++;

                // 타입 전환의 비용이 크기 때문에 숫자로 연산한다
                // 나머지를 미리 구해두면 연산 과정을 크게 줄일 수 있다
                testNum = (testNum * 10 + 1) % N;

                if (testNum == 0) {
                    System.out.println(cnt);
                    break;
                }
            }
        }
    }
}
