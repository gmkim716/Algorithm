const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let N;
let map = [];
let ans = 0;

rl.on("line", (line) => {
  if (!N) {
    N = parseInt(line); // 첫 번째 입력은 N
  } else {
    map.push(line.split("")); // 이후 입력은 보드 상태를 저장
    if (map.length === N) {
      // 모든 입력을 받으면 계산 시작
      findAns();
      console.log(ans);
      rl.close();
    }
  }
});

function findAns() {
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      // 오른쪽과 교환
      if (j + 1 < N) {
        swap(i, j, i, j + 1);
        checkMaxLength();
        swap(i, j, i, j + 1); // 원상복구
      }

      // 아래쪽과 교환
      if (i + 1 < N) {
        swap(i, j, i + 1, j);
        checkMaxLength();
        swap(i, j, i + 1, j); // 원상복구
      }
    }
  }
}

function swap(x1, y1, x2, y2) {
  const temp = map[x1][y1];
  map[x1][y1] = map[x2][y2];
  map[x2][y2] = temp;
}

function checkMaxLength() {
  // 가로 방향에서 연속된 사탕 찾기
  for (let i = 0; i < N; i++) {
    let rowMax = 1;
    for (let j = 0; j < N - 1; j++) {
      if (map[i][j] === map[i][j + 1]) {
        rowMax++;
      } else {
        ans = Math.max(ans, rowMax);
        rowMax = 1;
      }
    }
    ans = Math.max(ans, rowMax);
  }

  // 세로 방향에서 연속된 사탕 찾기
  for (let j = 0; j < N; j++) {
    let colMax = 1;
    for (let i = 0; i < N - 1; i++) {
      if (map[i][j] === map[i + 1][j]) {
        colMax++;
      } else {
        ans = Math.max(ans, colMax);
        colMax = 1;
      }
    }
    ans = Math.max(ans, colMax);
  }
}
