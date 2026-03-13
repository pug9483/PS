import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genreTotalPlays = new HashMap<>();
        Map<String, List<Song>> genreSongs = new HashMap<>();
        
        for(int i = 0; i < genres.length; i++){
            String genre = genres[i];
            int play = plays[i];
            
            genreTotalPlays.put(genre, genreTotalPlays.getOrDefault(genre, 0) + play);

            genreSongs.putIfAbsent(genre, new ArrayList<>());
            genreSongs.get(genre).add(new Song(i, play));
        }
        
        List<String> sortedGenres = new ArrayList<>(genreTotalPlays.keySet());
        sortedGenres.sort((a, b) -> Integer.compare(genreTotalPlays.get(b), genreTotalPlays.get(a)));

        List<Integer> answerList = new ArrayList<>();

        for (String genre : sortedGenres) {
            List<Song> songs = genreSongs.get(genre);
            Collections.sort(songs, (o1, o2) -> Integer.compare(o2.play, o1.play));

            answerList.add(songs.get(0).id);
            if (songs.size() > 1) {
                answerList.add(songs.get(1).id);
            }
        }

        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
        
        
    static class Song {
        int id;
        int play;
        
        public Song(int id, int play){
            this.id = id;
            this.play = play;
        }
    }
}