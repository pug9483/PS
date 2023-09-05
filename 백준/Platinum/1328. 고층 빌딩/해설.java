/*
D[N][L][R]: 왼쪽에서 L개 보이고, 오른쪽에서 R개 보이는 N개의 빌딩을 배치하는 방법의 수

빌딩 [2..N]까지 이미 세워져있고, 높이가 1인 빌딩을 추가한다고 생각해보자.
-> 기존에 지어져있는 빌딩들에 새로운 빌딩을 추가하는 방법의 수는 N개가 있다.
1) 가장 왼쪽에 놓을 경우: N++, L++ => D[N+1][L+1][R] += D[N][L][R]
2) 가장 오른쪽에 놓을 경우: N++, R++ => D[N+1][L][R+1] += D[N][L][R]
3) 가운데에 놓을 경우: 변경 x => D[N+1][L][R] += D[N][L][R] * (N-1)

가장 작은 빌딩부터 빠진다고 가정할 경우
1) 가장 왼쪽에 빌딩1이 있는 경우: L++ => D[N][L][R] += D[N-1][L-1][R]
2) 가장 오른쪽에 빌딩1이 있는 경우: R++ => D[N][L][R] += D[N-1][L][R-1]
3) 가운데에 빌딩1이 있는 경우: D[N][L][R] += D[N-1][L][R] * (N-2)
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int MOD = 1_000_000_007;
    private static long[][][] cache = new long[101][101][101];
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        cache[1][1][1] = 1;

        for (int n = 2; n <= N; n++) {
            for (int l = 1; l <= L; l++) {
                for (int r = 1; r <= R; r++) {
                    cache[n][l][r] = (cache[n][l][r] + cache[n-1][l-1][r]) % MOD;
                    cache[n][l][r] = (cache[n][l][r] + cache[n-1][l][r-1]) % MOD;
                    cache[n][l][r] = (cache[n][l][r] + (cache[n-1][l][r] * (n-2))) % MOD;
                }
            }
        }
        System.out.println(cache[N][L][R]);
    }
}
