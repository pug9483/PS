import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static int N; 
	public static int M; 
	public static List<List<Integer>> parties = new ArrayList<>();
    public static List<Integer> knownPeople = new ArrayList<>();
    public static List<List<Integer>> graph = new ArrayList<>();
    public static Set<Integer> set = new HashSet<>();
    public static boolean[] visited;
    
	public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        st = new StringTokenizer(br.readLine());
        int knownCount = Integer.parseInt(st.nextToken());
        for(int i = 0; i < knownCount; i++)
            knownPeople.add(Integer.parseInt(st.nextToken()));
        for(int i = 0; i <= N; i++)
            graph.add(new ArrayList<>());
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            parties.add(new ArrayList<>());
            for(int j = 0; j < size; j++)
                parties.get(i).add(Integer.parseInt(st.nextToken()));
        }
        System.out.println(solve());
    }
    
    public static int solve(){
        makeGraph();
        dfs();
        return canGoToParty();
    }
    
    
    public static void makeGraph(){
        for(List<Integer> peoples: parties){
            for(int people: peoples){
                for(List<Integer> others: parties){
                    if(contain(people, others)){
                        drawEdge(people, others);
                    }
                }
            }
        }
    }
    
    public static boolean contain(int people, List<Integer> others){
        for(int other: others){
            if(other == people) return true;
        }
        return false;
    }
    
    public static void drawEdge(int people, List<Integer> others){
        for(int other: others){
            if(other == people) continue;
            graph.get(people).add(other);
            graph.get(other).add(people);
        }
    }
    
    public static void dfs(){
        for(int people: knownPeople){
            if(visited[people]) continue;
            dfsUtil(people);
        }
    }
    
    public static void dfsUtil(int here){
        visited[here] = true;
        
        for(int there: graph.get(here)){
            if(visited[there]) continue;
            dfsUtil(there);
        }
    }
    
    public static int canGoToParty(){
        int ret = 0;
        for(List<Integer> party: parties){
            boolean canGo = true;
            for(int other: party){
                if(visited[other]){
                    canGo = false;
                    break;
                }
            }
            if(canGo) ret++;
        }
        return ret;
    }
}