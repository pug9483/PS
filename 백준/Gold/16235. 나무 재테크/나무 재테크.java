import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Tree{
        public int y;
        public int x; // (y,x) : 위치
        public int age;
    
        public Tree(int y, int x, int age){
            this.y = y;
            this.x = x;
            this.age = age;
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
    public static int N; // N x N 크기
    public static int M; // 나무의 개수
    public static int K; // 연도
    public static int[][] board;
    public static int[][] plusBoard;
    public static ArrayDeque<Tree> trees = new ArrayDeque<>();
    public static List<Tree> deadTrees = new ArrayList<>();
    public static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    public static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        plusBoard = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                plusBoard[i][j] = Integer.parseInt(st.nextToken());
                board[i][j] = 5;
            }
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            trees.add(new Tree(y, x, age));
        } 
        System.out.println(solve());
    }
    
    public static void spring(){
        int i = 0;
        while(i < trees.size()){
            Tree tree = trees.pollFirst();
            if(board[tree.y][tree.x] >= tree.age){
                board[tree.y][tree.x] -= tree.age;
                tree.age++;
                i++;
                trees.addLast(tree);
            } else{
                deadTrees.add(tree);  
            }
        }
    }
    
    public static void summer(){
        for(Tree tree : deadTrees){
            board[tree.y][tree.x] += tree.age / 2;
        }
        deadTrees.clear();
    }
    
    public static void fall(){
        List<Tree> plusTrees = new ArrayList<>();
        for(Tree tree : trees){
            if(tree.age % 5 == 0){ 
                int y = tree.y;
                int x = tree.x;
                for(int dir = 0; dir < 8; dir++){
                    int ny = y + dy[dir];
                    int nx = x + dx[dir];
                    if(ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                    plusTrees.add(new Tree(ny, nx, 1));
                }
            }
        }
        for(Tree tree: plusTrees)
            trees.addFirst(tree);
    }
    
    public static void winter(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                board[i][j] += plusBoard[i][j];
            }
        }
    }
    
    public static int solve(){
        while(K-- > 0){
            spring();
            summer();
            fall();
            winter();
        }
        return trees.size();
    }
}