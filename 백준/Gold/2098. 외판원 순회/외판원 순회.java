import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[][] board;
    private static int[][] cache;
    private static int full = 0;
    private static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        cache = new int[N][(int)Math.pow(2, N)];
        for (int i = 0; i < N; i++) {
            Arrays.fill(cache[i], -1);
        }
        full = (1 << N) - 1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solve(0, 1));
    }

    private static int solve(int here, int set) {
        if (set == full){
            if(board[here][0] != 0)
                return board[here][0];
            return INF;
        }

        if(cache[here][set] != -1) return cache[here][set];
        int ret = INF;

        for (int next = 0; next < N; next++) {
            if(board[here][next] == 0) continue;
            if((set & (1 << next)) == 0) { //방문하지 않았으면
                ret = Math.min(ret, board[here][next] + solve(next, set | (1 << next)));
            }
        }
        cache[here][set] = ret;
        return ret;
    }
}
