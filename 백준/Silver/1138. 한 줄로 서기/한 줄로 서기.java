import java.io.*;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[] A;
    public static List<Integer> ranks = new ArrayList<>();
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++)
            A[i] = Integer.parseInt(st.nextToken());
        for(int i = N; i > 0; i--)
            ranks.add(A[i], i);
        ranks.stream().forEach(s -> System.out.print(s + " "));
    }
}