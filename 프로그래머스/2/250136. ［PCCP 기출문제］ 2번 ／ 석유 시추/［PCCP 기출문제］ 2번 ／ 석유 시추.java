import java.util.*;

class Solution {
    static class Point{
        int y, x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    
    int N; // 세로
    int M; // 가로
    int[] dy = new int[]{1, -1, 0, 0};
    int[] dx = new int[]{0, 0, 1, -1};
    
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        
        List<Integer> groupLands = new ArrayList<>();
        groupLands.add(0);
        int groupCount = 1;
        int[][] group = new int[N][M];
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(land[i][j] == 1 && group[i][j] == 0){
                    int ret = bfs(i, j, land, groupCount, group);
                    groupLands.add(ret);
                    groupCount++;
                }
            }
        }
        
        int ret = 0;
        for(int j = 0; j < M; j++){
            Set<Integer> set = new HashSet<>();
            int sum = 0;
            for(int i = 0; i < N; i++){
                set.add(group[i][j]);
            }
            for(int i : set){
                sum += groupLands.get(i);
            }
            ret = Math.max(ret, sum);
        }
    
        return ret;
    }
    
    public int bfs(int sy, int sx, int[][] land, int groupCount, int[][] group){
        int ret = 0;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(sy, sx));
        group[sy][sx] = groupCount;
        
        while(!q.isEmpty()){
            Point here = q.poll();
            ret++;
            for(int dir = 0; dir < 4; dir++){
                int ny = here.y + dy[dir];
                int nx = here.x + dx[dir];
                if(ny < 0 || ny >= N || nx < 0 || nx >= M || group[ny][nx] != 0 || land[ny][nx] != 1) continue;
                group[ny][nx] = groupCount;
                q.add(new Point(ny, nx));
            }   
        }
        return ret;
    }
}