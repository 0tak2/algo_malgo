package programmers.level1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution178871Slow {
	public int findIdx(List<String> ranking, String target) {
//		System.out.println("START: (TARGET: " + target);
//		System.out.println(ranking + "\n");
		
		for (int i=0; i<ranking.size(); i++) {
			String player = ranking.get(i);
//			System.out.println("player: " + player);
//			System.out.println("target: " + target);
			if (player.equals(target)) {
//				System.out.println("FOUND\n");
				return i;
			}
		}
		
		return -1;
	}
	
    public String[] solution(String[] players, String[] callings) {
    	List<String> ranking = new LinkedList<>();
    	
        Arrays.asList(players)
        	.stream()
        	.forEach(p -> ranking.add(p));
        
        Arrays.asList(callings)
        	.stream()
        	.forEach(c -> {
        		int beforeRank = findIdx(ranking, c);
        		ranking.add(beforeRank-1, c);
        		ranking.remove(beforeRank+1);
        	});
        
        return ranking.toArray(new String[0]);
    }
}

class Solution178871 {
	public String[] solution(String[] players, String[] callings) {
    	List<String> rankingSeq = new LinkedList<>();
    	Map<String, Integer> rankingTable = new HashMap<>();
    	
        for (int i=0; i<players.length; i++) {
        	String p = players[i];
        	
        	rankingSeq.add(p);
        	rankingTable.put(p, i);
        }
        
        Arrays.asList(callings)
        	.stream()
        	.forEach(c -> {
        		int beforeRank = rankingTable.get(c);
        		String playerA = rankingSeq.get(beforeRank-1);
        		String playerB = rankingSeq.get(beforeRank);
        		
        		rankingSeq.set(beforeRank-1, playerB);
        		rankingSeq.set(beforeRank, playerA);
        		
        		rankingTable.put(playerA, beforeRank);
        		rankingTable.put(playerB, beforeRank-1);
        	});
        
        return rankingSeq.toArray(new String[0]);
	}
}

public class Running178871 {
	public static void main(String[] args) {
		Solution178871 solution = new Solution178871();
		solution.solution(new String[] {"mumu", "soe", "poe", "kai", "mine"}, 
				new String[] {"kai", "kai", "mine", "mine"});
	}
}
