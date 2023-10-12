import java.io.*;
import java.util.*;

public class Main {   
    static class Vector{
        public int x;
        public int y;
        
        public Vector(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static Vector[] v = new Vector[3];
    
    public static void main(String[] args) throws IOException{
        for(int i = 0; i < 3; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            v[i] = new Vector(x, y);
        }
        System.out.println(solve());
    }
    
    public static int solve(){
        int b = v[1].y - v[0].y;
        int a = v[1].x - v[0].x;
        
        int right = b * (v[2].x - v[0].x) + a*v[0].y;
        int left = a * v[2].y;
        
        // System.out.printf("%d, %d, %d, %d\n", a, b, left, right);
        if(left == right) return 0;
        if(left > right) return 1;
        return -1;
    }
}