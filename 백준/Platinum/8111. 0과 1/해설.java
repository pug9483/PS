/* 해설 및 코드
1. N의 배수를 구하는 것이기 때문에 실제로 그 수가 무엇인지 아는 것 보다는 그 수를 N으로 나눈 나머지가 몇 인지 아는 것이 중요하다.
2. N으로 나눈 나머지는 총 N개 존재한다.
3. parent[y] = x; // y의 부모인 x 저장
4. how[num] = [0,1] // 나머지가 num인 것을 0으로 만들었는지 1로 만들었는지 저장
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            solve();
        }
    }

    public static void solve() {
        int[] parent = new int[N];
        int[] how = new int[N];
        int[] dist = new int[N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(parent, -1);
            Arrays.fill(how, -1);
            Arrays.fill(dist, -1);
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(1 % N);
        dist[1 % N] = 0;
        how[1 % N] = 1;
        while (!q.isEmpty()) {
            int here = q.poll();
            for (int num = 0; num <= 1; num++) {
                int next = (here * 10 + num) % N;
                if(dist[next] == -1){
                    dist[next] = dist[here] + 1;
                    parent[next] = here;
                    how[next] = num;
                    q.add(next);
                }
            }
        }
        if(dist[0] == -1) System.out.println("BRAK");
        else{
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i != -1; i = parent[i]) {
                sb.append(how[i]);
            }
            System.out.println(sb.reverse());
        }
    }
}
