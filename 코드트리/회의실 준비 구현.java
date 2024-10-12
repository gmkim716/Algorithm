import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;


class Meeting implements Comparable<Meeting> {
    int start, end;

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override 
    public int compareTo(Meeting m) {
        // 끝나는 시점을 기준으로 오름차순 정렬: *this가 기준이면 오름차순, 인스턴스가 기준이면 내림차순
        return this.end - m.end;
    }
}

public class Main {
    public static final int MAX_N = 100000; 

    public static int n, ans;
    public static ArrayList<Meeting> al = new ArrayList<>();  // *사용자 정렬 기준을 사용할 때 ArrayList를 사용하도록 하자

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for (int i=0; i<n; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            al.add(new Meeting(s, e));
        }

        // comparTo에서 정의한 사용자 정렬
        Collections.sort(al);

        int currE = -1;  // currE: 확인 중인 마지막 회의 시간 표시
        
        for (int i=0; i<al.size(); i++) {
            int start = al.get(i).start;
            int end = al.get(i).end;

            if (currE <= start) {
                currE = end;  // currE 변경
                ans++;
            }
        }
        System.out.println(ans);
    }

}