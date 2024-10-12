import java.util.*; 

class Pair implements Comparable<Pair> {
    int a, b;
    public Pair(int a, int b) {
        this.a = a;
        this.b = b; 
    }

    @Override
    public int compareTo(Pair pair) {
        return this.b - pair.b;
    }
}

public class Main {

    public static final int MAX_C = 100000; 
    public static int c, n, ans;

    public static int[] reds = new int[MAX_C];
    public static TreeSet<Integer> redsTreeSet = new TreeSet<>();  // 트리 셋을 이용: 항상 정렬된 상태 유지
    public static ArrayList<Pair> blacks = new ArrayList<>(); 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        c = sc.nextInt();
        n = sc.nextInt(); 

        for (int i=0; i<c; i++) {
            reds[i] = sc.nextInt(); 
        }

        for (int i=0; i<n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            blacks.add(new Pair(a, b)); 
        }

        // 빨간 돌을 모두 treeSet에 입력: 오름차순 정렬 
        for (int i=0; i<c; i++) {
            redsTreeSet.add(reds[i]); 
        }

        Collections.sort(blacks);  // 검은돌 오름차순 정렬

        for (int i=0; i<n; i++) {
            int a = blacks.get(i).a;
            int b = blacks.get(i).b;

            if (redsTreeSet.ceiling(a) != null) {  // a보다 크거나 같은 값이 있는 경우
                int ti = redsTreeSet.ceiling(a);  // ti: a보다 크거나 같은 값
                
                // 조건을 달성하는 ti가 있을 때: ans +1 증가, ti 제거
                if (ti <= b) {
                    ans ++;
                    redsTreeSet.remove(ti);
                }
            }
        }
        System.out.println(ans);
    }
}