import java.io.*;
import java.util.*;

import static java.util.Comparator.*;

public class Main {
    static class Node{
        int left;
        int aNum;
        int bNum;

        public Node(int left, int aNum, int bNum) {
            this.left = left;
            this.aNum = aNum;
            this.bNum = bNum;
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int ACnt;
    public static int BCnt;
    public static List<Node> nodes;

    public static void main(String[] args) throws IOException {
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            ACnt = Integer.parseInt(st.nextToken());
            BCnt = Integer.parseInt(st.nextToken());
            if(N == 0 && ACnt == 0 && BCnt == 0) break;

            nodes = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int left = Integer.parseInt(st.nextToken());
                int aNum = Integer.parseInt(st.nextToken());
                int bNum = Integer.parseInt(st.nextToken());
                Node node = new Node(left, aNum, bNum);
                nodes.add(node);
            }
            System.out.println(solve());
        }
    }

    public static int solve() {
        int ret = 0;
        nodes.sort((o1, o2) -> Integer.compare(Math.abs(o2.aNum - o2.bNum), Math.abs(o1.aNum - o1.bNum)));

        for (Node node : nodes) {
            if (node.aNum < node.bNum) { // a가 더 가까운 경우
                if (ACnt >= node.left) {
                    ret += node.left * node.aNum;
                    ACnt -= node.left;
                }
                else{
                    ret += ACnt * node.aNum;
                    node.left -= ACnt;
                    ACnt = 0;

                    ret += node.left * node.bNum;
                    BCnt -= node.left;
                }
            }
            else{
                if (BCnt >= node.left) {
                    ret += node.left * node.bNum;
                    BCnt -= node.left;
                }
                else{
                    ret += BCnt * node.bNum;
                    node.left -= BCnt;
                    BCnt = 0;

                    ret += node.left * node.aNum;
                    ACnt -= node.left;
                }
            }
        }
        return ret;
    }
}