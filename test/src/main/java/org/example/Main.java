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
        A = new int[N+1][M+1];

        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int layers = Math.min(N, M) / 2;
        for (int i = 0; i < R; i++) {
            for (int layer = 0; layer < layers; layer++) {
                rotate(1 + layer, N - layer, M - layer, 1 + layer); // 테두리 회전
            }
        }

        printA();
    }

    // for 문을 돌릴 때 t, b, r, l 값을 최대한 활용해
    static void rotate(int t, int b, int r, int l) {
        int tl = A[t][l];
        // 위
        for (int i=l; i<r; i++) {
            A[t][i] = A[t][i+1];
        }
        // 오른쪽
        for (int i=t; i<b; i++) {
            A[i][r] = A[i+1][r];
        }
        // 아래
        for (int i=r; i>l; i--) {
            A[b][i] = A[b][i-1];
        }
        // 왼쪽
        for (int i=b; i>t+1; i--) {
            A[i][l] = A[i-1][l];
        }
        A[t+1][l] = tl;
    }

    static void printA() {
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=M; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }
}
