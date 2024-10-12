// 새로운 값이 추가 되었을 때 영향을 주는 요소 세 가지
//  1. median보다 작은 숫자들 중 최대값 → 최대 힙
//  2. 기존 median
//  3. median보다 큰 숫자들 중 최솟값 → 최소 힙

import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Arrays;

public class Main {
    public static final int MAX_N = 100000;
    
    public static int t, m;
    public static int[] arr = new int[MAX_N];
    
    public static void findMedian() {
        //==첫번째 요소가 입력된 상태에서 진행==// 
        // 중앙값에 대해서 계속해서 관리를 진행
        int median = arr[0];

        // 중앙값보다 작은 숫자들을 최대힙으로 관리: 
        PriorityQueue<Integer> maxPq = new PriorityQueue<>();
        
        // 중앙값보다 큰 숫자들을 최소힙으로 관리: 
        PriorityQueue<Integer> minPq = new PriorityQueue<>();

        System.out.print(median+" ");  // 중앙값을 출력

        //==새로운 요소가 추가되며 진행==//
        for (int i=1; i<m; i++) {
            
            // case 1. 짝수 번째: maxPq, minPq에 들어있는 원소 수가 동일
            // 중앙값을 그대로 두고 median과 arr[i] 값을 비교
            // arr[i] < median이면 maxPq에, arr[i] > median이라면 minPq에 넣어주기
            if (i%2 == 1) {
                if (arr[i] < median) {
                    maxPq.add(-arr[i]);  // 최대힙
                } else {
                    minPq.add(arr[i]);  // 최소힙
                }
            } 
            // case 2. 홀수 번째: maxPq, minPq 중 개수가 더 많은 쪽에서 우선순위가 가장 큰 값을 뽑기
            else {
                int newCand;  // 새로운 우선순위 변수
                if (maxPq.size() > minPq.size()) { 
                    newCand = -maxPq.poll();  // maxPq에서 추출
                } else {
                    newCand = minPq.poll();  // minPq에서 추출
                }

                // 현재 maxPq, minPq에 들어있는 숫자의 개수는 동일
                // median, arr[i], newCand에서 가장 작은 값은 maxPq에, 가운데 값은 median에, 가장 큰 값은 minPq에 넣어줘야 함
                // median: 기존의 중앙값 / arr[i]: 새로 추가되는 요소 / newCand: 입력된 값 중에서 변화가 적용되는 요소
                int[] nums = new int[]{median, arr[i], newCand};  
                Arrays.sort(nums);  // 배열을 이용하면 정렬 가능

                maxPq.add(-nums[0]);
                median = nums[1];
                minPq.add(nums[2]);

                // 홀수 번째의 경우에 중앙값을 출력
                System.out.print(median+" ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();

        while (t-->0) {
            m = sc.nextInt();
            for (int i=0; i<m; i++) {
                arr[i] = sc.nextInt();
            }

            findMedian();  // findMedian(): 홀수 번째 마다 중앙값 찾기 
        }
    }
}