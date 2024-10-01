import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int G;
    public static int P;
    public static int[] A;
    public static int[] parent;
    
    public static void main(String[] args) throws IOException{
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        A = new int[P];
        parent = new int[G+1];
        
        for(int i = 0; i < P; i++){
            A[i] = Integer.parseInt(br.readLine());
        }
        for(int i = 1; i <= G; i++) parent[i] = i;
        System.out.println(solve());
    }   
    
    public static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
    
    public static void union(int a, int b){
        int u = find(a), v = find(b);
        if(u == v) return;
        parent[v] = u;
    }
    
    public static int solve(){
        int ret = 0;
        
        for(int i = 0; i < P; i++){
            int pos = A[i];
            if(find(pos) == 0) return ret;
            ret++;
            union(find(pos) - 1, find(pos));
        }
        return ret;
    }
}