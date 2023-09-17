import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        int ret = 0;
        for(int i = 5; i <= N; i *= 5)
            ret += N / i;
        System.out.println(ret);
    }
} 