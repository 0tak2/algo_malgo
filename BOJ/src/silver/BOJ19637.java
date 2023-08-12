/**
 * https://www.acmicpc.net/problem/19637
 * - 주어진 시간 조건을 보고, 순차 탐색으로 가능한지 가늠해본 후 코드를 짤 것
 * - 시간 조건이 빡빡한데 출력이 잦은 경우 StringBuilder를 통해 출력을 먼저 만든 후
 *   한 번에 출력하는 방법을 생각해볼 것.
 */

package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Nick {
	int up;
	String nickname;
	
	Nick (String nickname, int up) {
		this.nickname = nickname;
		this.up = up;
	}
}

public class BOJ19637 {
	static int binarySearch(int targetVal, List<Nick> src) {
		int left = 0;
		int right = src.size()-1;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			Nick cmpNick = src.get(mid);
			
			if (targetVal <= cmpNick.up) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		
		return right + 1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] input = br.readLine().split(" ");
		int numOfNick = Integer.parseInt(input[0]);
		int numOfChar = Integer.parseInt(input[1]);
		
		List<Nick> nickList = new ArrayList<>();
		
		for (int i=0; i<numOfNick; i++) {
			input = br.readLine().split(" ");
			
			nickList.add(new Nick(input[0], Integer.parseInt(input[1])));
		}
		
		
		// 순차탐색은 시간이 너무 오래 걸린다.
//		for (int i=0; i<numOfChar; i++) {
//			int power = Integer.parseInt(br.readLine());
//			
//			for (int j=0; j<nickList.size(); j++) {
//				Nick prevNick = null;
//				
//				if (j == 0) {
//					prevNick = new Nick("", 0);
//				} else {
//					prevNick = nickList.get(j-1);
//				}
//				Nick currentNick = nickList.get(j);
//				
//				if (power > prevNick.up && power <= currentNick.up) {
//					System.out.println(currentNick.nickname);
//					break;
//				}
//			}
//		}
		
		for (int i=0; i<numOfChar; i++) {
			int power = Integer.parseInt(br.readLine());
			
			// 바로 바로 출력하는 것도 시간이 너무 오래 걸린다.
//			System.out.println(nickList.get(binarySearch(power, nickList)).nickname);
			
			sb.append(nickList.get(binarySearch(power, nickList)).nickname + "\n");
		}
		
		System.out.println(sb.toString().trim()); // 마지막 개행문자 제거
		br.close();
	}
}
