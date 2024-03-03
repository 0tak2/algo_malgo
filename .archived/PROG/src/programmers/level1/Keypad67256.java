package programmers.level1;

class Solution67256 {
	private final int[][] keymap = {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9},
			{-1, 0, -3}
	};
	
	private int findRowIdx(int num) {	
		if (num <= 0) return 3;
		
		if (num % 3 == 0) return num / 3 - 1;
		
		return num / 3;
	}
	
	private int findColIdx(int num) {
		if (num < 0) return num * -1 -1;
		
		if (num == 0) return 1;
		
		if (num % 3 == 0) return 2;
		
		return num % 3 - 1;
	}
	
	private int calcLength(int num1, int num2) {
		int x1, y1, x2, y2;
		
		y1 = findRowIdx(num1);
		x1 = findColIdx(num1);
		y2 = findRowIdx(num2);
		x2 = findColIdx(num2);
		
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
	
	private int posToNum(int ypos, int xpos) {
		return keymap[ypos][xpos];
	}
	
    public String solution(int[] numbers, String hand) {
        String answer = "";
        
        int leftHandY = 3;
        int leftHandX = 0;
        int rightHandY = 3;
        int rightHandX = 2;
        
        for (int num : numbers) {
        	int ypos = findRowIdx(num);
        	int xpos = findColIdx(num);
        	
        	if (xpos == 0) {
        		answer += "L";
        		leftHandY = ypos;
        		leftHandX = 0;
        	} else if (xpos == 2) {
        		answer += "R";
        		rightHandY = ypos;
        		rightHandX = 2;
        	} else {       		
        		int leftNum = posToNum(leftHandY, leftHandX);
        		int rightNum = posToNum(rightHandY, rightHandX);
        		
        		int leftDistance = calcLength(leftNum, num);
        		int rightDistance = calcLength(rightNum, num);
        		
        		if (leftDistance < rightDistance) {
            		answer += "L";
            		leftHandY = ypos;
            		leftHandX = xpos;
        		} else if (leftDistance > rightDistance) {
            		answer += "R";
            		rightHandY = ypos;
            		rightHandX = xpos;
        		} else if (leftDistance == rightDistance && hand.equals("left")) {
            		answer += "L";
            		leftHandY = ypos;
            		leftHandX = xpos;
        		} else if (leftDistance == rightDistance && hand.equals("right")) {
            		answer += "R";
            		rightHandY = ypos;
            		rightHandX = xpos;
        		}
        	}
        }
        
        return answer;
    }
}

public class Keypad67256 {
	public static void main(String[] args) {
		Solution67256 solution = new Solution67256();
		String result1 = solution.solution(new int[] {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5},
				"right");
		System.out.println(result1);
		assert result1.equals("LRLLLRLLRRL");
		
		String result2 = solution.solution(new int[] {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2},
				"left");
		assert result2.equals("LRLLRRLLLRR");
		
		String result3 = solution.solution(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0},
				"right");
		assert result3.equals("LLRLLRLLRL");
	}
}
