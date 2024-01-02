import java.io.*;
import java.util.*;

public class Main {  
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static String S;

    public static void main(String[] args) throws IOException{
        S = br.readLine();
        N = S.length();
        int aCnt = 0;
        for(int i = 0; i < N; i++){
            if(S.charAt(i) == 'a') aCnt++;
        }
        S = S + S;
        int ret = 987654321;
        
        for(int i = 0; i < N; i++){
            int diff = 0;
            for(int j = i; j < i + aCnt; j++){
                if(S.charAt(j) == 'b') diff++;
            }   
            ret = Math.min(ret, diff);
        }
        System.out.println(ret);
    }
}