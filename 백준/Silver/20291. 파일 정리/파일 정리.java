import java.io.*;
import java.util.*;

public class Main {
    static class File{
        String name;
        String ext;

        public File(String name, String ext) {
            this.name = name;
            this.ext = ext;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static Map<String, List<String>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            File file =  splitFile(br.readLine());
            if(map.containsKey(file.ext)){
                map.get(file.ext).add(file.name);
            }
            else{
                List<String> files = new ArrayList<>();
                files.add(file.name);
                map.put(file.ext, files);
            }
        }

        StringBuilder sb = new StringBuilder();
        map.keySet().stream()
                .sorted()
                .forEach(o -> sb.append(o).append(" ").append(map.get(o).size()).append("\n"));
        System.out.println(sb);
    }

    private static File splitFile(String file) {
        String[] split = file.split("\\.", -1);
        return new File(split[0], split[1]);
    }
}