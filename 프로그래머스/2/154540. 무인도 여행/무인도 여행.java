import java.util.*;

class Solution {
    int N;
    int M;
    char[][] board;
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, 1, -1};
    
    public int[] solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        board = new char[N][M];
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                board[i][j] = maps[i].charAt(j);
            }
        }
        
        boolean[][] visited = new boolean[N][M];
        List<Integer> list = new ArrayList<>();
        for(int y = 0; y < N; y++){
            for(int x = 0; x < M; x++){
                if(visited[y][x] || board[y][x] == 'X') continue;
                list.add(bfs(visited, new int[]{y, x}));
            }
        }
        
        int[] answer = list.size() == 0 ? new int[]{-1} : new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        Arrays.sort(answer);
        return answer;
    }
    
    public int bfs(boolean[][] visited, int[] src){
        int ret = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(src);
        visited[src[0]][src[1]] = true;
        
        while(!q.isEmpty()){
            int[] here = q.poll();
            ret += board[here[0]][here[1]] - '0';
            for(int dir = 0; dir < 4; dir++){
                int ny = here[0] + dy[dir];
                int nx = here[1] + dx[dir];
                if(ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx]) continue;
                if(board[ny][nx] == 'X') continue;
                visited[ny][nx] = true;
                q.add(new int[]{ny, nx});
            }
        }
        return ret;
    }
}