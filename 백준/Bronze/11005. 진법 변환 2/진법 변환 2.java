import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int num;
    public static int f;
    public static String ret = "";
    public static String[] s = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                             "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                             "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                             "U", "V", "W", "X", "Y", "Z"};
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        num = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());
        go(num, f);
        System.out.print(sb);
    }
    
    public static void go(int num, int f){
        if(num == 0) return;
        int mod = num % f;
        int div = num / f;
        go(div, f);
        sb.append(s[mod]);
    }
}