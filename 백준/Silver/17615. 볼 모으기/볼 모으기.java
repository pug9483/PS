import java.io.*;
import java.util.*;

public class Main {  
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static String S;

    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        S = br.readLine();
        int ret = 987654321;
        
        // RRR...BBB
        // 1. R을 앞으로 옮기는 경우
        int a = 0;
        boolean seq = true;
        for(int i = 0; i < N; i++){
            if(S.charAt(i) == 'R' && !seq) a++;
            else if(S.charAt(i) == 'B') seq = false;
        }
        ret = Math.min(ret, a);
        
        // 2. B를 뒤로 옮기는 경우
        int b = 0;
        seq = true;
        for(int i = N-1; i >= 0; i--){
            if(S.charAt(i) == 'B' && !seq) b++;
            else if(S.charAt(i) == 'R') seq = false;
        }
        ret = Math.min(ret, b);
        
        // BBB...RRR
        // 1. B를 앞으로 옮기는 경우
        int c = 0;
        seq = true;
        for(int i = 0; i < N; i++){
            if(S.charAt(i) == 'B' && !seq) c++;
            else if(S.charAt(i) == 'R') seq = false;
        }
        ret = Math.min(ret, c);
        
        // 2. R을 뒤로 옮기는 경우
        int d = 0;
        seq = true;
        for(int i = N-1; i >= 0; i--){
            if(S.charAt(i) == 'R' && !seq) d++;
            else if(S.charAt(i) == 'B') seq = false;
        }
        ret = Math.min(ret, d);
        System.out.println(ret);
    }
}