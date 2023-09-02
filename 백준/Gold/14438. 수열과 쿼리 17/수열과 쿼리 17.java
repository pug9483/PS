import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int N, M;
    public static int[] A;
    public static int[] tree;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        tree = new int[4*N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        init(0, N-1, 1);
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());            
            if(type == 1){
                int index = Integer.parseInt(st.nextToken()) - 1;
                int value = Integer.parseInt(st.nextToken());
                update(index, value, 0, N-1, 1);
            }
            else{
                int left = Integer.parseInt(st.nextToken()) - 1;
                int right = Integer.parseInt(st.nextToken()) - 1;
                sb.append(query(left, right, 0, N-1, 1)).append("\n");
            }
        }
        System.out.print(sb);
    }
    
    public static int init(int left, int right, int node){
        if(left == right) return tree[node] = A[left];
        int mid = (left + right) / 2;
        int leftMin = init(left, mid, node*2);
        int rightMin = init(mid+1, right, node*2 + 1);
        return tree[node] = Math.min(leftMin, rightMin);
    }
    
    public static int update(int index, int value, int nodeLeft, int nodeRight, int node){
        if(index < nodeLeft || nodeRight < index) return tree[node];
        if(nodeLeft == nodeRight) return tree[node] = value;
        int nodeMid = (nodeLeft + nodeRight) / 2;
        int updateLeft = update(index, value, nodeLeft, nodeMid, node*2);
        int updateRight = update(index, value, nodeMid+1, nodeRight, node*2 + 1);
        return tree[node] = Math.min(updateLeft, updateRight);
    }
    
    public static int query(int left, int right, int nodeLeft, int nodeRight, int node){
        if(right < nodeLeft || nodeRight < left) return Integer.MAX_VALUE;
        if(left <= nodeLeft && nodeRight <= right) return tree[node];
        int nodeMid = (nodeLeft + nodeRight) / 2;
        int leftMin = query(left, right, nodeLeft, nodeMid, node*2);
        int rightMin = query(left, right, nodeMid+1, nodeRight, node*2 + 1);
        return Math.min(leftMin, rightMin);
    }
}