import java.util.*;

public class Main {
    public static final int MAX_N = 100000;
    public static final int MAX_K = 3;  // 낼 수 있는 종류: {가위, 바위, 보}

    public static int n, ans;
    public static char[] bList = new char[MAX_N];  // b가 낸 목록 

    // L R 테크닉 사용: 계산 비용 절감
    public static int[] L = new int[MAX_N];  // 왼쪽에서부터, 동일한 값을 입력했을 때, 얻을 수 있는 가장 큰 실시간 점수 배열
    public static int[] R = new int[MAX_N];  // 오른쪽에서부터, 동일한 값을 입력했을 때, 얻을 수 있는 가장 큰 실시간 점수 배열

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i=0; i<n; i++) {
            bList[i] = sc.next().charAt(0);
        }

        String SHP = new String("SHP");  // {가위, 바위, 보} 문자 생성: 문자열을 활용하기 위함

        // L배열 채우기, L배열: (0, k) 구간동안 동일한 모양만 냈을 때, 최대로 이길 수 있는 횟수
        for (int k=0; k<MAX_K; k++) {

            int sameCnt = 0;  // 동일한 개수
            for (int i=0; i<n; i++) {
                if (bList[i] == SHP.charAt(k)) {
                    sameCnt += 1;
                }
                L[i] = Math.max(L[i], sameCnt); 
            }
        }

        // for (int i=0; i<n; i++) {
        //     System.out.print(L[i]+" ");
        // }
        // System.out.println(); 
        

        // R배열 채우기, L배열: (k, n-1) 구간동안 동일한 모양만 냈을 때, 최대로 이길 수 있는 횟수
        for (int k=0; k<MAX_K; k++) {

            int sameCnt = 0;  // 동일한 개수
            for (int i=n-1; i>=0; i--) {
                if (bList[i] == SHP.charAt(k)) {
                    sameCnt += 1;
                }
                R[i] = Math.max(R[i], sameCnt); 
            }
        }

        // for (int i=0; i<n; i++) {
        //     System.out.print(R[i]+" ");
        // }
        // System.out.println(); 

        // 해당 i 순간에 선택을 변경했다고 가정했을 때, 최대로 이기는 횟수를 갱신
        for (int i=0; i<n-1; i++) {
            ans = Math.max(ans, L[i]+R[i+1]);
        }
        
        System.out.println(ans);

    }
}