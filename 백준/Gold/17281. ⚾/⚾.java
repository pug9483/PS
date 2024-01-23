import java.io.*;
import java.util.*;

public class Main {  
    /*
    야구: 9명 공, 수
    N이닝 게임 진행(하나의 이닝은 공,수로 이루어짐)
    3아웃이면 공, 수 교대
    타순은 변경 불가. 다음 이닝때는 순서 유지 상태로 공격 시작.
    1루,2루,3루 거쳐 홈에 도달하면 1점(이닝 시작 시, 주자 없음)
    타자 공격 결과: 안타, 2루타, 3루타, 홈런, 아웃
      - 안타(1): 타자와 모든 주자 1루씩 진루
      - 2루타(2): .. 2루씩 진루
      - 3루타(3): .. 3루씩 진루
      - 홈런(4): .. 홈으로진루
      - 아웃(0): 모든 주자 진루 불가. 아웃 하나 증가
    감독은 4번 타자 제외 나머지 선수 정해야 함.
    출력: 가장 많은 득점을 하는 타순 때의 득점 수 구하기
    */
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N = 9;
    public static int M; // 이닝 수
    public static List<List<Integer>> A = new ArrayList<>();
    public static boolean[] visited = new boolean[N];
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < M; i++){
            List<Integer> list = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                list.add(Integer.parseInt(st.nextToken()));
            }
            A.add(list);
        }
        
        int[] seq = new int[N];
        seq[3] = 0;
        visited[0] = true;
        System.out.println(solve(0, seq));
    }
    
    // seq[i] = j: i번 타석에 j번 타자가 있다.
    public static int solve(int here, int[] seq){ // here번 타석
        if(here >= N)
            return getScore(seq);
        
        if(here == 3) return solve(here+1, seq);

        int ret = 0;
        for(int player = 0; player < N; player++){
            if(visited[player]) continue;
            visited[player] = true;
            seq[here] = player;
            ret = Math.max(ret, solve(here+1, seq));
            visited[player] = false;
        }
        return ret;
    }
    
    public static int getScore(int[] seq){
        int ret = 0;
        
        int here = 0;
        for(int round = 0; round < M; round++){
            int out = 0; 
            int[] goals = new int[5]; // 0루, 1루, 2루, 3루, 홈
            List<Integer> hits = A.get(round);
            while(true){
                int player = seq[here];
                out += hitting(hits.get(player), goals);
                here = (here + 1) % 9;
                if(out == 3) break;
                ret += goals[4];
                goals[4] = 0;
            }   
        }
        return ret;
    }
    
    public static int hitting(int hit, int[] goals){
        if(hit == 0) return 1;
    
        goals[0] = 1;
        for(int goal = 3; goal >= 0; goal--){
            goals[Math.min(4, goal+hit)] += goals[goal];
            goals[goal] = 0;
        }
        return 0;
    }
}