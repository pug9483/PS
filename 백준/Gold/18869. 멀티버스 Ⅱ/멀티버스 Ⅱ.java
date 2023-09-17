import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    static class Node{
        int index;
        int num;
        public Node(int index, int num){
            this.index = index;
            this.num = num;
        }
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N, M;
    public static int[][] A;
    public static List<List<Integer>> list = new ArrayList<>();
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 우주의 개수
        N = Integer.parseInt(st.nextToken()); // 행성의 개수
        A = new int[M][N];
        int ret = 0;
        for(int i = 0; i < M; i++) list.add(new ArrayList<>());
        
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                int num = Integer.parseInt(st.nextToken());
                A[i][j] = num;
                list.get(i).add(num);
            }
            Collections.sort(list.get(i));
        }
        
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A[i][j] = Collections.binarySearch(list.get(i), A[i][j]);
        
        for(int i = 0; i < M; i++){
            for(int j = i+1; j < M; j++){
                if(Arrays.equals(A[i], A[j])) ret++;
            }
        }
        System.out.println(ret);
    }   
    

    
    public static boolean check(int[] B, int[] C){
        for(int i = 0; i < N; i++)
            if(B[i] != C[i]) return false;
        return true;
    }
} 