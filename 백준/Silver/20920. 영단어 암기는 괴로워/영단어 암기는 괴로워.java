import java.io.*;
import java.util.*;

public class Main {
    static class Word{
        String name;
        int count;

        public Word(String name, int count) {
            this.name = name;
            this.count = count;
        }

        @Override
        public String toString() {
            return name;
        }
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static Map<String, Integer> map = new HashMap<>();
    public static List<Word> words = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            if (s.length() < M) continue;
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        solve();
    }

    private static void solve() {
        StringBuilder sb = new StringBuilder();
        for (String name : map.keySet()) {
            words.add(new Word(name, map.get(name)));
        }
        Collections.sort(words, ((o1, o2) -> {
            int cmp = Integer.compare(o2.count, o1.count);
            if(cmp != 0) return cmp;
            cmp = Integer.compare(o2.name.length(), o1.name.length());
            if(cmp != 0) return cmp;
            return o1.name.compareTo(o2.name);
        }));
        for (Word word : words) {
            sb.append(word).append("\n");
        }
        System.out.print(sb);
    }
}