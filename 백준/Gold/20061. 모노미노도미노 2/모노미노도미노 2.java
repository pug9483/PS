import java.io.*;
import java.util.*;

public class Main {  
    static class Block{
        int y, x;
        public Block(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    /*
    int[] green: green[i] => i번째 행에 있는 타일의 개수
    int[] blue: blue[j] => j번째 열에 있는 타일의 개수
    int[] gTop: gTop[j] => j번째 열의 높이
    int[] bTop : bTop[i] => i번째 행의 높이
    int[] border = [4, 5]
    */
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N = 9;
    public static int M;
    public static int[][] A = new int[N][N];
    public static int ret = 0;
    public static int[][] blue = new int[6][4];
    public static int[][] green = new int[6][4];
    public static int[] bTop = new int[]{5, 5, 5, 5};
    public static int[] gTop = new int[]{5, 5, 5, 5};
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            go(type, y, x);
        }
        System.out.println(ret);
        System.out.println(getTile());
    }
    
    public static int getTile(){
        int ret = 0;
        for(int y = 0; y < 6; y++){
            for(int x = 0; x < 4; x++){
                if(blue[y][x] == 1) ret++;
                if(green[y][x] == 1) ret++; 
            }
        }
        return ret;
    }
    
    public static void go(int type, int sy, int sx){
        List<Block> blocks = new ArrayList<>();
        if(type == 1) blocks.add(new Block(sy, sx));
        else if(type == 2){
            blocks.add(new Block(sy, sx));
            blocks.add(new Block(sy, sx+1));
        }
        else{
            blocks.add(new Block(sy, sx));
            blocks.add(new Block(sy+1, sx));
        }
        goDown(type, blocks, green, gTop);
        blocks = transForm(blocks);
        goDown(type == 1? 1 : 5 - type, blocks, blue, bTop);
        // printBoard(0, green, gTop);
        // printBoard(1, blue, bTop);
    }
    
    // tops[i] : i번째 열에서 타일을 놓을 수 있는 가장 낮은 높이
    public static void goDown(int type, List<Block> blocks, int[][] B, int[] tops){
        int cnt = 0;
        int minHeight = 5;
        for(Block block: blocks){
            int height = tops[block.x];
            minHeight = Math.min(minHeight, height);
        }
        if(type == 1){
            Block block = blocks.get(0);
            B[minHeight][block.x] = 1;
            tops[block.x] = minHeight - 1;
        }
        else if(type == 2){
            Block b1 = blocks.get(0);
            Block b2 = blocks.get(1);
            B[minHeight][b1.x] = 1;
            B[minHeight][b2.x] = 1;
            tops[b1.x] = tops[b2.x] = minHeight - 1;
        }
        else if(type == 3){
            Block b1 = blocks.get(0);
            Block b2 = blocks.get(1);
            B[minHeight][b1.x] = 1;
            B[minHeight-1][b2.x] = 1;
            tops[b1.x] = minHeight - 2;
        }

        
        // 행이 모두 채워지는 경우
        for(int y = 5; y >= 0; y--){
            if(B[y][0] + B[y][1] + B[y][2] + B[y][3] == 4){
                cnt++;
                B[y][0] = B[y][1] = B[y][2] = B[y][3] = 0;
            }
            else if(cnt > 0){
                for(int x = 0; x < 4; x++){
                    if(B[y][x] == 1){
                        B[y][x] = 0;
                        B[y+cnt][x] = 1;                        
                    }
                }
            }
        }

        
        int borderBombCnt = 0;
        for(int y = 0; y < 2; y++){
            if(B[y][0] + B[y][1] + B[y][2] + B[y][3] > 0){
                borderBombCnt++;
            }
        }
        
        if(borderBombCnt > 0){
            for(int y = 6 - borderBombCnt; y < 6; y++){
                for(int x = 0; x < 4; x++)
                    B[y][x] = 0;
            }
            
            for(int y = 4; y >= 0; y--){
                for(int x = 0; x < 4; x++){
                    if(B[y][x] == 1){
                        B[y][x] = 0;
                        B[y+borderBombCnt][x] = 1;
                    }
                }
            }
        }
        
        //높이 갱신
        tops[0] = tops[1] = tops[2] =  tops[3] = 5;
        for(int y = 5; y >= 0; y--){
            for(int x = 0; x < 4; x++){
                if(B[y][x] == 1){
                    tops[x] = y-1;
                }
            }
        }
        ret += cnt;
    }
    
    public static List<Block> transForm(List<Block> blocks){
        List<Block> list = new ArrayList<>();
        for(Block block : blocks){
            int ny = block.x;
            int nx = block.y;
            list.add(new Block(ny, nx));
        }
        return list;
    }
    
    public static void printBoard(int type, int[][] B, int[] top){
        System.out.println();
        System.out.println(type == 0? "green" : "blue");
        for(int y = 0; y < 6; y++){
            for(int x = 0; x < 4; x++){
                System.out.print(B[y][x] + " ");
            }
            System.out.println();
        }
        
        System.out.println(Arrays.toString(top));
        System.out.println("----------------------------------");
    }
}