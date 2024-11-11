package org.example;

import java.util.*;

// List 타입의 정렬은 Collections를 사용한다
// 배열의 경우 Arrays.sort()를 사용한다
// StringBuilder를 사용해 만든 문자열은 toString()을 사용해 String으로 변환한다
public class Main {

    static int N, C;
    static char[] arr;
    static List<String> ansLst = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();  // 암호 길이
        C = sc.nextInt();
        arr = new char[C];

        for (int i=0; i<C; i++) {
            arr[i] = sc.next().charAt(0);
        }

        Arrays.sort(arr);  // 초기에 입력을 정렬해서 중복 정렬을 방지한다
        dfs(0, 0, 0, new StringBuilder());

        Collections.sort(ansLst);  // 결과를 오름차순으로 정렬한다
        ansLst.forEach(System.out::println);
    }

    static void dfs(int start, int len, int volwels, StringBuilder sb) {
        if (len == N) {
            int consonants = len-volwels;
            if (volwels >= 1 && consonants >= 2) {
                ansLst.add(sb.toString());
            }
            return;
        }

        for (int i=start; i<C; i++) {
            sb.append(arr[i]);

            if (isVowel(arr[i])) {
                dfs(i+1, len+1, volwels+1, sb);
            } else {
                dfs(i+1, len+1, volwels, sb);
            }

            // deleteCharAt(idx): sb의 문자 삭제
            sb.deleteCharAt(sb.length()-1);
        }
    }
    static boolean isVowel(char c) {
        return c=='a' || c=='e' || c=='i' || c=='o' || c=='u';
    }
}