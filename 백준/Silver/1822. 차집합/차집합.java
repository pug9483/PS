import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N, M;
    public static Set<Integer> A = new HashSet<>();
    public static Set<Integer> B = new HashSet<>();
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) A.add(Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) B.add(Integer.parseInt(st.nextToken()));
        A.removeAll(B);
        System.out.println(A.size());
        A.stream().sorted().forEach(s -> System.out.print(s + " "));
    }   
}