import java.util.Scanner;
import java.util.Arrays;  // Arrays: *sort() 메소드 사용을 위함

class Pair implements Comparable<Pair>{
    int t, x, y;

    public Pair(int t, int x, int y) {
        this.t = t;
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Pair p) {
        return this.t - p.t;
    }

}

public class Main {
    public static final int MAX_T = 250;
    public static final int MAX_N = 100;
    public static int N, K, P, T; 

    public static Pair[] pair = new Pair[MAX_T+1];
    public static int[] shakeNum = new int[MAX_N+1];
    public static boolean[] infected = new boolean[MAX_N+1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        N = sc.nextInt();  // 개발자의 수
        K = sc.nextInt();  // 전염 가능 횟수
        P = sc.nextInt();  // 처음 전염병에 걸린 개발자 번호
        T = sc.nextInt();  // 악수 횟수
        infected[P] = true;

        for (int i=0; i<T; i++) {
            // t초에 x가 y와 악수
            int t = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            pair[i] = new Pair(t, x, y);
        }
        
        // custom comparator를 이용해 정렬
        Arrays.sort(pair, 0, T);  // *Arrays.sort(배열, 정렬 시작 인덱스, 정렬 끝 인덱스)


        for (int i=0; i<T; i++) {
            int x = pair[i].x;
            int y = pair[i].y;

            // 감염되어 있을 경우 악수 횟수를 증가 
            if (infected[x]) {
                shakeNum[x] ++;
            }
            if (infected[y]) {
                shakeNum[y] ++;
            } 

            // 감염되어 있고 K번 이하로 악수했을 때 악수 상대의 감염 여부 변경
            if (shakeNum[x] <= K && infected[x]) {
                infected[y] = true;
            }
            if (shakeNum[y] <= K && infected[y]) {
                infected[x] = true;
            }

        }
        for (int i=1; i<=N; i++) {
            if (infected[i]) {
                System.out.print(1);
            } else {
                System.out.print(0);
            }
        }
    }
}