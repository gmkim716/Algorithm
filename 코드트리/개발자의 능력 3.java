import java.util.Scanner;

public class Main {
    public static int[] arr = new int[6];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i=0; i<6; i++) {
            arr[i] = sc.nextInt();
        }

        int totalSum = 0;
        for (int i=0; i<6 ;i++){
            totalSum += arr[i];
        }

        int ans = Integer.MAX_VALUE;
        for (int i=0; i<6-2; i++) {
            for (int j=i+1; j<6-1; j++) {
                for (int k=j+1; k<6; k++) {
                    int team1 = arr[i]+arr[j]+arr[k];
                    int team2 = totalSum - team1;

                    ans = Math.min(ans, Math.abs(team1-team2));
                }
            }
        }
        System.out.println(ans);
    }
}