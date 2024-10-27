function solution(video_len, pos, op_start, op_end, commands) {
  var curr = timeToSeconds(pos);
  var opStart = timeToSeconds(op_start);
  var opEnd = timeToSeconds(op_end);
  var videoEnd = timeToSeconds(video_len);

  // 처음 위치가 오프닝 구간일 경우를 고려해 jump 수행
  curr = jumpFunc(curr, opStart, opEnd);

  commands.forEach((command) => {
    switch (command) {
      case "next":
        curr = nextFunc(curr, videoEnd);
        break;
      case "prev":
        curr = prevFunc(curr);
        break;
      default:
        break;
    }
    curr = jumpFunc(curr, opStart, opEnd);
  });

  return secondsToTime(curr);
}

function prevFunc(curr) {
  if (curr < 10) {
    curr = 0;
  } else {
    curr -= 10;
  }

  return curr;
}

function nextFunc(curr, videoEnd) {
  if (curr + 10 > videoEnd) {
    curr = videoEnd;
  } else {
    curr += 10;
  }

  return curr;
}

function jumpFunc(curr, opStart, opEnd) {
  if (opStart <= curr && curr <= opEnd) {
    curr = opEnd;
  }

  return curr;
}

function timeToSeconds(time) {
  const timeSplit = time.split(":");
  let min = parseInt(timeSplit[0], 10);
  let sec = parseInt(timeSplit[1], 10);

  return sec + min * 60;
}

function secondsToTime(time) {
  var min = Math.floor(time / 60);
  var sec = time % 60;

  var minStr = String(min).padStart(2, "0");
  var secStr = String(sec).padStart(2, "0");

  return `${minStr}:${secStr}`;
}
