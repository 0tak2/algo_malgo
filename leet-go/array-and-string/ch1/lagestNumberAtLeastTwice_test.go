package array_and_string_ch1

import (
	"fmt"
	"testing"

	"github.com/0tak2/algo_malgo/leet-go/common"
)

func TestLargestTwice(t *testing.T) {
	testEntries := []common.TestEntry{
		common.TestEntry{Input: []int{3, 6, 1, 0}, Output: 1},
		common.TestEntry{Input: []int{1, 2, 3, 4}, Output: -1},
		common.TestEntry{Input: []int{3, 6, 1, 0}, Output: 1},
	}

	for _, testEntry := range testEntries {
		result := dominantIndex(testEntry.Input.([]int))

		if result == testEntry.Output {
			fmt.Printf("Passed... expected: %v, output: %v\n", testEntry.Output, result)
		} else {
			fmt.Printf("Wrong... expected: %v, output: %v\n", testEntry.Output, result)
			t.Error(fmt.Sprintf("Wrong... expected: %v, output: %v", testEntry.Output, result))
		}
	}
}
