import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int M;
    public static int[] pi;
	public static void main(String[] args) throws Exception {
        String s = "";
        while(!(s = br.readLine()).equals(".")){
            M = s.length();
            pi = new int[M];
            getPartialMatch(s);
            System.out.println(solve());
        }
    }
    
    public static void getPartialMatch(String s){
        int begin = 1;
        int matched = 0;
        while(begin + matched < M){
            if(s.charAt(begin + matched) == s.charAt(matched)){
                matched++;
                pi[begin + matched - 1] = matched;
            }
            else{
                if(matched == 0) begin++;
                else{
                    begin += matched - pi[matched-1];
                    matched = pi[matched-1];
                }
            }
        }
    }
    
    public static int solve(){
        return M % (M - pi[M-1]) != 0 ? 1 : M / (M - pi[M-1]);
    }
}