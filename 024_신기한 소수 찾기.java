import java.util.Scanner;

public class Main {
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();  // N자리 숫자

        DFS(2, 1);
        DFS(3, 1);
        DFS(5, 1);
        DFS(7, 1);
    }

    static void DFS(int num, int pos) {
        if (pos == N) {  // N자리에 해당하는 경우
            if (isPrime(num)) {
                System.out.println(num);
            }
            return;
        }
        for (int i=1; i<10; i++) {
            if (i%2 == 0) {  // 짝수이면 탐색할 필요가 없기 때문에 종료
                continue;
            }
            if (isPrime(num*10+i)) {  // 소수라면, 재귀함수로 자리수를 늘려서 진행
                DFS(num*10+i, pos+1);
            }
        }
    }
    static boolean isPrime(int num) {
        for (int i=2; i<=num/2; i++) {
            if (num%i == 0) {
                return false;
            }
        }
        return true;
    }
}
