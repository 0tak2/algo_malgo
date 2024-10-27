package grind75

import (
	"fmt"
	"testing"

	"github.com/0tak2/algo_malgo/leet-go/common"
)

func TestPlusOne(t *testing.T) {
	testEntries := []common.TestEntry{
		common.TestEntry{Input: "A man, a plan, a canal: Panama", Output: true},
		common.TestEntry{Input: "0P", Output: false},
	}

	for _, testEntry := range testEntries {
		result := isPalindrome(testEntry.Input.(string))

		if result == testEntry.Output {
			fmt.Printf("Passed... expected: %v, output: %v\n", testEntry.Output, result)
		} else {
			fmt.Printf("Wrong... expected: %v, output: %v\n", testEntry.Output, result)
			t.Error(fmt.Sprintf("Wrong... expected: %v, output: %v", testEntry.Output, result))
		}
	}
}
