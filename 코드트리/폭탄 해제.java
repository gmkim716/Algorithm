import java.util.Scanner;

class Boom {
    String code;
    char color;
    int second;

    public Boom(String code, char color, int second) {
        this.code = code;
        this.color = color;
        this.second = second;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String code = sc.next();
        char color = sc.next().charAt(0);
        int second = sc.nextInt();

        Boom boom = new Boom(code, color, second);

        System.out.println("code : " + boom.code);
        System.out.println("color : " + boom.color);
        System.out.println("second : " + boom.second);
    }
}