package array_and_string_ch1

// Largest Number At Least Twice of Others
// https://leetcode.com/explore/learn/card/array-and-string/201/introduction-to-array/1147/
// 0ms, 4MB
//
// max를 먼저 찾고, max가 다른 원소의 2배 이상인지 검사하도록 구현
// 연산 수는 2N번에 비례 -> O(N)
func dominantIndex(nums []int) int {
	max := nums[0]
	maxIndex := 0

	for i, num := range nums {
		if num > max {
			max = num
			maxIndex = i
		}
	}

	for i, num := range nums {
		if i == maxIndex {
			continue
		}

		if max < num*2 {
			return -1
		}
	}

	return maxIndex
}
