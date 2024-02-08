import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static int N; 
	public static int M; 
	public static int K; 
	public static long[] bit1, bit2;
    public static StringBuilder sb = new StringBuilder();
    
	public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        bit1 = new long[N+1];
        bit2 = new long[N+1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            rangeUpdate(i, i, Long.parseLong(st.nextToken()));
        }
        
        for(int i = 0; i < M+K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a == 1) rangeUpdate(b, c, Long.parseLong(st.nextToken()));
            else sb.append(query(b,c)).append("\n");
        }
        System.out.print(sb);
    }    
    
    public static void rangeUpdate(int left, int right, long diff){
        update(1, left, diff);
        update(1, right+1, -diff);
        update(2, left, diff * (left-1));
        update(2, right+1, -diff * right);
    }
    
   public static void update(int bitType, int pos, long diff) {
        long[] bit = bitType==1 ? bit1 : bit2;
        while (pos <= N) {
            bit[pos] += diff;
            pos += pos&-pos;
        }
    }
    
    public static long query(int a, int b) {
        return prefixSum(b) - prefixSum(a-1);
    }
    
    public static long prefixSum(int pos) {
        return getBitValue(1, pos) * pos - getBitValue(2, pos);
    }
    
    public static long getBitValue(int bitType, int pos) {
        long[] bit = bitType==1 ? bit1 : bit2;
        long answer = 0;
        while (pos > 0) {
            answer += bit[pos];
            pos -= pos&-pos;
        }
        return answer;
    }
}