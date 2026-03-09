import java.util.*;

class Solution {
    public final int SIZE = 1000000;
    public int solution(int x, int y, int n) {
        int[] dist = new int[SIZE + 1];
        Arrays.fill(dist, -1);
        
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        dist[x] = 0;
        
        while(!q.isEmpty()){
            int num = q.poll();
            
            if(num*2 <= SIZE && dist[num*2] == -1){
                dist[num*2] = dist[num] + 1;
                q.add(num*2);
            }
            if(num*3 <= SIZE && dist[num*3] == -1){
                dist[num*3] = dist[num] + 1;
                q.add(num*3);
            }
            if(num+n <= SIZE && dist[num+n] == -1){
                dist[num+n] = dist[num] + 1;
                q.add(num+n);
            }
        }
        
        return dist[y];
    }
}