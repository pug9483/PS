import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String s = br.readLine();
        String[] A = s.split("");
        Arrays.sort(A);
        Collections.reverse(Arrays.asList(A));
        System.out.println(String.join("", A));
    }
}