import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static String S;
    public static String[] s1 = {"", "1", "10", "11"};
    public static String[] s2 = {"000", "001", "010", "011", "100", "101", "110", "111"};
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        S = br.readLine();
        if(S.charAt(0) == '0' && S.length() == 1) System.out.println(0);
        else{
            for(int i = 0; i < S.length(); i++){
                int c = S.charAt(i) - '0';
                if(i == 0 && c >= 0 && c <= 3) sb.append(s1[c]);
                else sb.append(s2[c]);
            }
            System.out.println(sb);
        }
    }
}