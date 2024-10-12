import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static final int MAX_N = 100000; 

    public static int n;
    public static TreeMap<String, Integer> freq = new TreeMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        n = sc.nextInt();

        for (int i=0; i<n; i++) {
            String str = sc.next();
            if (!freq.containsKey(str)) {
                freq.put(str, 1);
            } else {
                freq.put(str, freq.get(str)+1); 
            }
        } 

        freq.forEach((key, value) -> {
            System.out.println(key+" "+value); 
        });
    }
}