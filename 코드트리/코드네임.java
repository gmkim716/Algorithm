import java.util.Scanner;

class Mr {
    String codeName;
    int score;

    public Mr() {
        this.codeName = "";
        this.score = 101;
    }

    public Mr(String codeName, int score) {
        this.codeName = codeName;
        this.score = score;
    }
}

public class Main {
    public static Mr[] mrs = new Mr[5];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i=0; i<5; i++) {
            String codeName = sc.next();
            int score = sc.nextInt();

            mrs[i] = new Mr(codeName, score);    
        }

        Mr theMr = new Mr();
        for (int i=0; i<5; i++) {
            if (mrs[i].score < theMr.score) {
                theMr = mrs[i];
            }
        }
        System.out.print(theMr.codeName+" ");
        System.out.print(theMr.score);
    }
}