import java.util.Scanner;
import java.util.HashMap; 

public class Main {
    
    public static final int MAX_N = 1000; 

    public static int n, k, ans;
    
    public static int[] a = new int[MAX_N];

    public static HashMap<Integer, Integer> freq = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        n = sc.nextInt();
        k = sc.nextInt();

        //== *모든 배열을 순회하면 O(N) 시간이 소요되지만 HashMap에 정보를 담아두면 O(1) 시간만에 확인할 수 있다==//  
        for (int i=0; i<n; i++) {
            a[i] = sc.nextInt();

            if (!freq.containsKey(a[i])) {
                // 이전까지 등장하지 않았던 값이 등장할 때, 1을 입력
                freq.put(a[i], 1); 
            } else {  
                // 이미 입력된 값이 등장할 때, 값을 추가
                freq.put(a[i], freq.get(a[i])+1); 
            }
        }

        // 배열을 앞에서부터 순회하면서 쌍을 만든다
        for (int i=0; i<n; i++) {
            // 이미 순회한 적이 있는 숫자는 빼서, 같은 조합이 여러번 세어지는 것을 방지
            if (!freq.containsKey(a[i])) {
                freq.put(a[i], -1);
            } else {
                freq.put(a[i], freq.get(a[i])-1);
            }

            for (int j=0; j<i; j++) {
                if (freq.containsKey(k-a[i]-a[j])) {
                    ans += freq.get(k-a[i]-a[j]);
                }
            }
        }

        System.out.println(ans); 
    }

}