import java.util.Scanner;

public class Main {
    public static int a, b;
    public static String n;
    public static int[] digits = new int[50];  // 20 정도면 여유로운 듯

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();
        b = sc.nextInt();
        n = sc.next();        

        
        //== A진수 n을 10진수로 변경 ==//
        int num = 0;
        for (int i=0; i<n.length(); i++) {
            num = num*a + n.charAt(i)-'0'; 
        }

        //== 10진수 num을 b진수로 변경==//
        int idx = 0; 
        while(true) {
            if (num < b) {
                digits[idx++] = num;
                break;
            }
            digits[idx++] = num%b;
            num /= b;
        }

        // 정답 출력
        for (int i=idx-1; i>=0; i--) {
            System.out.print(digits[i]); 
        }
    }
}