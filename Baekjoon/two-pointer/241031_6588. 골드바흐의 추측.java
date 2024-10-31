import java.io.IOException;
import java.util.*;

// 투 포인터 방식을 떠올리는 것은 잘했지만, 기존의 방식에서 소수가 너무 많은 경우 시간 초과가 발생할 수 있다
// 따라서 boolean 타입에 소수를 미리 구해놓고 right = N-left로 값을 찾는 방식이 효율적이다
public class Main {

    static int N;
    static List<Integer> primeNumbers = new ArrayList<>();
    static final int MAX_NUM = 1000000;
    static boolean[] isPrime = new boolean[MAX_NUM+1];

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        makeIsPrimeNumbers();

        while (true) {
            N = sc.nextInt();
            if (N == 0) break;

            boolean found = false;
            for (int leftVal : primeNumbers) {
                if (leftVal >= N) break;  // left 값은 N보다 작아야 한다

                int rightVal = N - leftVal;
                if (isPrime[rightVal]) {
                    System.out.println(N + " = " + leftVal + " + " + rightVal);
                    found = true;
                    break;

                }
            }
            if (!found) {
                System.out.println("Goldbach's conjecture is wrong.");
            }
        }
    }

    static void makeIsPrimeNumbers() {
        // 초기화: 모든 숫자를 소수로 가정한다
        for (int i=2; i<=MAX_NUM; i++) {
            isPrime[i] = true;
        }

        // 소수의 배수를 지워나간다
        for (int i=2; i*i<=MAX_NUM; i++) {
            if (isPrime[i]) {
                for (int j=i*i; j<=MAX_NUM; j+=i) {
                    isPrime[j] = false;
                }
            }
        }

        // 소수만 primeNumbers에 추가한다
        for (int i=3; i<=MAX_NUM; i++) {
            if (isPrime[i]) {
                primeNumbers.add(i);
            }
        }
    }
}

