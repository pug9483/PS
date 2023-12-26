import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static String S;

    public static void main(String[] args) throws IOException {
        S = br.readLine();
        String T = br.readLine();
        StringBuilder sb = new StringBuilder(T);
        System.out.println(go(T) ? 1 : 0);
    }

    public static boolean go(String str) {
        if (str.length() == S.length()) {
            return str.equals(S);
        }
        if(str.charAt(str.length()-1) == 'A'){
            if(go(str.substring(0, str.length() - 1)))
                return true;
        }
        if(str.charAt(0) == 'B'){
            StringBuilder sb = new StringBuilder();
            sb.append(str.substring(1, str.length()));
            if(go(sb.reverse().toString()))
                return true;
        }
        return false;
    }
}