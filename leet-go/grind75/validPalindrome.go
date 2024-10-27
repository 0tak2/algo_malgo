package grind75

// Valid Palindrome
// Grind75 #5
//
// https://leetcode.com/problems/valid-palindrome/
//
// 문제 조건을 그대로 구현했다
//  1. rune을 담는 슬라이스 `runes`를 만들고, 소문자 알파벳과 숫자는 그대로,
//     대문자 알바파벳은 소문자로 바꾸어 runes에 추가
//  2. runes를 순회하면서 i번 원소와 length-1-i번 원소가 모두 같은지 검증
//
// 0ms, 4,98MB
func isPalindrome(s string) bool {
	runes := make([]rune, len(s))
	length := 0

	const DIFF rune = 'a' - 'A'

	for _, c := range s {
		if (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') {
			runes[length] = c
			length++
			continue
		}

		if c >= 'A' && c <= 'Z' {
			runes[length] = c + DIFF
			length++
			continue
		}
	}

	// fmt.Println(runes)

	for i := 0; i < length; i++ {
		// fmt.Println(i)
		// fmt.Println(length - 1 - i)
		// fmt.Println()
		if runes[i] != runes[length-1-i] {
			return false
		}
	}

	return true
}
