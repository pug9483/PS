import java.util.*;
class Solution {
    static class Time {
        int start;
        int end;
        
        public Time(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    
    int N;
    List<Time> list = new ArrayList<>();
    public int solution(String[][] bookTime) {
        int ret = 0;
        N = bookTime.length;
        
        for(int i = 0; i < N; i++){
            String[] time = bookTime[i];
            String[] start = time[0].split(":");
            String[] end = time[1].split(":");
            int startTime = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
            int endTime = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]);
            
            list.add(new Time(startTime, endTime));
        }
        
        list.sort((o1, o2) -> Integer.compare(o1.start, o2.start));
        
        PriorityQueue<Time> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.end, o2.end));
        for(Time time: list) {
            if(!pq.isEmpty()){
                Time first = pq.peek();
                if(first.end + 10 <= time.start){
                    pq.poll();
                }    
            }
            pq.add(time);
            ret = Math.max(ret, pq.size());
        }
        return ret;
    }
}