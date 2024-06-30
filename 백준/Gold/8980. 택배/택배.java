import java.io.*;
import java.util.*;

public class Main {
    static class Village{
        public int from;
        public int to;
        public int capacity;

        public Village(int from, int to, int capacity) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int C;
    public static int M;
    public static List<Village> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int capacity = Integer.parseInt(st.nextToken());
            list.add(new Village(from, to, capacity));
        }
        System.out.println(solve());
    }

    private static int solve() {
        int ret = 0;

        list.sort(Comparator.comparingInt((Village o) -> o.to).thenComparingInt(o -> o.from));
        int[] maxWeight = getMaxWeight();

        for (int i = 0; i < M; i++) {
            Village village = list.get(i);
            int maxBox = getMaxBox(village, maxWeight); // 각 마을당 보낼 수 있는 최대 용량 설정
            ret += getDelivery(maxBox, village, maxWeight); // [from, to] 사이에서 보낼 수 있는 택배 양 구하기
        }
        return ret;
    }

    private static int getDelivery(int maxBox, Village village, int[] maxWeight) {
        int ret = 0;
        if (maxBox >= village.capacity) {
            for (int j = village.from; j < village.to; j++) {
                maxWeight[j] -= village.capacity;
            }
            ret += village.capacity;
        }
        else{
            for (int j = village.from; j < village.to; j++) {
                maxWeight[j] -= maxBox;
            }
            ret += maxBox;
        }
        return ret;
    }

    private static int getMaxBox(Village village, int[] maxWeight) {
        int maxBox = Integer.MAX_VALUE;

        for (int j = village.from; j < village.to; j++) {
            maxBox = Math.min(maxBox, maxWeight[j]);
        }
        return maxBox;
    }

    private static int[] getMaxWeight() {
        int[] maxWeight = new int[N];
        for (int i = 0; i < N; i++) {
            maxWeight[i] = C;
        }
        return maxWeight;
    }
}