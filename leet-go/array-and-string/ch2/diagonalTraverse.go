package array_and_string_ch2

// Diagonal Traverse
// https://leetcode.com/explore/learn/card/array-and-string/202/introduction-to-2d-array/1167/
//
// 대각선으로 왔다갔다 하는 동작을 직접 구현
// O(N)
//
// 0ms, 8.2MB
func findDiagonalOrder(mat [][]int) []int {
	result := []int{}

	// 시작점 0, 0
	posY := 0
	posX := 0
	directions := [][]int{{-1, +1}, {+1, -1}}
	nextDirection := 0 // 0 -> 대각선 위, 1 -> 대각선 아래

	// 끝 점에 도달할 떄 까지 반복
	for posY != len(mat)-1 && posX != len(mat[0])-1 {
		result = append(result, mat[posY][posX])

		if posY+directions[nextDirection][0] < 0 || posY+directions[nextDirection][0] > len(mat)-1 ||
			posX+directions[nextDirection][1] < 0 || posX+directions[nextDirection][1] > len(mat[0])-1 {
			// 우상으로 진행하는 경우 수평 재할당이 우선
			if nextDirection == 0 {
				if posX+1 >= 0 && posX+1 < len(mat[0]) {
					posX = posX + 1
				} else {
					posY = posY + 1
				}

				nextDirection = 1

				continue
			}

			// 좌하로 진행하는 경우 수직 재할당이 우선
			if nextDirection == 1 {
				if posY+1 >= 0 && posY+1 < len(mat) {
					posY = posY + 1
				} else {
					posX = posX + 1
				}

				nextDirection = 0

				continue
			}
		}

		posY = posY + directions[nextDirection][0]
		posX = posX + directions[nextDirection][1]
	}
	result = append(result, mat[posY][posX])

	return result
}

// Example #1
// [[1,2,3],[4,5,6],[7,8,9]]
// [1,2,4,7,5,3,6,8,9]
//
// (0, 0)
// (0, 1) (1, 0)
// (2, 0) (1, 1) (0, 2)
// (1, 2) (2, 1)
// (2, 2)

// Example #2
// [[1,2],[3,4]]
// [1,2,3,4]
//
// (0, 0)
// (0, 1) (1, 0)
// (2, 0)

// Example #3
//  1   2   3   4
//  5   6   7   8
//  9  10  11  12
// 13  14  15  16
//
// [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]]
// [1,2,4,7,5,3,6,8,9]
//
// (0, 0)
// (0, 1) (1, 0)
// (2, 0) (1, 1) (0, 2)
// (0, 3) (1, 2) (2, 1) (3, 0)
// (3, 1) (2, 2) (1, 3)
// (2, 3) (3, 2)
// (3, 3)
