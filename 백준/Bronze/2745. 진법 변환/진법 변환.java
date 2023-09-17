import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static String S;
    public static int N;
    public static int f;
    public static int[] A = new int[36];
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = st.nextToken();
        f = Integer.parseInt(st.nextToken());
        N = S.length();
        System.out.println(go(0, N-1));
    }
    
    public static long go(int i, int cnt){
        if(i == S.length()) return 0;
        char c = S.charAt(i);
        int num = 0;
        if(c >= '0' && c <= '9') num = c - '0';
        else num = c - 'A' + 10;
        return (int)Math.pow(f, cnt) * num + go(i+1, cnt-1);
    }
}