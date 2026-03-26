import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int n = jobs.length;
        Node[] arr = new Node[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Node(i, jobs[i][0], jobs[i][1]);
        }
        
        Arrays.sort(arr, (o1, o2) -> {
            if (o1.start != o2.start) return Integer.compare(o1.start, o2.start);
            return Integer.compare(o1.idx, o2.idx);
        });
        
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.diff != o2.diff) return Integer.compare(o1.diff, o2.diff);
            if (o1.start != o2.start) return Integer.compare(o1.start, o2.start);
            return Integer.compare(o1.idx, o2.idx);
        });
        
        int time = 0;
        int totalTurnaround = 0;
        int pointer = 0;
        int completed = 0;
        
        while(completed < n){
            while(pointer < n && arr[pointer].start <= time){
                pq.add(arr[pointer++]);
            }
            
            if(pq.isEmpty()){
                time = arr[pointer].start;
                continue;
            }
            
            Node here = pq.poll();
            time += here.diff;
            totalTurnaround += (time - here.start);
            completed++;
        }
        
        return totalTurnaround / n;
    }
    
    static class Node {
        int idx;
        int start;
        int diff;
        
        public Node(int idx, int start, int diff){
            this.idx = idx;
            this.start = start;
            this.diff = diff;
        }
    }
}