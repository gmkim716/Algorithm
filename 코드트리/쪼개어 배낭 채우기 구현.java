import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Jewel implements Comparable<Jewel> {
    int w, v;

    public Jewel (int w, int v) {
        this.w = w;
        this.v = v;
    }

    @Override
    public int compareTo(Jewel j) {
        double x = (double)j.v/j.w - (double)this.v/this.w;  // 내림차순 정렬 (인스턴스 > this)
        
        // Question: return -1/0/1 이해 x?
        if (x<0) {  // 무게 대비 가격이 작음
            return -1;
        } else if (x == 0) {  // 무게 대비 가격이 동일
            return 0;
        } else {  // 무게 대비 가격이 큼
            return 1;
        }
    }
}

public class Main {
    public static final int MAX_N = 100000;

    public static int n, m;
    public static double ans;
    public static ArrayList<Jewel> jewels = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        n = sc.nextInt();
        m = sc.nextInt(); 

        for (int i=1; i<=n; i++) {
            int w = sc.nextInt();  // 무게
            int v = sc.nextInt();  // 가치
            jewels.add(new Jewel(w, v));
        }

        // 무게 당 가치가 내림차순이 되도록 정렬: 배열을 정렬: Arrays.sort(x), *ArrayList를 정렬: Collections.sort(x)
        // *Collections.sort(x): Comaprable에서 정의한 compareTo 매소드에 따라 정렬을 진행
        Collections.sort(jewels);  

        for (int i=0; i<n; i++) {
            int w = jewels.get(i).w;
            int v = jewels.get(i).v;

            // 가방이 온전한 보석을 담을 수 있을 때
            if (m >= w) {
                m -= w;
                ans += v;
            } 
            // 가방이 온전한 보석을 담을 수 없음 → 쪼개야 함
            else {
                ans += (double)v/w*m;  // *곱과 나눗셈 밖에 없으므로 굳이 괄호를 여러개 사용할 이유x
                m = 0;
            }
        }
        System.out.printf("%.3f", ans); 
    }
}