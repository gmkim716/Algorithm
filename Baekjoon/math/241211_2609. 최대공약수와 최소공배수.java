import java.util.Scanner;

public class Main {

    // 최대공약수 (GCD) 구하는 메서드 (유클리드 호제법)
    public static int getGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // 최소공배수 (LCM) 구하는 메서드
    public static int getLCM(int a, int b) {
        return a * b / getGCD(a, b);
    }

    // 메인 메서드
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        int a = sc.nextInt();
        int b = sc.nextInt();

        // 최대공약수 (GCD) 구하기
        int gcd = getGCD(a, b);

        // 최소공배수 (LCM) 구하기
        int lcm = getLCM(a, b);

        // 출력
        System.out.println(gcd); // 첫째 줄에 최대공약수
        System.out.println(lcm); // 둘째 줄에 최소공배수
    }
}
