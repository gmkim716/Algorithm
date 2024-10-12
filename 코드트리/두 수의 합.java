// https://www.codetree.ai/missions/8/problems/sum-of-two-num?&utm_source=clipboard&utm_medium=text
// 모든 배열을 순회하면 O(n) 시간이 소요되지만, HashMap에 모든 배열 원소에 대한 정보를 담아주면 O(1) 시간 내에 확인 가능

import java.util.*;

public class Main {
    public static final int MAX_N = 100000; 
    public static int n, k, ans; 
    public static int[] a = new int[MAX_N];
    public static HashMap<Integer, Integer> m = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        
        // 값 입력
        n = sc.nextInt();
        k = sc.nextInt(); 
        for (int i=0; i<n; i++) {
            a[i] = sc.nextInt(); 
        }

        // 배열을 순회하며 두 개의 수 탐색
        for (int i=0; i<n; i++) {
            // 하나의 수를 선택해 차이를 구함
            int diff = k - a[i];

            // 완성된 HashMap에서 차이 값이 존재하는 지 확인
            if (m.containsKey(diff)) {
                ans += m.get(diff);  // 존재한다면 HashMap의 값을 ans에 추가
            }

            // 배열 i번째 값이 HashMap 내부에 존재하지 않는 경우, 해당 지점에 1 추가
            // 배열 i번째 값이 HashMap 내부에 존재하는 경우, 해당 지점의 값에 +1 추가
            if (!m.containsKey(a[i])) {
                m.put(a[i], 1);
            } else {
                m.put(a[i], m.get(a[i])+1);
            }
        }
        
        System.out.println(ans); 
    }
}