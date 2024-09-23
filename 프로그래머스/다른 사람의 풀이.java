package 프로그래머스;

public class Solution {
    private int totalVideoRuntime; 
    private int opStartTime;
    private int opEndTime; 

    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {

        int time = convertTimeToSeconds(pos);
        totalVideoRuntime = convertTimeToSeconds(video_len); 

        opStartTime = convertTimeToSeconds(op_start); 
        opEndTime = convertTimeToSeconds(op_end);

        for (String command : commands) {
            time = handleButton(command, time); 
        }

        if (isInOpening(time)) {
            time = opEndTime;
        }

        String answer = convertSecondsToTime(time);
        return answer;
    }

    public int convertTimeToSeconds(String time) {
        String[] splitted_time = time.split(":");
        int min = Integer.parseInt(splitted_time[0]); 
        int sec = Integer.parseInt(splitted_time[1]);

        return sec + min * 60; 
    }

    public String convertSecondsToTime(int seconds) {
        int min = seconds / 60; 
        int sec = seconds % 60;

        String minStr;
        if (min < 10) {
            minStr = "0" + String.valueOf(min);
        } else {
            minStr = String.valueOf(min);
        }

        String secStr;
        if (sec < 10) {
            secStr = "0" + String.valueOf(sec); 
        } else {
            secStr = String.valueOf(sec); 
        }

        return minStr + ":" + secStr;
    }

    public int handleButton(String command, int currPos) {
        if (isInOpening(currPos)) {
            currPos = opEndTime;
        }

        switch (command) {
            case "prev": 
                return goBack(currPos); 
            case "next": 
                return goFront(currPos); 
            default: 
                return currPos;
        }
    }

    public int goBack(int currPos) {
        int newPos = currPos - 10;
        if (newPos < 0) {
            newPos = 0; 
        }
        return newPos;
    }

    public int goFront(int currPos) {
        int newPos = currPos + 10;
        if (totalVideoRuntime - newPos < 10) {
            newPos = totalVideoRuntime;
        }
        return newPos;
    }

    public boolean isInOpening(int currPos) {
        if (opStartTime <= currPos && currPos <= opEndTime) {
            return true;
        } 
        return false;
    }
}
