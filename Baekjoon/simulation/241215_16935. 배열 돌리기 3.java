package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, R;
    static int[][] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        A = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            int num = Integer.parseInt(st.nextToken());
            func(num);
        }

        printA();
    }

    static void func(int num) {
        switch (num) {
            case 1: func1(); break;
            case 2: func2(); break;
            case 3: func3(); break;
            case 4: func4(); break;
            case 5: func5(); break;
            case 6: func6(); break;
        }
    }

    // 1. 상하 반전
    static void func1() {
        int[][] retA = new int[N][M];
        for (int i = 0; i < N; i++) {
            retA[i] = A[N - i - 1];
        }
        A = retA;
    }

    // 2. 좌우 반전
    static void func2() {
        int[][] retA = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                retA[i][j] = A[i][M - 1 - j];
            }
        }
        A = retA;
    }

    // 3. 시계 방향 90도 회전
    static void func3() {
        int[][] retA = new int[M][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                retA[j][N - 1 - i] = A[i][j];
            }
        }
        A = retA;
        int temp = N;
        N = M;
        M = temp;
    }

    // 4. 반시계 방향 90도 회전
    static void func4() {
        int[][] retA = new int[M][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                retA[M - 1 - j][i] = A[i][j];
            }
        }
        A = retA;
        int temp = N;
        N = M;
        M = temp;
    }

    // 5. 4개의 부분 배열을 시계 방향으로 회전
    static void func5() {
        int n = N / 2, m = M / 2;
        int[][] retA = new int[N][M];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                retA[i][j + m] = A[i][j];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                retA[i + n][j + m] = A[i][j + m];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                retA[i + n][j] = A[i + n][j + m];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                retA[i][j] = A[i + n][j];

        A = retA;
    }

    // 6. 4개의 부분 배열을 반시계 방향으로 회전
    static void func6() {
        int n = N / 2, m = M / 2;
        int[][] retA = new int[N][M];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                retA[i + n][j] = A[i][j];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                retA[i][j] = A[i][j + m];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                retA[i][j + m] = A[i + n][j + m];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                retA[i + n][j + m] = A[i + n][j];

        A = retA;
    }

    static void printA() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }
}
