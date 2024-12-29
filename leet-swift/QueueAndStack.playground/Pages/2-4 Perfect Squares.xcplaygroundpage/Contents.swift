//: [Previous](@previous)

import Foundation

// Perfect Squares
// https://leetcode.com/explore/learn/card/queue-stack/231/practical-application-queue/1371/
/*
 ### 분석
 주어진 수 n을 최소한의 제곱수들을 이용해서 합으로 나타낸다면, 몇 개의 제곱수가 필요한지 구하는 문제이다.
 예> 12 = 4 + 4 + 4이므로, 3
 다른 문제에 비해서 많이 어려워서 다른 자료들을 참고했다.
 
 ### DP
 DP로 풀이하는 예시가 많았다. (참고: https://algo.monster/liteproblems/279)
 
 우리가 구하고자 하는 함수(n을 만들려면 최소 몇 개의 제곱수를 더해야 하는가)를 F_n이라고 하고,
 임의의 수 j에 대하여, j보다 작은 i*i가 존재한다고 할 때,
 F_j = F_j-i*i + 1이 성립한다.
 예를 들어, F_10 = F_9 + 1 = 0 + 1 = 1
 
 단 이때 위의 점화식에 따른 메모이제이션 테이블은 두 변수(위에서 i, j)에 의해 결정되므로 루프가 두 개 필요하다.
 밖의 루프에서는 n보다 작은 모든 제곱수를 오름차순으로 순회한다.
 안의 루프에서는 현재 제곱수부터 n까지를 순회하면서, 현재 수를 구성할 수 있는 최소 경우의 수를 구한다.
 for i*i in 1...n
    for j in i*i...n
       F_j = min(F_j, F_j-i*i + 1)
 그렇다면 j에 대한 값은 다음 범위에 걸쳐 업데이트된다.
 j = {1*1부터 n까지}
 j = {2*2부터 n까지}
 j = {3*3부터 n까지}
 ...
 
 ### BFS
 위의 코드는 엄연히 DP이다. 필요에 따라 상태트리를 탐색한다는 점에서는 BFS와 취지가 비슷하지만 큐를 이용하지는 않는다.
 ChatGPT를 이용해 위의 코드를 BFS로 변경했다.
 
 큐에는 현재 빼고 남은 수와 현재 사용된 개수의 누적을 같이 넣는다.
 visited에는 빼고 남은 남은 수를 넣는다.
 
 떠올리기는 어려운 아이디어지만, 코드를 보면 직관적으로 이해된다.
 */

// 63ms, 17.8MB
class Solution {
    func numSquares(_ n: Int) -> Int {
        var q: [[Int]] = [] // 0:Remain 1:Steps
        q.append([n, 0])
        var visited: Set<Int> = [] // Remains
        visited.insert(n)
        
        while !q.isEmpty {
            let popped = q.removeFirst()
//            print(popped)
            let (remain, steps) = (popped[0], popped[1])
            
            let sqrtMax = Int(sqrt(Double(remain)))
            for i in 1...sqrtMax {
                let square = i * i
                let nextRemain = remain - square
                
                if nextRemain == 0 {
                    return steps + 1
                }
                
                if !visited.contains(nextRemain) {
                    visited.insert(nextRemain)
                    q.append([nextRemain, steps + 1])
                }
            }
        }
        
        return 0
    }
}
/*
 [12, 0]
 [11, 1]
 [8, 1]
 [3, 1]
 [10, 2]
 [7, 2]
 [2, 2]
 [4, 2]
 3
 */

// 7ms, 17.8MB
class SolutionWithDP {
    func numSquares(_ n: Int) -> Int {
        let sqrtMax = Int(sqrt(Double(n)))
        var memo = Array(repeating: Int.max, count: n + 1)
        memo[0] = 0
        
        for i in 1...sqrtMax {
            let square = i * i // 1부터 n까지
            
//            print("+ i=\(i) square=\(square)")
            
            for j in square...n {
//                print("- j=\(j) square=\(square)")
//                print("\t memo[\(j)]=\(memo[j]) memo[\(j-square)]+1=\(memo[j - square] + 1)")
                memo[j] = min(memo[j], memo[j - square] + 1)
            }
        }
        
        return memo[n]
    }
}
/*
 + i=1 square=1
 - j=1 square=1
      memo[1]=9223372036854775807 memo[0]+1=1
 - j=2 square=1
      memo[2]=9223372036854775807 memo[1]+1=2
 - j=3 square=1
      memo[3]=9223372036854775807 memo[2]+1=3
 - j=4 square=1
      memo[4]=9223372036854775807 memo[3]+1=4
 - j=5 square=1
      memo[5]=9223372036854775807 memo[4]+1=5
 - j=6 square=1
      memo[6]=9223372036854775807 memo[5]+1=6
 - j=7 square=1
      memo[7]=9223372036854775807 memo[6]+1=7
 - j=8 square=1
      memo[8]=9223372036854775807 memo[7]+1=8
 - j=9 square=1
      memo[9]=9223372036854775807 memo[8]+1=9
 - j=10 square=1
      memo[10]=9223372036854775807 memo[9]+1=10
 - j=11 square=1
      memo[11]=9223372036854775807 memo[10]+1=11
 - j=12 square=1
      memo[12]=9223372036854775807 memo[11]+1=12
 + i=2 square=4
 - j=4 square=4
      memo[4]=4 memo[0]+1=1
 - j=5 square=4
      memo[5]=5 memo[1]+1=2
 - j=6 square=4
      memo[6]=6 memo[2]+1=3
 - j=7 square=4
      memo[7]=7 memo[3]+1=4
 - j=8 square=4
      memo[8]=8 memo[4]+1=2
 - j=9 square=4
      memo[9]=9 memo[5]+1=3
 - j=10 square=4
      memo[10]=10 memo[6]+1=4
 - j=11 square=4
      memo[11]=11 memo[7]+1=5
 - j=12 square=4
      memo[12]=12 memo[8]+1=3
 + i=3 square=9
 - j=9 square=9
      memo[9]=3 memo[0]+1=1
 - j=10 square=9
      memo[10]=4 memo[1]+1=2
 - j=11 square=9
      memo[11]=5 memo[2]+1=3
 - j=12 square=9
      memo[12]=3 memo[3]+1=4
 */

let result = Solution().numSquares(12)
print(result)
//: [Next](@next)
