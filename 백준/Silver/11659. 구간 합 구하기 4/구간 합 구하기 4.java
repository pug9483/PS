import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {        
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int N, M;
    public static int[] tree;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tree = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken());
            update(i, num);
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken()) - 1;
            int right = Integer.parseInt(st.nextToken()) - 1;
            sb.append(sum(right) - sum(left-1)).append("\n");
        }
        System.out.print(sb);
    }
 
    public static void update(int pos, int value){
        pos++;
        while(pos <= N){
            tree[pos] += value;
            pos += (pos & -pos);
        }
    }
    
    public static int sum(int pos){
        int ret = 0;
        pos++;
        while(pos > 0){
            ret += tree[pos];
            pos &= (pos - 1);
        }
        return ret;
    }
}