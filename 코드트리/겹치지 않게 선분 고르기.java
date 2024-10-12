import java.util.Scanner;
import java.util.ArrayList;

class Pair{
    int s, e;
    public Pair(int s, int e) {
        this.s = s;
        this.e = e;
    }
}

public class Main {

    public static int n, ans;
    public static final int MAX_N = 15; 

    public static ArrayList<Pair> selectedSegs = new ArrayList<>(); 
    public static Pair[] segments = new Pair[MAX_N+1];

    public static boolean overlapped(Pair seg1, Pair seg2) {
        int ax1 = seg1.s;
        int ax2 = seg1.e;
        
        int bx1 = seg2.s;
        int bx2 = seg2.e;

        return (ax1 <= bx1 && bx1 <= ax2) || (ax1 <= bx2 && bx2 <= ax2) ||
               (bx1 <= ax1 && ax1 <= bx2) || (bx1 <= ax2 && ax2 <= bx2); 
    }

    // 겹치는 선분이 있는지 확인: i번째 선분과 j번째 선분 비교
    public static boolean possible() {
        for (int i=0; i<selectedSegs.size(); i++) {
            for (int j=i+1; j<selectedSegs.size(); j++) {
                if (overlapped(selectedSegs.get(i), selectedSegs.get(j))) {  // 두 선분이 겹치면 false
                    return false;
                }
            }
        }
        return true;
    }

    // n개의 선분을 사용했을 때 최댓값 확인, cnt: 확인 중인 선분의 개수 
    public static void findMaxSegments(int cnt) {
        if (cnt == n) {
            if (possible()) {
                ans = Math.max(ans, selectedSegs.size());
            }
            return;      
        }

        //== cnt 번째 선분이 있을 때와 없을 때 확인 ==//
        selectedSegs.add(segments[cnt]);  // cnt 번째 선분에 대해 확인 
        findMaxSegments(cnt+1);  // 확인중인 선분의 개수 +1 
        selectedSegs.remove(selectedSegs.size()-1);  // 가장 최근에 추가한 선분 제거 
        
        findMaxSegments(cnt+1);  // 확인중인 선분의 개수 +1 
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        // 사용할 수 있는 선분 정보 등록
        for (int i=0; i<n; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            segments[i] = new Pair(s, e); 
        }

        // 모든 선분 탐색
        findMaxSegments(0); 

        System.out.println(ans);

    }
}