import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] arr = new int[9];
    static int total;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i=0; i<9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            total += arr[i];
        }

        findAns();
    }

    static void findAns() {
        for (int i=0; i<8; i++) {
            for (int j=i+1; j<9; j++) {
                if (total - (arr[i] + arr[j]) == 100) {
                    printAns(i, j);
                    return;  // break가 아닌 return으로 종료하기 
                }
            }
        }
    }

    static void printAns(int idx1, int idx2) {
        List<Integer> ansArr = new ArrayList<>();

        for (int i=0; i<9; i++) {
            if (i != idx1 && i != idx2) {
                ansArr.add(arr[i]);
            }
        }

        Collections.sort(ansArr);  // List를 정렬

        for (int num: ansArr) {
            System.out.println(num);
        }
    }
}
