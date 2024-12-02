import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        long length = 0;

        long start = 1; // 시작 숫자
        long digit = 1; // 자릿수

        while (start <= N) {
            long end = Math.min(N, start * 10 - 1); // 현재 자릿수의 끝
            length += (end - start + 1) * digit; // 자릿수별 개수 합산
            start *= 10; // 다음 자릿수로 이동
            digit++; // 자릿수 증가
        }

        System.out.println(length);
    }
}
