import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public final static int INT_MAX = Integer.MAX_VALUE;
    public final static int MAX_N = 10;

    public static int[][] cost = new int[MAX_N][MAX_N];
    public static boolean[] visited = new boolean[MAX_N];
    public static ArrayList<Integer> picked = new ArrayList<>(); 
    
    public static int n; 
    public static int ans = INT_MAX;

    public static void findMin(int cnt) {  // cnt를 파라미터로 사용
        
        // 모든 지점에 방문했을 때의 비용 집계 
        if (cnt == n) {
            int totalCost = 0;
            for (int i=0; i<picked.size()-1; i++) {
                int currCost = cost[picked.get(i)][picked.get(i+1)];
                
                // 비용이 0이라면 잘못 방문한 것(자신이 자신을 방문): 바로 빠져나온다
                if (currCost == 0) {
                    return; 
                }
                totalCost += currCost;
            }
            
            // 
            int lastPos = picked.get(picked.size()-1);
            int additionalCost = cost[lastPos][0];
            if (additionalCost == 0) {
                return; 
            }

            // 답을 갱신
            ans = Math.min(ans, totalCost+additionalCost);
            return;
        }

        // 방문할 지점 선택
        for (int i=0; i<n; i++) {

            // i를 탐색했을 때 이미 방문한 경우 
            if (visited[i]) continue;  
            
            //== i를 탐색했을 때 방문한 적이 없는 경우 ==//
            // 탐색 기록 표시
            visited[i] = true; 
            picked.add(i);

            findMin(cnt+1);  // 탐색 진행

            // 탐색 기록 제거
            visited[i]= false;
            picked.remove(picked.size()-1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                cost[i][j] = sc.nextInt();
            }
        }

        //== 1번 지점부터 시작 ==//
        visited[0] = true; 
        picked.add(0);

        findMin(1);  // 1개가 카운트 된 상태에서 진행

        System.out.println(ans);
    }
}