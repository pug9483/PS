import java.util.*;

class Solution {
    private Set<Integer> visited;
    
    public int solution(String[] user_id, String[] banned_id) {
        visited = new HashSet<>();
        
        dfs(0, 0, user_id, banned_id);
        
        return visited.size();
    }
    
    private void dfs(int depth, int bitmask, String[] user_id, String[] banned_id){
        if(depth == banned_id.length){
            visited.add(bitmask);
            return;
        }
        
        for(int i = 0; i < user_id.length; i++) {
            if((bitmask & (1 << i)) == 0){
                if(isOk(user_id[i], banned_id[depth])) {
                    dfs(depth + 1, bitmask | (1 << i), user_id, banned_id);
                }
            }
        }
    }
    
    private boolean isOk(String user, String banned) {
        if (user.length() != banned.length()) return false;
        
        for (int i = 0; i < user.length(); i++) {
            if (banned.charAt(i) != '*' && user.charAt(i) != banned.charAt(i)) return false;
        }
        
        return true;
    }
}