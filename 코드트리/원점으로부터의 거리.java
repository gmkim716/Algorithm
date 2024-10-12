import java.util.Scanner;
import java.util.Arrays;

class Pair implements Comparable<Pair> {
    int x, y, number;

    public Pair (int x, int y, int number) {
        this.x = x;
        this.y = y;
        this.number = number;
    }

    @Override
    public int compareTo(Pair pair) {
        // 아래와 같을 때 매소드를 만들어서 진행해도 좋을 듯 하다
        return (Math.abs(this.x)+Math.abs(this.y))-(Math.abs(pair.x)+Math.abs(pair.y));
    }
}

public class Main {
    public static final int MAX_N = 1000;
    public static int n;
    public static Pair[] pairs = new Pair[MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        for (int i=0; i<n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            pairs[i] = new Pair(x, y, i+1);
        }

        Arrays.sort(pairs, 0, n);

        for (int i=0; i<n; i++) {
            System.out.println(pairs[i].number);
        }
    }
}