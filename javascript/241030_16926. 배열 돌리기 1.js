function rotateArray(arr, N, M, R) {
  const layers = Math.min(N, M) / 2; // 전체 배열을 층 단위로 나눔

  for (let r = 0; r < R; r++) {
    for (let l = 0; l < layers; l++) {
      let top = l;
      let bottom = N - 1 - l;
      let left = l;
      let right = M - 1 - l;

      let temp = arr[top][left];

      for (let i = left; i < right; i++) {
        arr[top][i] = arr[top][i + 1];
      }

      for (let i = top; i < bottom; i++) {
        arr[i][right] = arr[i + 1][right];
      }

      for (let i = right; i > left; i--) {
        arr[bottom][i] = arr[bottom][i - 1];
      }

      for (let i = bottom; i > top; i--) {
        arr[i][left] = arr[i - 1][left];
      }

      arr[top + 1][left] = temp;
    }
  }

  return arr;
}

// 배열 출력 함수
function printArray(arr) {
  arr.forEach((row) => {
    console.log(row.join(" "));
  });
}

// 배열 회전 및 출력
const rotatedArr = rotateArray(arr, N, M, R);
printArray(rotatedArr);
