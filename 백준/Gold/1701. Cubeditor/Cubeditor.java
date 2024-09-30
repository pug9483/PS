import java.io.*;
import java.util.*;

public class Main {       
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static String N;
    public static int m;
    
    public static void main(String[] args) throws IOException{
        N = br.readLine();
        m = N.length();
        System.out.println(solve());
    }   
    
    public static int[][] getPartialMatch(String N){
        int[][] pi = new int[m][m];
        
        for(int i = 0; i < m; i++){
            int begin = i + 1;
            int matched = 0;
            while(begin + matched < m){
                if(N.charAt(begin + matched) == N.charAt(i + matched)){
                    matched++;
                    pi[i][begin + matched - 1] = matched;
                }
                else{
                    if(matched == 0) begin++;
                    else{
                        begin += matched - pi[i][i + matched-1];
                        matched = pi[i][i + matched-1];
                    }
                }
            }
        }
        return pi;
    }
    
    public static int solve(){
        int[][] pi = getPartialMatch(N);
        int ret = 0;
        
        for(int i = 0; i < m; i++){
            for(int j = i+1; j < m; j++){
                ret = Math.max(ret, pi[i][j]);
            }        
        }
        return ret;
    }
}