import java.util.*;

class Solution {
    static class Point{
        int y;
        int x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    
    public int N;
    public int M;
    public int[] dy = {1, -1, 0, 0};
    public int[] dx = {0, 0, 1, -1};
    
    public int[] solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        List<Integer> list = new ArrayList<>();
        boolean[][] visited = new boolean[N][M];
        
        for(int y = 0; y < N; y++){
            for(int x = 0; x < M; x++){
                if(visited[y][x] || maps[y].charAt(x) == 'X') continue;
                list.add(bfs(y, x, maps, visited));
            }
        }
        
        if(list.isEmpty()){
            return new int[]{-1};
        }
        
        return list.stream()
            .mapToInt(Integer::intValue)
            .sorted()
            .toArray();
    }
    
    public int bfs(int y, int x, String[] maps, boolean[][] visited){
        int ret = 0;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(y, x));
        visited[y][x] = true;
        
        while(!q.isEmpty()){
            Point p = q.poll();
            ret += maps[p.y].charAt(p.x) - '0';
            for(int dir = 0; dir < 4; dir++){
                int ny = p.y + dy[dir];
                int nx = p.x + dx[dir];
                if(ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                if(maps[ny].charAt(nx) == 'X' || visited[ny][nx]) continue;
                q.add(new Point(ny, nx));
                visited[ny][nx] = true;
            }
        }
        return ret;
    }
}
