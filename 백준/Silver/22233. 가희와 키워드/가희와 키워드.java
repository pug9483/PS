import java.io.*;
import java.util.*;

public class Main {   
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int N;
    public static int M;
    public static Set<String> set = new HashSet<>();
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++){
            String keyWord = br.readLine();
            set.add(keyWord);
        }
        for(int i = 0; i < M; i++){
            String[] splits = br.readLine().split(",");
            sb.append(solve(splits)).append("\n");
        }
        System.out.print(sb);
    }
    
    public static int solve(String[] splits){
        for(String s : splits)
            set.remove(s);
        return set.size();
    }
}