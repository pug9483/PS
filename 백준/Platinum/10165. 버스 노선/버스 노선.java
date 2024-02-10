/* 내 해설 -> 그리디, 정렬와 통해 해결
 * 1. start와 end의 비교
 * 1_1. start < end: [start, end] 저장
 * 1_2. start > end: [start-N, end], [start, end+N] 저장
 *
 * 2. 정렬
 * 2_1. start 오름차순
 * 2_2. start가 같은 경우 end 내림차순
 *
 * 3. 그리디
 * 3_1. start오름차순으로 정렬했기 때문에 i번째 노선 이후(j라고 하자)에 나오는 노선들은 모두 i.start <= j.start이다.
 *      i.start <= j.start && i.end <= j.end인 것들은 모두 i번째 노선에 포함되는 것이므로 답에 포함시키지 않는다.
 * 3_2. 1_2에서 start > end인 경우 범위를 2개로 만들어서 저장하였다. 이 방법을 사용할 경우, 포함되는 노선들은 모두 범위에 들어가기 때문에 삭제된다.
 *      하지만 문제가 존재한다. 
 *      N = 10이고 [1, 9, 1], [2, 8, 2]라는 노선이 있다고 가정해보자. 범위를 2개씩 만들었기 때문에 [1, -1, 1], [1, 9, 11], [2, -2, 2], [2, 8, 12]가 만들어져 저장되게 된다.
 *      원래에 비해 노선이 2배씩 늘어났기 때문에 원래는 존재하지 않았던 노선이 있다고 되어버려 같은 id가 중복해서 저장되는 문제가 발생한다.
 *      위의 경우에는 2가 총 2번 저장되게 된다. 원래 중복되는 것들이 2개씩 복사되어 저장되는 것이기 때문에 문제의 답이 달라지진 않는다.(1은 똑같이 2에 포함된다)
 *		따라서, 중복을 제거하여 해답을 구해주어야 한다.
 */

import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int id, start, end;
        public Node(int id, int start, int end){
            this.id = id;
            this.start = start;
            this.end = end;
        }
    }
    
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static int N; 
	public static int M; 
    public static List<Node> list = new ArrayList<>();
    public static List<Integer> ret = new ArrayList<>();
    
	public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if(start < end)
                list.add(new Node(i+1, start, end));
            else{
                list.add(new Node(i+1, start, end + N));
                list.add(new Node(i+1, start - N, end));
            }
        }
        list.sort((o1, o2) -> {
            int cmp = Integer.compare(o1.start, o2.start);
            return cmp == 0? Integer.compare(o2.end, o1.end) : cmp;
        });
        
        for(int i = 0; i < list.size(); i++){
            Node here = list.get(i);
            ret.add(here.id);
            int j = i+1;
            while(j < list.size()){
                Node next = list.get(j);
                if(!(here.start <= next.start && here.end >= next.end)) break;
                j++;
            }
            i = j-1;
        }
        ret.stream().sorted().distinct().forEach(s -> System.out.printf("%d ", s));
    }
}
