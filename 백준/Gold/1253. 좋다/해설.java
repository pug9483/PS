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
        int ret = 0;
        
        for(int i = 0; i < N; i++){
            int num = A[i];
            int left = 0, right = N-1;
            
            while(left < right){
                int sum = A[left] + A[right];
                if(sum == num){
                    if(i == left) left++;
                    else if(i == right) right--;
                    else{
                        ret++;
                        break;
                    }
                }
                else if(sum > num) right--;
                else left++;
            }
        }
        return ret;
    }
}
