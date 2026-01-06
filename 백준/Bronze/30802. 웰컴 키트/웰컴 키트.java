import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int TSHIRT_SIZES = 6;
    public static int N;
    public static int[] A = new int[TSHIRT_SIZES]; // 티셔츠 사이즈 배열
    public static int T;
    public static int P;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        int totalShirts = 0;
        for (int i = 0; i < TSHIRT_SIZES; i++) {
            totalShirts += A[i] % T == 0 ? A[i] / T : A[i] / T + 1;
        }

        int pencilBundles = N / P;
        int pencilsMod = N % P;

        System.out.println(totalShirts);
        System.out.printf("%d %d\n", pencilBundles, pencilsMod);
    }
}
