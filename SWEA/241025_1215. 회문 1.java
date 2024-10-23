// 공백이 있을 때 StringTokenizer를 사용하면 편리하다. 그렇지 않으면 그냥 String을 사용하기

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

    static char[][] map;
    static int ans, palinLen;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t=0; t<10; t++) {
            palinLen = Integer.parseInt(br.readLine());
            map = new char[8][8];
            ans = 0;

            for (int i=0; i<8; i++) {
                String str = br.readLine();
                for (int j=0; j<8; j++) {
                    map[i][j] = str.charAt(j);
                }
            }

            for (int y=0; y<8; y++) {
                for (int x=0; x<8; x++) {
                    checkRow(x, y);
                    checkCol(x, y);
                }
            }
            System.out.println("#" + (t+1) + " " + ans);
        }
        br.close();
    }

    public static void checkRow(int x, int y) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        if (x+palinLen <= 8) {
            for (int i=0; i<palinLen; i++) {
                sb1.append(map[y][x+i]);
            }
            for (int i=palinLen-1; i>=0; i--) {
                sb2.append(map[y][x+i]);
            }

            if (sb1.toString().equals(sb2.toString())) {
                ans ++;
            }

        }
    }

    public static void checkCol(int x, int y) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        if (y+palinLen <= 8) {
            for (int i=0; i<palinLen; i++) {
                sb1.append(map[y+i][x]);
            }
            for (int i=palinLen-1; i>=0; i--) {
                sb2.append(map[y+i][x]);
            }

            if (sb1.toString().equals(sb2.toString())) {
                ans ++;
            }

        }
    }

    public static void printMap() {
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
