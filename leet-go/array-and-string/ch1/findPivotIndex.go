package array_and_string_ch1

// Find Pivot Index
// https://leetcode.com/explore/learn/card/array-and-string/201/introduction-to-array/1144/
// 0ms, 8.4MB
//
// 특정 원소가 피벗일 떄의 왼쪽 합계와 오른쪽 합계를 계산해두고
// 두 값이 같은 경우의 인덱스를 리턴
// 연산 수는 3N번에 비례 -> O(N)
func pivotIndex(nums []int) int {
	// sumL[i] = pivot이 i일 때 왼쪽 합계
	sumL := make([]int, len(nums))
	// sumR[i] = pivot이 i일 때 오른쪽 합계
	sumR := make([]int, len(nums))

	// 예시
	// [1, 7, 3, 6, 5, 6]
	// 0   1  8 11 17 22
	// 27 20 17 11  6  0

	// [2, 1, -1]
	// 1 2 3
	// 0 2 3
	// 0 -1 0

	// make sumL
	for i, _ := range nums {
		if i != 0 {
			sumL[i] = sumL[i-1] + nums[i-1]
		} else {
			sumL[i] = 0
		}
	}

	// fmt.Println(sumL)

	// make sumR
	for i := len(nums) - 1; i >= 0; i-- {
		if i != len(nums)-1 {
			sumR[i] = sumR[i+1] + nums[i+1]
		} else {
			sumR[i] = 0
		}

		// if sumL[i] == sumR[i] { // 처음에는 sumR을 만들면서 비교하려고 했지만, 가능한 피벗 위치가
		// 	fmt.Println(sumR)      // 두 개 이상이라면 leftmost 값을 출력하라는 것이 조건이므로
		// 	return i               // 다음 단계에서 다시 판단해야 한다
		// }                       // 반례: [-1, -1, 0, 0, -1, -1]
	}

	// get result
	for i := 0; i < len(nums); i++ {
		if sumL[i] == sumR[i] {
			// fmt.Println(sumR)
			return i
		}
	}

	return -1
}
