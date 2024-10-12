import java.util.Scanner; 

public class Main {
    public static final int OFFSET = 1000*100;
    public static int n, currX;
    public static int[] arr = new int[OFFSET*2];
    public static int[] arrB = new int[OFFSET*2];
    public static int[] arrW = new int[OFFSET*2];
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        n = sc.nextInt();
        currX = OFFSET;

        while(n-->0) {
            int x = sc.nextInt();
            char c = sc.next().charAt(0);
            if(c == 'L') {
                // x칸 왼쪽으로 이동, White 칠하기
                while(x-- > 0) {
                    arr[currX] = 1;
                    arrW[currX]++;
                    if(x > 0) currX--;
                }
            }
            else {
                // x칸 오른쪽으로 이동, Black 칠하기
                while(x-- > 0) {
                    arr[currX] = 2;
                    arrB[currX]++;
                    if(x > 0) currX++;
                }
            }
        }

        int white = 0, black = 0, gray = 0;
        for (int i=0; i<OFFSET*2; i++) {
            if (arrB[i]>=2 && arrW[i]>=2) gray ++; 
            else if (arr[i] == 2) black ++;
            else if (arr[i] == 1) white ++;
        }

        System.out.println(white+" "+black+" "+gray); 
    }
}