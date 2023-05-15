package programmers.level1;

class Solution133499 {
	public int solution(String[] babbling) {
		int answer = 0;

		String[] words = { "aya", "ye", "woo", "ma" };
		String[] prevents = { "ayaaya", "yeye", "woowoo", "mama" };

		Outer: for (String s : babbling) {
			for (String p : prevents) {
				if (s.contains(p))
					continue Outer;
			}

			for (String w : words) {
				s = s.replaceAll(w, "Z"); // 공백으로 바꾸면 다음과 같은 반례 발생: "mayaa" -> "ma" -> 발음가능
			}

			s = s.replaceAll("Z", "");
			if (s.equals(""))
				answer++;
		}

		return answer;
	}
}

public class OngalTwo133499 {

}
