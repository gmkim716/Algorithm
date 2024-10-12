import java.util.Scanner;

public class Main {
    public static String n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        n = sc.next();  // 이진수: *String으로 받아서 사용 

        //== 2진수 to 10진수 ==//
        int num = 0;  // 결과물을 저장할 num 변수 사용
        for (int i=0; i<n.length(); i++) {
            num = num*2 + n.charAt(i)-'0';            
        }

        num *= 17;

        //== 10진수 to 2진수 ==// 
        int[] digits = new int[20];
        int idx = 0; 

        while(true) {
            if (num < 2) {
                digits[idx++] = num;
                break;      
            }
            digits[idx++] = num%2;
            num /= 2;
        }

        for (int i=idx-1; i>=0; i--) {
            System.out.print(digits[i]); 
        }
    }
}