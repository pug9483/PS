import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Shark implements Comparable<Shark>{
        public int y; // [1,R]
        public int x; // [1,C]
        public int v; // [1,1000]
        public int dir; // 1: 위, 2: 아래, 3: 오른쪽, 4: 왼쪽
        public int size; // [1,10000]

        public Shark(int y, int x, int v, int dir, int size) {
            this.y = y;
            this.x = x;
            this.v = v;
            this.dir = dir;
            this.size = size;
        }

        public void setShark(int y, int x, int v, int dir, int size){
            this.y = y;
            this.x = x;
            this.v = v;
            this.dir = dir;
            this.size = size;
        }

        @Override
        public int compareTo(Shark o) {
            int comp1 = Integer.compare(x, o.x);
            if(comp1 != 0) return comp1;
            int comp2 = Integer.compare(y, o.y);
            if(comp2 != 0) return comp2;
            return Integer.compare(o.size, size);
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int R; // 가로 길이
    public static int C; // 세로 길이
    public static int M; // 상어의 개수
    public static List<Shark> sharks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            sharks.add(new Shark(y, x, v, dir, size));
        }
        Collections.sort(sharks);
        System.out.println(solve());
    }

    private static int solve() {
        int ret = 0;
        int start = 0;
        while(start < C){
//            printSharks();
            ret += catchShark(start);
            move();
            start++;
        }
        return ret;
    }

    private static void printSharks() {
        for (Shark shark : sharks) {
            System.out.printf("[%d, %d], v = %d, dir = %d, size = %d\n", shark.y + 1, shark.x + 1, shark.v, shark.dir, shark.size);
        }
        System.out.println("------------------------------------------------");
    }

    private static void move() {
        for (Shark shark : sharks) {
            int y = shark.y;
            int x = shark.x;
            int v = shark.v;
            int dir = shark.dir;
            if(v == 0) continue;
            if(dir == 1 || dir == 2){
                v %= 2 * (R-1); // [0, 2R-3]
                while(v-- > 0){
                    if(dir == 1){
                        if(y == 0){
                            dir = 2;
                            y++;
                        }else y--;
                    }
                    else if(dir == 2){
                        if(y == R-1){
                            dir = 1;
                            y--;
                        } else y++;
                    }
                }
            }
            else if(dir == 3 || dir == 4) {
                v %= 2 * (C - 1);
                while(v-- > 0){
                    if(dir == 3){
                        if(x == C-1){
                            dir = 4;
                            x--;
                        }else x++;
                    }
                    else if(dir == 4){
                        if(x == 0){
                            dir = 3;
                            x++;
                        } else x--;
                    }
                }
            }
            shark.setShark(y, x, shark.v, dir, shark.size);
        }
        Collections.sort(sharks);

        int i = 0;
        while(i < sharks.size()){
            Shark shark = sharks.get(i);
            if(i == sharks.size() - 1) break;
            Shark nextShark = sharks.get(i+1);
            if (shark.y == nextShark.y && shark.x == nextShark.x) {
                sharks.remove(i+1);
            }
            else{
                i++;
            }
        }
    }

    private static int catchShark(int start) {
        int ret = 0;
        Iterator<Shark> iter = sharks.iterator();
        while (iter.hasNext()) {
            Shark shark = iter.next();
            if(shark.x == start){
                ret = shark.size;
                iter.remove();
                break;
            }
        }
        return ret;
    }
}
