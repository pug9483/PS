import java.util.*;

class Solution {
    private Map<String, PriorityQueue<String>> graph = new HashMap<>();
    private List<String> route = new ArrayList<>();

    public String[] solution(String[][] tickets) {
        for (String[] ticket : tickets) {
            graph
                .computeIfAbsent(ticket[0], k -> new PriorityQueue<>())
                .offer(ticket[1]);
        }

        dfs("ICN");

        Collections.reverse(route);
        return route.toArray(new String[0]);
    }

    private void dfs(String here) {
        PriorityQueue<String> there = graph.get(here);

        while (there != null && !there.isEmpty()) {
            dfs(there.poll());
        }

        route.add(here);
    }
}