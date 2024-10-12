import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

// 합 중에서 최댓값이 최소가 되려면: 가장 작은 수와 가장 큰 수를 순서대로 매칭

class Pair implements Comparable<Pair> {
    int y, x;
    Pair(int y, int x) {
        this.y = y;
        this.x = x; 
    }

    @Override
    public int compareTo(Pair p) {
        return this.y - p.y;   // y 기준 오름차순 정렬
    }
}

public class Main {
    
    public static int n;
    public static ArrayList<Pair> nums = new ArrayList<>();

    public static int ans;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i=0; i<n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            nums.add(new Pair(y, x));
        }

        // y기준 오름차순 정렬
        Collections.sort(nums);  // 사용자 정의 정렬을 사용하기 위해 Collections 사용

        // 가장 작은 수와 큰 수를 하나씩 매칭
        int li = 0, ri = n-1; 
        while (li <=ri) {
            int ly = nums.get(li).y;
            int lx = nums.get(li).x;
            int ry = nums.get(ri).y;
            int rx = nums.get(ri).x;

            // 답 갱신
            ans = Math.max(ans, ly+ry);

            // 왼쪽 개수가 더 적을 때, 왼쪽을 전부 매칭
            if (lx < rx) {
                // 오른쪽은 lx만큼 개수를 줄여준다
                nums.set(ri, new Pair(ry, rx-lx));  // Q. set이란?  A. ArrayList의 특정 값을 교체

                // 왼쪽 위치를 한칸 오른쪽으로 옮겨준다
                li ++; 
            }

            // 오른쪽 개수가 더 적을 때, 오른쪽을 전부 매칭
            else if (lx > rx) {
                // 왼쪽은 rx만큼 개수를 줄여준다
                nums.set(li, new Pair(ly, lx-rx));

                // 오른쪽 위치를 한칸 왼쪽으로 옮겨준다
                ri --;
            }

            // 개수가 동일할 때, li, ri 위치를 모두 옮겨준다
            else {
                li ++;
                ri --;
            }
        }
        System.out.println(ans);
    }
}