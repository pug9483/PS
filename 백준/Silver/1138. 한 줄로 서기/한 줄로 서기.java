import java.io.*;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[] A;
    public static boolean[] visited;
    public static int[] arr;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        arr = new int[N];
        visited = new boolean[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());
        
        solve(0);
    }
    
    public static boolean solve(int here){
        if(here == N){
            for(int i = 0; i < N; i++){
                int cnt = 0;
                for(int j = 0; j < i; j++)
                    if(arr[i] < arr[j]) cnt++;
                if(cnt != A[arr[i]-1]) return false;
            }
            for(int i = 0; i < N; i++) System.out.print(arr[i] + " ");
            return true;
        }
        for(int i = 0; i < N; i++){
            if(visited[i]) continue;
            arr[here] = i+1;
            visited[i] = true;
            if(solve(here+1)) return true;
            visited[i] = false;
        }
        return false;
    }
}