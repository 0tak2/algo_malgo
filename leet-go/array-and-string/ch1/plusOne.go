package array_and_string_ch1

// Plus One
// https://leetcode.com/explore/learn/card/array-and-string/201/introduction-to-array/1148/
//
// 올림 과정을 구현
//
// 0ms, 3.8MB
func plusOne(digits []int) []int {
	if digits[len(digits)-1]+1 < 10 { // (a) -> rebundant checking
		digits[len(digits)-1]++
		return digits
	}

	if len(digits) == 1 {
		return []int{1, 0}
	}

	digits[len(digits)-1] = 0
	for i := len(digits) - 2; i >= 0; i-- {
		if digits[i]+1 < 10 { // (b)
			digits[i]++
			break
		}

		digits[i] = 0

		if i-1 < 0 { // 앞 자리가 모자라면 append 하자는 아이디어
			// https://stackoverflow.com/a/53737602
			digits = append([]int{1}, digits...)
		}
	}

	return digits
}

// 샘플 풀이
func plusOne_sample(digits []int) []int {

	n := len(digits)
	for i := n - 1; i >= 0; i-- {
		if digits[i] < 9 { // 나의 풀이의 (a)와 (b)를 루프 안에서 같이 수행
			digits[i]++
			return digits // 더 이상 digits를 수정할 필요가 없음. 바로 리턴
		}
		digits[i] = 0

	}
	digits = append([]int{1}, digits...) // 여기까지 도달한 경우 1을 올려준다
	return digits
}
