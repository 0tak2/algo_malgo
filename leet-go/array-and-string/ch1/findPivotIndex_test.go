package array_and_string_ch1

import (
	"fmt"
	"testing"

	"github.com/0tak2/algo_malgo/leet-go/common"
)

func TestFindPivot(t *testing.T) {
	testEntries := []common.TestEntry{
		common.TestEntry{Input: []int{1, 7, 3, 6, 5, 6}, Output: 3},
		common.TestEntry{Input: []int{1, 2, 3}, Output: -1},
		common.TestEntry{Input: []int{2, 1, -1}, Output: 0},
		common.TestEntry{Input: []int{-1, -1, 0, 0, -1, -1}, Output: 2},
	}

	for _, testEntry := range testEntries {
		result := pivotIndex(testEntry.Input.([]int))

		if result == testEntry.Output {
			fmt.Printf("Passed... expected: %v, output: %v\n", testEntry.Output, result)
		} else {
			fmt.Printf("Wrong... expected: %v, output: %v\n", testEntry.Output, result)
			t.Error(fmt.Sprintf("Wrong... expected: %v, output: %v", testEntry.Output, result))
		}
	}
}
