package array_and_string_ch1

import (
	"fmt"
	"testing"

	"github.com/0tak2/algo_malgo/leet-go/common"
)

func TestPlusOne(t *testing.T) {
	testEntries := []common.TestEntry{
		common.TestEntry{Input: []int{1, 2, 3}, Output: []int{1, 2, 4}},
		common.TestEntry{Input: []int{4, 3, 2, 1}, Output: []int{4, 3, 2, 2}},
		common.TestEntry{Input: []int{9}, Output: []int{1, 0}},
		common.TestEntry{Input: []int{2, 9, 9}, Output: []int{3, 0, 0}},
		common.TestEntry{Input: []int{9, 9}, Output: []int{1, 0, 0}},
		common.TestEntry{Input: []int{9, 8, 9}, Output: []int{9, 9, 0}},
	}

	for _, testEntry := range testEntries {
		result := plusOne(testEntry.Input.([]int))
		expected := testEntry.Output.([]int)

		if len(result) != len(expected) {
			fmt.Printf("Wrong... expected: %v, output: %v\n", testEntry.Output, result)
			t.Error(fmt.Sprintf("Wrong... expected: %v, output: %v", testEntry.Output, result))
			return
		}

		for i, el := range result {
			if el != expected[i] {
				fmt.Printf("Wrong... expected: %v, output: %v\n", testEntry.Output, result)
				t.Error(fmt.Sprintf("Wrong... expected: %v, output: %v", testEntry.Output, result))
				return
			}
		}

		fmt.Printf("Passed... expected: %v, output: %v\n", testEntry.Output, result)
	}
}
