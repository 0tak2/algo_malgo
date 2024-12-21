//: [Previous](@previous)

import Foundation

// MARK: - 70. Climbing Stairs (Grind75 #2-3)
// https://leetcode.com/problems/climbing-stairs
/**
 ### 분석
 정상에 도달하는 데 n개 만큼의 계단을 올라야한다. 이때 한 번 계단을 오를 때 계단을 한 칸 올라가거나, 한 번에 두 칸 올라갈 수 있다.
 그렇다면 전체 경우의 수는 몇 가지가 나오는지를 구하면 된다.
 
 ### 접근
 점화식을 떠올렸다.
 R_n을 n칸 오르는 데 가능한 모든 경우의 수라고 하면,
 
 R_n = R_n-1 + R_n-2
 &
 R_2 = 2, R_1 = 1
 
 계단을 한 번에 오르는 방법이 두 가지이기 때문에, (한 번에 한 칸, 또는 두 칸)
 n칸 오를 때 가능한 경우의 수는 n-1칸을 오르는 모든 경우의 수와 n-2칸을 오르는 모든 경우의 수의 합과 같다.
 
 ### 구현
 1. 먼저 단순히 점화식대로 재귀로 구현해본다. -> 시간 초과 `Solution_recursive`
 2. 점화식대로 짜되, 메모이제이션을 해본다. `Solution_topdown`
 3. bottom-up으로 최적화해본다. `Solution`
 */

// 시간 초과
class Solution_recursive {
    func result(_ n: Int) -> Int {
        if n == 1 {
            return 1
        }
        
        if n == 2 {
            return 2
        }
        
        return result(n - 2) + result(n - 1)
    }
    
    func climbStairs(_ n: Int) -> Int {
        return result(n)
    }
}

// 0ms, 17.13MB
class Solution_topdown {
    var memo: [Int] = Array(repeating: -1, count: 46) // 추가 공간 사용
    
    init() {
        memo[2] = 2
        memo[1] = 1
    }
    
    func result(_ n: Int) -> Int {
        if memo[n] != -1 {
            return memo[n]
        }
        
        memo[n] = result(n - 2) + result(n - 1)
        return memo[n]
    }
    
    func climbStairs(_ n: Int) -> Int {
        return result(n)
    }
}

// 0ms, 17.06MB
class Solution {
    func climbStairs(_ n: Int) -> Int {
        if n <= 2 {
            return n // n=1, n=2
        }
        
        var prv1 = 1
        var prv2 = 2
        for i in 3...n {
            let current = prv1 + prv2
            
            // prepare for next cycle
            prv1 = prv2
            prv2 = current
        }
        
        return prv2
    }
}

//: [Next](@next)
