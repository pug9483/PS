import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        int[] D = new int[N];

        // 배열 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        // A와 B의 합, C와 D의 합을 저장할 배열
        int[] sumAB = new int[N * N];
        int[] sumCD = new int[N * N];

        int idx = 0;
        // A와 B의 합 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sumAB[idx++] = A[i] + B[j];
            }
        }

        idx = 0;
        // C와 D의 합 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sumCD[idx++] = C[i] + D[j];
            }
        }

        // sumAB와 sumCD 정렬
        Arrays.sort(sumAB);
        Arrays.sort(sumCD);

        long count = 0;
        int pointerA = 0;
        int pointerB = sumCD.length - 1;

        // 투 포인터 알고리즘
        while (pointerA < sumAB.length && pointerB >= 0) {
            int currentA = sumAB[pointerA];
            int currentB = sumCD[pointerB];
            int sum = currentA + currentB;

            if (sum == 0) {
                long countA = 0;
                long countB = 0;

                // sumAB에서 같은 값의 개수 세기
                while (pointerA < sumAB.length && sumAB[pointerA] == currentA) {
                    countA++;
                    pointerA++;
                }

                // sumCD에서 같은 값의 개수 세기
                while (pointerB >= 0 && sumCD[pointerB] == currentB) {
                    countB++;
                    pointerB--;
                }

                count += countA * countB;
            } else if (sum < 0) {
                pointerA++;
            } else {
                pointerB--;
            }
        }

        // 결과 출력
        System.out.println(count);
    }
}
