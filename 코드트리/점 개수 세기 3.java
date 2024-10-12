import java.util.*;

// 좌표 압축 이용: x좌표를 (1, n) 사이로 만들 수 있다

class Pair {
    int a, b;
    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }
}

public class Main {
    public static int MAX_NUM = 100000;

    public static int n, q; 
    public static int[] arr = new int[MAX_NUM];
    public static Pair[] queries = new Pair[MAX_NUM];

    public static TreeSet<Integer> nums = new TreeSet<>();
    public static HashMap<Integer, Integer> mapper = new HashMap<>(); 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        q = sc.nextInt();

        for (int i=0; i<n; i++) {
            arr[i] = sc.nextInt();  // 점의 위치
        }

        for (int i=0; i<q; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            queries[i] = new Pair(a, b);
        }

        // 주어진 x좌표를 모두 treeset에 입력
        for (int i=0; i<n; i++) {
            nums.add(arr[i]);
        }

        // treeset에서 정점을 작은 순 번호부터 뽑으면서, 각 정점별로 1번부터 순서대로 매칭, 결과를 hashmap에 입력
        int cnt = 1;
        for (Integer num : nums) {
            mapper.put(num, cnt);
            cnt ++; 
        }

        // 질의에 대해 각 [a, b]에 해당하는 번호를 mapper를 통해 구해, 두 번호 사이의 점을 수를 출력
        for (int i=0; i<q; i++) {
            int a = queries[i].a;
            int b = queries[i].b;

            int newA = mapper.get(a);
            int newB = mapper.get(b);

            // System.out.println(a+" "+b+" "+newA+" "+newB);
            System.out.println(newB - newA + 1); 
        }
    }
}