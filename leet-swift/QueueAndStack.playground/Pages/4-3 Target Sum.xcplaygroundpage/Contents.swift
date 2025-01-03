//: [Previous](@previous)

import Foundation

// Target Sum
// https://leetcode.com/explore/learn/card/queue-stack/232/practical-application-stack/1389/
/**
 ### 분석
 숫자 배열이 주어지면, 각 숫자 사이에 + 또는 -를 추가해 식을 만들고, 그 식의 결과가 target과 같은 경우가 몇 가지인지를 반환한다.
 
 ### 접근
 예를 들어, nums=[a, b, c] target=d이면,
 + a + b + c
         - c
     - b + c
         - c
 - a + b + c
         - c
     - b + c
         - c
 와 같은 상태 트리가 나오고, 이 중 합이 target인 경우를 세면 된다.
 
 따라서 전체 경로를 유지한 채 전체를 탐색해야 하고, 이는 dfs를 통해 할 수 있다.
 
 단순 방문이 아니라 탐색하면서 계산을 해야하기 때문에, 전체 숫자 배열 / 그 중 몇 번째 인덱스 차례인지 /
 이전 단계까지 계산된 값 등 스택에 넣어서 다음 루프에 넘겨줘야 하는 데이터가 많다.
 따라서 스택 + 반복문 조합이 아니라 재귀함수 + 인자 조합으로 구현했다.
 
 ### 개선
 Solution이 무료이다. 첫번째 풀이는 이 방법과 비슷해보이고, 다른 방법들을 참고해보면 좋을 것 같다.
 
 */

// 487ms, 17.4MB
class Solution {
    func calc(_ nums: [Int], _ index: Int, _ acc: Int, _ target: Int, _ result: inout Int) {
        if index >= nums.count {
            if acc == target {
                result += 1
                return
            } else {
                return
            }
        }
        
        calc(nums, index + 1, acc + nums[index], target, &result)
        calc(nums, index + 1, acc - nums[index], target, &result)
    }
    
    func findTargetSumWays(_ nums: [Int], _ target: Int) -> Int {
        var result = 0
        
        calc(nums, 0, 0, target, &result)
        
        return result
    }
}

let solution = Solution()

let result = solution.findTargetSumWays([1,1,1,1,1], 3)
print(result)

//: [Next](@next)
