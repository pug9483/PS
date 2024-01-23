import java.io.*;
import java.util.*;

public class Main {  
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[] A;
    public static Map<Integer, Integer> map = new HashMap<>();
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
        }
        Arrays.sort(A);
        System.out.println(solve());
    }
    
    public static int solve(){
        if(A.length <= 2) return 0;    
        int ret = 0;
        for(int k = 0; k < N; k++){
            for(int i = 0; i < N; i++){
                if(i == k) continue;
                int num = A[k] - A[i];
                int j = Arrays.binarySearch(A, num);
                if(check(i, j, k)){
                    ret++;
                    break;
                }
            }
        }
        return ret;
    }
    
    public static boolean check(int i, int j, int k){
        if(i < 0 || j < 0 || k < 0) return false;
        if(i == j){
            if(A[i] == A[k] && map.get(A[i]) >= 3) return true;
            if(A[i] != A[k] && map.get(A[i]) >= 2) return true;
            return false;
        }
        if(i != j){
            if(k == j){
                if(A[i] == A[j] && map.get(A[j]) >= 3) return true;
                if(A[i] != A[j] && map.get(A[j]) >= 2) return true;
            } 
            else return true;
        }
        return false;
    }
}
