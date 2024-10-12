import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static int MAX_NUM = 1000;
    public static int n, b;
    public static int[] wants = new int[MAX_NUM+1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        b = sc.nextInt();

        for (int i=0; i<n; i++) {
            wants[i] = sc.nextInt();
        }

        int ans = 0;

        for (int i=0; i<n ; i++) {

            // i번째 학생에게 쿠폰을 사용할 때, 각 학생의 선물 가격 저장
            int[] temp = new int[MAX_NUM];
            for (int j=0; j<n; j++) {
                temp[j] = wants[j];
            }
            temp[i] /= 2;

            // *할인 상태를 기준으로 정렬
            Arrays.sort(temp, 0, n);

            // 가장 많은 학생에게 선물을 줄 때, 학생의 수 구하기
            int students = 0;
            int balance = 0;

            for (int j=0; j<n; j++) {
                if (balance + temp[j] > b) {
                    break;
                }
                balance += temp[j];
                students ++;
            }
            ans = Math.max(ans, students);
        }
        
        System.out.println(ans); 
    }
}