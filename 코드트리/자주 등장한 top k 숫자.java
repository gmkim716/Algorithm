import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

// 정렬을 사용하기 위해 class정의
class Pair implements Comparable<Pair> {
    int cnt, num; 
    public Pair (int cnt, int num) {
        this.cnt = cnt;
        this.num = num;
    }

    // cnt, num 순서대로 정렬
    @Override
    public int compareTo(Pair b) {
        if (cnt != b.cnt) {
            return cnt - b.cnt;
        }
        return num - b.num;
    }
}

public class Main {
    public static int MAX_N = 100000; 
    public static HashMap<Integer, Integer> m = new HashMap<>(); 
    public static ArrayList<Pair> v = new ArrayList<>();

    public static int[] a = new int[MAX_N];
    public static int n, k;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        for (int i=0; i<n; i++) {
            a[i] = sc.nextInt(); 

            // 각 숫자가 몇 번씩 나왔는지를 hashmap에 기록: O(1) 시간 복잡도
            if (!m.containsKey(a[i])) {
                m.put(a[i], 1);
            } else {
                m.put(a[i], m.get(a[i])+1); 
            }
        }

        // hashmap 순회하며 새 배열 생성
        for (Integer key : m.keySet()) {
            v.add(new Pair(m.get(key), key));
        }

        Collections.sort(v); 

        for (int i=v.size()-1; i>=v.size()-k; i--) {
            System.out.print(v.get(i).num+" ");
        }

    }
}