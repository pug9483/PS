import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int num;
        char color;

        public Node() {}

        public Node(int num, char color) {
            this.num = num;
            this.color = color;
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int[] dy = {0, 0, 1, 1};
    public static int[] dx = {0, 1, 0, 1};
    public static Node[][] board = new Node[5][5];
    public static List<Node[][]> ingredientsList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            Node[][] ingredients = new Node[4][4];
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    ingredients[j][k] = new Node();
                }
            }
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    ingredients[j][k].num = Integer.parseInt(st.nextToken());
                }
            }
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    ingredients[j][k].color = st.nextToken().charAt(0);
                }
            }
            ingredientsList.add(ingredients);
        }

        List<Node[][]> list = new ArrayList<>();
        boolean[] visited = new boolean[N];
        System.out.println(solve(list, visited));
    }

    private static int solve(List<Node[][]> list, boolean[] visited) {
        if (list.size() == 3) {
            int[] types = new int[3];
            return solveUtil(0, types, list);
        }

        int ret = 0;
        for (int here = 0; here < N; here++) {
            if(visited[here]) continue;
            visited[here] = true;
            list.add(copy(ingredientsList.get(here)));
            ret = Math.max(ret, solve(list, visited));
            list.remove(list.size() - 1);
            visited[here] = false;
        }
        return ret;
    }

    public static Node[][] copy(Node[][] srcNodes) {
        Node[][] dstNodes = new Node[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                dstNodes[i][j] = new Node(srcNodes[i][j].num, srcNodes[i][j].color);
            }
        }
        return dstNodes;
    }

    private static int solveUtil(int here, int[] types, List<Node[][]> list) {
        if (here == 3) {
            return getScore(types, list);
        }

        int ret = 0;
        for (int rotate = 0; rotate < 4; rotate++) {
            rotateNodes(here, list);
            for (int type = 0; type < 4; type++) {
                types[here] = type;
                ret = Math.max(ret, solveUtil(here + 1, types, list));
            }
        }
        return ret;
    }

    private static void rotateNodes(int here, List<Node[][]> list) {
        Node[][] nodes = list.get(here);
        Node[][] tmp = new Node[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tmp[i][j] = nodes[4 - j - 1][i];
            }
        }
        list.set(here, tmp);
    }

    private static int getScore(int[] types, List<Node[][]> list) {
        init();
        for (int i = 0; i < 3; i++) {
            int type = types[i];
            Node[][] nodes = list.get(i);
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    int num = nodes[y][x].num;
                    char color = nodes[y][x].color;
                    int ny = y + dy[type];
                    int nx = x + dx[type];
                    int newNum = num + board[ny][nx].num;
                    board[ny][nx].num = Math.min(newNum, 9);
                    board[ny][nx].num = Math.max(board[ny][nx].num, 0);
                    if(color != 'W') board[ny][nx].color = color;
                }
            }
        }

        int r = 0;
        int b = 0;
        int g = 0;
        int y = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j].color == 'R') r += board[i][j].num;
                else if(board[i][j].color == 'B') b += board[i][j].num;
                else if(board[i][j].color == 'G') g += board[i][j].num;
                else if(board[i][j].color == 'Y') y += board[i][j].num;
            }
        }
        return 7*r + 5*b + 3*g + 2*y;
    }

    private static void init() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = new Node(0, 'W');
            }
        }
    }
}