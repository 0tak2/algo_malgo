package programmers.level1;

class Solution134240 {
    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder();
        
        for (int i=1; i<food.length; i++) {
        	for (int j=0; j<food[i]/2; j++) {
        		sb.append(Integer.toString(i));
        	}
        }
        
        StringBuilder sbReversed = new StringBuilder(sb.toString());
        sbReversed.reverse();
        
        return sb.append("0").append(sbReversed).toString();
    }
}

public class FoodFight134240 {

}
