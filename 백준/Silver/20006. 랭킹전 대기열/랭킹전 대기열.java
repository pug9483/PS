import java.io.*;
import java.util.*;

public class Main {   
    static class Player{
        private String name;
        private int level;
        public Player(String name, int level){
            this.name = name;
            this.level = level;
        }
    }
    
    static class Room{
        private Player master;
        private List<Player> players = new ArrayList<>();
        
        public Room(Player master){
            this.master = master;
            players.add(master);
        }
        public boolean comeIn(Player oPlayer, int criteria){
            int diff = Math.abs(master.level - oPlayer.level);
            return diff <= criteria;
        }
        public void sortPlayer(){
             Collections.sort(players, (o1, o2) -> o1.name.compareTo(o2.name));
        }
        public int getSize(){
            return players.size();
        }
        public void addPlayer(Player oPlayer){
            players.add(oPlayer);
        }
        public boolean isFull(int fullSize){
            return players.size() == fullSize;
        }
        public List<Player> getPlayers(){
            return new ArrayList<>(players);
        }
    }
    
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int N; // 플레이어 수
    public static int M; // 방의 정원
    public static List<Player> A = new ArrayList<>();
    public static List<Room> rooms = new ArrayList<>();
    
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            A.add(new Player(name, level));
        }
        
        for(Player p: A){
            boolean hasCome = false;
            for(Room room: rooms){
                if(room.getSize() < M && room.comeIn(p, 10)){
                    room.addPlayer(p);
                    hasCome = true;
                    break;
                }
            }   
            if(!hasCome) rooms.add(new Room(p));
        }
        
        for(Room room: rooms){
            room.sortPlayer();
            if(room.isFull(M)) sb.append("Started!\n");
            else sb.append("Waiting!\n");
            for(Player p: room.getPlayers())
                sb.append(p.level).append(" ").append(p.name).append("\n");
        }
        System.out.print(sb);
    }
}