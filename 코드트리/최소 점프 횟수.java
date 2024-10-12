// https://www.codetree.ai/missions/2/problems/min-num-of-jumps?&utm_source=clipboard&utm_medium=text

// 직접 풀 때는 dp 형태로 풀이했지만, 아래와 같이 ans 값만 변화시켜서 풀이 가능한 문제였다.

import java.util.Scanner;

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE; 
    public static int MAX_N = 10; 

    public static int n;
    public static int[] arr = new int[MAX_N];

    public static int ans = INT_MAX;

    public static void findMin(int idx, int cnt) {
        // 종료 조건
        if (idx >= n-1) {
            ans = Math.min(ans, cnt); 
            return; 
        }

        // 점프해서 이동할 수 있는 곳의 인덱스 탐색
        for (int dist=arr[idx]; dist>=1; dist--) {
            findMin(idx+dist, cnt+1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        n = sc.nextInt();
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt(); 
        }

        findMin(0, 0);

        if (ans == INT_MAX) {
            ans = -1; 
        }

        System.out.println(ans); 
    }
}