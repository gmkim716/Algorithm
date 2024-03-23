### input
```java
BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

StringTokenizer st = new StringTokenizer(bf.readLine());

// 새로 입력할 때마다
st = new StringTokenizer(bf.readLine());
```

### 이차원 구간 합
합 배열 저장하기
`D[i][j] = D[i][j-1] + D[i-1][j] - D[i-1][j-1] + A[i][j]`

결과 = `D[x2][y2] - D[x1-1][y2] - D[x2][y1-1] + D[x1-1][y1-1]`


### nC2
n개의 선택지 중에서 2개 선택
`C[i]*(C[i]-1))/2`