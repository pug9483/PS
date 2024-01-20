import java.io.*;
import java.util.*;

public class Main {  
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int ret = 0;
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int[][] A = new int[N][N];
        
        for(int i = 0; i < N; i++){    
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }    
        solve(0, A);
        System.out.println(ret);
    }

    public static void solve(int cnt, int[][] A){
        if(cnt == 5){
            ret = Math.max(ret, getMax(A));
            return;
        }
        // 가지치기 코드
        int anticipatedMaxNum = (int)Math.pow(2, 5-cnt) * getMax(A);
        if(anticipatedMaxNum <= ret) return;
        
        for(int dir = 0; dir < 4; dir++){
            int[][] newA = copy(A);
            move(newA, dir);
            solve(cnt+1, newA);
        }        
    }
    
    public static void move(int[][] A, int dir){
        switch(dir) {
            case 0:
                for(int i = 0; i < N; i++) {
                    int index = 0;
                    int block = 0;
                    for(int j = 0; j < N; j++) {
                        if(A[j][i] != 0) {
                            if(block == A[j][i]) {
                                A[index - 1][i] = block * 2;
                                block = 0;
                                A[j][i] = 0;
                            }
                            else {
                                block = A[j][i];
                                A[j][i] = 0;
                                A[index][i] = block;
                                index++;
                            }
                        }
                    }
                }
                break;
            case 1:
                for(int i = 0; i < N; i++) {
                    int index = N - 1;
                    int block = 0;
                    for(int j = N - 1; j >= 0; j--) {
                        if(A[j][i] != 0) {
                            if(block == A[j][i]) {
                                A[index + 1][i] = block * 2;
                                block = 0;
                                A[j][i] = 0;
                            }
                            else {
                                block = A[j][i];
                                A[j][i] = 0;
                                A[index][i] = block;
                                index--;
                            }
                        }
                    }
                }
                break;
            case 2:
                for(int i = 0; i < N; i++) {
                    int index = 0;
                    int block = 0;
                    for(int j = 0; j < N; j++) {
                        if(A[i][j] != 0) {
                            if(block == A[i][j]) {
                                A[i][index - 1] = block * 2;
                                block = 0;
                                A[i][j] = 0;
                            }
                            else {
                                block = A[i][j];
                                A[i][j] = 0;
                                A[i][index] = block;
                                index++;
                            }
                        }
                    }
                }
                break;
            case 3:
                for(int i = 0; i < N; i++) {
                    int index = N - 1;
                    int block = 0;
                    for(int j = N - 1; j >= 0; j--) {
                        if(A[i][j] != 0) {
                            if(block == A[i][j]) {
                                A[i][index + 1] = block * 2;
                                block = 0;
                                A[i][j] = 0;
                            }
                            else {
                                block = A[i][j];
                                A[i][j] = 0;
                                A[i][index] = block;
                                index--;
                            }
                        }
                    }
                }
                break;
        }
    }
    
    public static int[][] copy(int[][] src) { 
        int[][] arr = new int[N][N];
 
        for (int i = 0; i < N; i++) 
            System.arraycopy(src[i], 0, arr[i], 0, src[i].length);
 
        return arr;
    }
    
    public static int getMax(int[][] A){
        int ret = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                ret = Math.max(ret, A[i][j]);
            }
        }
        return ret;
    }
}