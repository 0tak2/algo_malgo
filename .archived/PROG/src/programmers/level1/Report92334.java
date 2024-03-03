/** 신고 결과 받기
 * https://school.programmers.co.kr/learn/courses/30/lessons/92334
 * 
 * 단순 구현 문제임에도 고수들은 다르게 한다. 아직 내공이 부족하다...
 */
package programmers.level1;

import java.util.*;

class Solution92334 {
	public int[] solution(String[] id_list, String[] report, int k) {
		int[] answer = new int[id_list.length];

		// 1. 데이터 준비
		// 신고 히스토리 데이터
		// 키: 피신고자
		// 값: 신고자 리스트
		// -> 신고자 리스트의 길이가 신고 횟수가 됨
		Map<String, List<String>> data = new HashMap<>();
		for (String member : id_list) {
			data.put(member, new ArrayList<String>());
		}

		// 신고 히스토리 인버스 데이터
		// 키: 신고자
		// 값: 피신고자 리스트
		Map<String, List<String>> ivData = new HashMap<>();
		for (String member : id_list) {
			ivData.put(member, new ArrayList<String>());
		}

		// 정치 처분 회원 리스트
		List<String> confirmed = new ArrayList<>();

		// 2. 신고 접수
		for (String r : report) {
			String[] parsedStr = r.split(" ");
			String from = parsedStr[0];
			String to = parsedStr[1];

			// 신고 기록을 불러옴
			List<String> reportHistory = data.get(to);
			boolean prevReported = reportHistory.contains(from); // 기신고여부 판단
			if (!prevReported) { // 신고 전적 없으면 추가
				reportHistory.add(from);
				List<String> ivReportHistroy = ivData.get(from);
				ivReportHistroy.add(to);
			}
			// 공부한 것: Stream의 distinct나 Set을 쓰면 중복 검사 로직이 필요 없다
			// stream 공부하기...
		}

		// 3. 신고 처분
		for (Map.Entry<String, List<String>> entry : data.entrySet()) {
			String beReported = entry.getKey();
			List<String> reports = entry.getValue();
			if (reports.size() >= k) { // k회 이상 신고당한 경우
				confirmed.add(beReported);
			}
		}

		// 4. 신고 통보
		for (Map.Entry<String, List<String>> entry : ivData.entrySet()) {
			String reporting = entry.getKey();
			List<String> reports = entry.getValue();
			int totalMail = 0;

			for (String reported : reports) {
				if (confirmed.contains(reported))
					totalMail++;
			}

			int memberNo = -1; // 공부한 것: LinkedHashMap을 쓰면 put 순서대로 값이 저장되어 이 절차가 필요 없다
			for (int i = 0; i < id_list.length; i++) {
				if (id_list[i].equals(reporting))
					memberNo = i;
			}
			answer[memberNo] = totalMail;
		}

		return answer;
	}
}

public class Report92334 {

	public static void main(String[] args) {
		Solution92334 solution = new Solution92334();
		int[] case1 = solution.solution(new String[] { "muzi", "frodo", "apeach", "neo" },
				new String[] { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi" }, 2);
		System.out.println(Arrays.toString(case1));

		int[] case2 = solution.solution(new String[] { "con", "ryan" },
				new String[] { "ryan con", "ryan con", "ryan con", "ryan con" }, 2);
		System.out.println(Arrays.toString(case2));
	}
}
