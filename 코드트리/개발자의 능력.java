import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static final int MAX_N = 6;
    public static int[] arr = new int[MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i=0; i<6; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr, 0, 6);

        int team1 = arr[0] + arr[6-1];
        int team2 = arr[1] + arr[6-2];
        int team3 = arr[2] + arr[6-3];

        int teamMax = Math.max(team1, Math.max(team2, team3));
        int teamMin = Math.min(team1, Math.min(team2, team3));

        System.out.println(teamMax - teamMin);
    }
}