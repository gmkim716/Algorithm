import java.util.Scanner;
import java.util.HashSet;  // *정렬되지 않은 
import java.util.Arrays; 

public class Main {
    public static final int MAX_N = 50000;

    public static int n;
    public static int[] aCards = new int[MAX_N];
    public static int[] bCards = new int[MAX_N];

    public static HashSet<Integer> bSet = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();  // 카드: 1~2n까지 
        for (int i=0; i<n; i++) {
            bCards[i] = sc.nextInt();
            bSet.add(bCards[i]);  // ?
        }

        // a 카드 목록 완성
        int cardIdx = 0;  // 기준이 될 인덱스
        for (int i=1; i<=2*n; i++) {
            if (!bSet.contains(i)) {  // 1~2n 번의 카드 확인
                aCards[cardIdx++] = i;
            }
        }

        // a, b 카드 목록 정렬
        // *Arrays.sort(A, 0, n): 0 ~ n-1 요소를 가지고 정렬, *범위 주의
        // Arrays.sort(A): A의 전체 범위에 대해서 정렬
        Arrays.sort(aCards, 0, n);  
        Arrays.sort(bCards, 0, n);


        // *0부터 시작하는 인덱스를 기준으로 비교 진행
        //  a의 경우 for문에 의해 진행되고, b의 경우 증가되는 bIdx로 확인 
        int ans = 0;
        int bIdx = 0;
        for (int aIdx = 0; aIdx < n; aIdx++) {
            if ((bIdx < n) && (aCards[aIdx] > bCards[bIdx])) {
                ans++;
                bIdx++;
            }
        }
        System.out.println(ans);
    }
}