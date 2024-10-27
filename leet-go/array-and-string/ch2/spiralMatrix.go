package array_and_string_ch2

// Spiral Matrix
// https://leetcode.com/explore/learn/card/array-and-string/202/introduction-to-2d-array/1168/
//
// 문제 조건 그대로 구현
// 방향을 표현하는 `direction`을 루프마다 더해가면서 다음 진행 좌표가 적절한지 검증 후 좌표 갱신
// 적절한 좌표? 1. 경계 안에 있는 좌표 2. 방문하지 않은 좌표
//
// 0ms, 3.9MB
func spiralOrder(matrix [][]int) []int {
	dy := []int{0, +1, 0, -1}
	dx := []int{+1, 0, -1, 0}

	direction := 0
	visited := make([][]bool, len(matrix))
	for i := range visited {
		visited[i] = make([]bool, len(matrix[0]))
	}
	visited[0][0] = true

	rotate := 0

	py := 0
	px := 0

	result := []int{matrix[py][px]}

	for {
		next := direction % 4

		if py+dy[next] >= 0 && py+dy[next] < len(matrix) &&
			px+dx[next] >= 0 && px+dx[next] < len(matrix[0]) &&
			!visited[py+dy[next]][px+dx[next]] {
			py = py + dy[next]
			px = px + dx[next]
			visited[py][px] = true
			rotate = 0
			result = append(result, matrix[py][px])
			continue
		}

		direction++
		rotate++
		if rotate >= 4 { // 제자리로 돌아왔으면 break
			break
		}
	}

	return result
}
