import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int cnt = 1;
        int left = 1;
        int right = 1;
        int sum = 1;

        while (right != N) {
            if (sum == N) {  // 값을 찾았을 경우: cnt 증가, 우측 포인터 이동, sum 증가
                cnt ++;  
                right ++;
                sum = sum + right;
            } else if (sum > N) {  // sum이 목표값보다 클 때: sum 감소, 왼쪽 포인터 이동
                sum = sum - left;
                left ++;
            } else {  // sum이 목표값보다 작을 때: 우측 포인터 이동, sum 증가
                right ++;
                sum = sum + right;
            }
        }
        System.out.println(cnt);
    }
}