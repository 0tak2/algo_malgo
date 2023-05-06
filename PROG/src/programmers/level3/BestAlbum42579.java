/*
 * 베스트앨범
 * https://school.programmers.co.kr/learn/courses/30/lessons/42579
 * 
 * 컬렉션 정렬, 스트림 및 람다 사용에 대해서 공부할 필요가 있음을 느꼈다...
 * 지금 내 수준에서 찾아보지 않고 자력으로는 아래의 코드를 작성하기는 어려울 듯 하다.
 * */
package programmers.level3;

import java.util.*;

// 장르를 담을 VO 클래스
class Genre {
    private String name;
    private List<Song> songs;
    private int playSum;
    
    public Genre(String name) {
        this.name = name;
        songs = new ArrayList<Song>();
        playSum = 0;
    }
    
    public void addSong(Song song) {
        this.songs.add(song);
        playSum += song.getPlays();
    }
    
    public String getName() {
        return name;
    }
    
    public List<Song> getSongs() {
        return songs;
    }
    
    public int getPlaySum() {
        return playSum;
    }
}

// 노래 정보를 담을 VO 클래스
class Song {
    private int id;
    private int plays;
    
    public Song(int id, int plays) {
        this.id = id;
        this.plays = plays;
    }
    
    public int getId() {
        return id;
    }
    
    public int getPlays() {
        return plays;
    }
}

public class BestAlbum42579 {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>(genres.length);
        
        // 주어지는 정보를 Genre VO에 묶고, 장르 명을 키로 하여 Map에 넣는다.
        Map<String, Genre> data = new HashMap<>();
        
        for (int i=0; i<genres.length; i++) {
            String genreName = genres[i];
            int playCnt = plays[i];
            
            Genre g = data.get(genreName);
            if (g == null) {
                Genre newGenre = new Genre(genreName);
                data.put(genreName, newGenre);
                g = newGenre;
            }
            
            g.addSong(new Song(i, playCnt));
            // addSong 메서드는 Genre VO 내부 List<Song> 필드에 Song을 추가하고 재생 수를 함께 합산한다.
        }
        
        // 우선 각 Genre를 누적 재생수를 기준으로 내림차 정렬한다.
        List<Map.Entry<String, Genre>> gList = new LinkedList<>(data.entrySet());
        gList.sort(new Comparator<Map.Entry<String, Genre>>() {
            @Override
            public int compare(Map.Entry<String, Genre> o1, Map.Entry<String, Genre> o2) {
                return o2.getValue().getPlaySum() - o1.getValue().getPlaySum();
                // o1을 먼저 쓰고 o2를 뒤에 쓰면 오름차 정렬된다.
            }
        });
        
        // 정렬된 장르 리스트를 순회하며 Song을 재생 수에 따라 오름차 정렬한다.
        for (Map.Entry<String, Genre> g : gList) {
            List<Song> songs = g.getValue().getSongs();
            songs.sort(new Comparator<Song>() {
                @Override
                public int compare(Song s1, Song s2) {
                    return s2.getPlays() - s1.getPlays();
                }
            });
            
            // 정렬된 Song을 순회하며 오름차순으로 정답 리스트에 추가한다.
            for (int i=0; i<songs.size(); i++) {
                if (i>1) break;
                Song song = songs.get(i);
                // System.out.println(song.getId());
                // System.out.println(song.getPlays());
                // System.out.println();
                answer.add(song.getId());
            }
        }
        
        // 리스트를 배열로 변환하여 리턴한다.
        int[] ansArr = new int[answer.size()];
        for (int i=0; i<answer.size(); i++) {
            ansArr[i] = answer.get(i);
        }
        return ansArr;
    }
}

/**
테스트 1 〉	통과 (1.12ms, 72.8MB)
테스트 2 〉	통과 (1.15ms, 88.6MB)
테스트 3 〉	통과 (1.33ms, 74.2MB)
테스트 4 〉	통과 (1.13ms, 73.6MB)
테스트 5 〉	통과 (1.39ms, 67.3MB)
테스트 6 〉	통과 (1.99ms, 83.9MB)
테스트 7 〉	통과 (1.48ms, 83.2MB)
테스트 8 〉	통과 (1.36ms, 75.8MB)
테스트 9 〉	통과 (1.71ms, 78.6MB)
테스트 10 〉	통과 (1.32ms, 69.9MB)
테스트 11 〉	통과 (1.56ms, 75.2MB)
테스트 12 〉	통과 (1.73ms, 75.5MB)
테스트 13 〉	통과 (1.72ms, 73.4MB)
테스트 14 〉	통과 (1.73ms, 80.6MB)
테스트 15 〉	통과 (1.13ms, 73.2MB)
*/