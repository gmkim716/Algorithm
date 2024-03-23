import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int cnt = 1;
        int start_index = 1;
        int end_index = 1;
        int sum = 1;

        while (end_index != N) {
            if (sum == N) {  // 값을 찾았을 경우: cnt 증가, 우측 포인터 이동, sum 증가
                cnt ++;  
                end_index ++; 
                sum = sum + end_index;
            } else if (sum > N) {  // sum이 목표값보다 클 때: sum 감소, 왼쪽 포인터 이동
                sum = sum - start_index;
                start_index ++;
            } else {  // sum이 목표값보다 작을 때: 우측 포인터 이동, sum 증가
                end_index ++;
                sum = sum + end_index;
            }
        }
        System.out.println(cnt);
    }
}