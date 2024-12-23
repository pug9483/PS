import java.io.*;
import java.util.*;

public class Main {
    static class Person {
        int height, count;

        public Person(int height, int count) {
            this.height = height;
            this.count = count;
        }
    }

    static int N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] A;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(solve());
    }

    private static long solve() {
        long ret = 0;
        Stack<Person> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            Person person = new Person(A[i], 1);

            while (!stack.isEmpty()) {
                if (stack.peek().height <= A[i]) {
                    ret += stack.peek().count;
                    if (stack.peek().height == A[i]) {
                        person.count += stack.peek().count;
                    }
                    stack.pop();
                } else {
                    break;
                }
            }
            if (!stack.isEmpty()) {
                ret++;
            }
            stack.add(person);
        }
        return ret;
    }
}
