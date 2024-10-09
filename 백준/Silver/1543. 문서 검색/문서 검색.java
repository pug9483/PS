import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String S;
    static String T;
    public static void main(String[] args) throws Exception {
        S = br.readLine();
        T = br.readLine();

        int fromIndex = 0;
        int ret = 0;
        int last;
        while ((last = S.indexOf(T, fromIndex)) != -1) {
            ret++;
            fromIndex = last + T.length();
        }

        System.out.println(ret);
    }

}