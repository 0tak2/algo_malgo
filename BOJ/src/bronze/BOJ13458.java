package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BOJ13458 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int numOfClass = Integer.parseInt(br.readLine());
		
		String[] numOfStdStrArr = br.readLine().split(" ");
		List<Integer> numOfStdList = Arrays.stream(numOfStdStrArr)
			.map(i -> Integer.parseInt(i))
			.collect(Collectors.toList());
		
		String[] supervisorInput = br.readLine().split(" ");
		int specialSupervisorStat = Integer.parseInt(supervisorInput[0]);
		int supervisorStat = Integer.parseInt(supervisorInput[1]);
		
		long total = numOfClass; // 시험장마다 총감독관은 1명씩 꼭 필요하므로 시험장 수로 초기화
		
		for (int numOfStd : numOfStdList) {
			// 총감독관이 감시할 수 있는 수보다 학생 수가 적어 음수가 되는 상황을 방지
			int numOfLeftStudent = numOfStd > specialSupervisorStat ? numOfStd - specialSupervisorStat : 0;
			
			int base = numOfLeftStudent / supervisorStat;
			int remain = numOfLeftStudent % supervisorStat > 0 ? 1 : 0;
			total += base + remain;
		}
		
		System.out.println(total);
		
		br.close();
	}
}
