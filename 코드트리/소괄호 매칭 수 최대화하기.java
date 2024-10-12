import java.util.*;

class Element implements Comparable<Element>{
    int open, closed;
    public Element(int open, int closed) {
        this.open = open;
        this.closed = closed;
    }
    
    @Override
    public int compareTo(Element element) {
        // 점수의 합계를 비교, 더 많이 얻을 수 있는 경우로 정렬
        return element.open*this.closed - this.open*element.closed;
    }
}

public class Main {
    public static final int MAX_N = 100000;
    
    public static int n;
    public static long ans;

    public static Element[] brackets = new Element[MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for (int i=0; i<n; i++) {
            String str = sc.next();
            
            int open = 0, closed = 0;  // 열린 소괄호, 닫힌 소괄호 수 세기
            
            for (int j=0; j<str.length(); j++) {
                if (str.charAt(j) == '(') {
                    open ++;
                } else {
                    closed ++;

                    // 해당 문자열 만으로 얻게 되는 점수는 미리 답에 더해준다
                    ans += open;
                }
            }
            brackets[i] = new Element(open, closed);
        }

        Arrays.sort(brackets, 0, n);

        // 정렬 순서를 보며, 새롭게 얻게 되는 점수 출력
        int openSum = 0;
        for (int i=0; i<n; i++) {
            int open = brackets[i].open;
            int closed = brackets[i].closed;

            // 답 갱신
            ans += (long) openSum*closed;

            // openSum을 누적해준다
            openSum += open;
        }

        System.out.println(ans);
    }
}