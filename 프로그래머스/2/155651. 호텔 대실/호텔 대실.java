class Solution {
    public int SIZE = 1450;
    
    public int solution(String[][] book_time) {
        int[] rooms = new int[SIZE]; 

        for (String[] time : book_time) {
            int start = parseTime(time[0]);
            int end = parseTime(time[1]) + 10; 

            rooms[start] += 1; 
            rooms[end] -= 1;   
        }

        int answer = 0;
        int currentRooms = 0;

        for (int i = 0; i < SIZE; i++) {
            currentRooms += rooms[i];
            answer = Math.max(currentRooms, answer);
        }

        return answer;
    }

    private int parseTime(String time) {
        String[] split = time.split(":");
        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);
        return hour * 60 + minute;
    }
}