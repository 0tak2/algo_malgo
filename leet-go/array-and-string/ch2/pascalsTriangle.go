package array_and_string_ch2

// Pascal's Triangle
// https://leetcode.com/explore/learn/card/array-and-string/202/introduction-to-2d-array/1170/
//
// 0행까지는 직접 할당: {1}
// 그 뒤로는, 첫 항과 마지막 항을 1로 할당해두고서, 1부터 마지막 항의 전 항까지 계산
// 규칙 상 현재 항이 j번 항이면, 이전 행의 j-1번 항과 j번 항을 더해서 계산하면 됨
//
// 0ms, 4.3MB
func generate(numRows int) [][]int {
	result := make([][]int, numRows)
	result[0] = []int{1}

	for i := 1; i < numRows; i++ {
		row := make([]int, i+1)
		row[0], row[i] = 1, 1

		for j := 1; j < i; j++ {
			row[j] = result[i-1][j-1] + result[i-1][j]
		}

		result[i] = row
	}

	return result
}
