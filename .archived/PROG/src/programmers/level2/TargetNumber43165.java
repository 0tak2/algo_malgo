/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/43165
 * 타켓넘버
 * 재귀함수, 재귀, DFS
 */
package programmers.level2;

class Solution43165 {
	int[] numbers;
	int target;
	int answer = 0;
	
	void dfs(int index, int sum) {
		if (index > numbers.length-1) {
			if (sum == target) answer++;
			return;
		}
		
		dfs(index+1, sum + numbers[index]); // +, - 두 갈래로 뻗혀짐.
		dfs(index+1, sum - numbers[index]);
	}
	
    public int solution(int[] numbers, int target) {
    	this.numbers = numbers;
    	this.target = target;
    	
    	dfs(0, 0);
    	
        return answer;
    }
}

public class TargetNumber43165 {

}
