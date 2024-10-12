import java.util.Scanner; 
import java.util.HashMap; 

public class Main {

    public static final int MAN_N = 100000;  

    public static int n;   
    public static long ans;
    public static HashMap<Integer, Integer> m = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        n = sc.nextInt(); 
        for (int i=0; i<n; i++) {
            int x = sc.nextInt(); 
            int y = sc.nextInt(); 
            if (!m.containsKey(x)) {
                m.put(x, y);
            } else {
                m.put(x, Math.min(m.get(x), y)); 
            }
        }
        
        // // forEach를 사용한 출력
        // m.forEach((key, value) -> {
        //     ans += value; 
        // });

        // 향상된 for문을 사용할 때: keySet()
        for(Integer key : m.keySet()) {
            ans += m.get(key);
        }

        System.out.println(ans); 
    }
}