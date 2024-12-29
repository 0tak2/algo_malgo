//: [Previous](@previous)

import Foundation

// Open the Lock
// https://leetcode.com/explore/learn/card/queue-stack/231/practical-application-queue/1375/
/**
 ### 분석
 4자리 회전형 자물쇠가 0000으로 세팅된 채 주어지면, taget으로 만들어 자물쇠를 푸는 것이 목표이다.
 단 회전 도중 deadends에 해당하게 되면 더 이상 자물쇠가 회전하지 않고 멈추게 된다.
 최단 회전 수를 구하면 된다. 방법이 없으면 -1을 반환한다.
 
 ### 접근
 Queue 섹션에 있는 문제기 때문에 BFS로 접근해보기로 한다.
 자물쇠에 대한 상태 트리를 생각해본다면, 한 노드에 대해 최대 8가지 자식 노드가 생길 수 있다. (자리별로 상/하)
 이때 deadends에 해당하는 경우 큐에 추가하면 안된다.
 
 걸리는 점
 - 중복 방문을 문제에서 제한하고 있지는 않는데 무한루프가 생기지는 않을지 걱정이다. -> 결과: 시간초과
 - 자물쇠 상태가 문자열로 주어지기 때문에 문자열 연산이 필요하다.
 
 ### 수정
 - 단순한 BFS 구현으로는 올바른 답을 찾을 수 없었다... 첫번째 케이스의 경우 결과가 822번이 나왔다... (SolutionWrong)
 - Solution이 무료여서 참고
   - 원래 while문 안에 현재 q의 모든 노드를 반복하는 루프를 하나 더 넣는다 (Create a queue pending_combinations to traverse all combinations in level-wise BFS)
   - 그리고 추가한 루프가 끝나면 count를 1 올린다
   - 카운팅을 레벨 단위로 하기 위해 이런 방식을 취한 듯 하다
 */

// Worng Answers
class SolutionWrong {
    var visited: [String: Bool] = [:]
    
    func turn(_ original: String, for indexNumber: Int, by upOrDown: Int) -> String {
        guard indexNumber < 4 && upOrDown != 0 else { return "" }
        
        let index: String.Index = original.index(original.startIndex, offsetBy: indexNumber)
        var number = Int(String(original[index]))!
        if upOrDown > 0 {
            number = number + 1 > 9 ? 0
                                    : number + 1
        } else {
            number = number - 1 < 0 ? 9
                                    : number - 1
        }
        
        var substring1 = original[original.startIndex..<index]
//        print("left \(substring1)")
        
        let nextIndex = original.index(index, offsetBy: 1)
        var substring2 = original[nextIndex..<original.endIndex]
//        print("right \(substring2)")
//        print("result \(substring1)\(number)\(substring2)")
        
        return "\(substring1)\(number)\(substring2)"
    }
    
    func openLock(_ deadends: [String], _ target: String) -> Int {
        var lock: String = "0000"
        if target == "0000" {
            return 0
        }
        
        var count = 0
        
        if deadends.contains("0000") {
            return -1
        }
        
        var q: [String] = [lock]
        visited[lock, default: false].toggle()
        while !q.isEmpty {
            let node = q.removeFirst()
//            print("visited: \(node)")
            if node == target {
//                print("found \(node)")
                return count
            }
            count += 1 // 한 번 더 돌려야 한다
            
            for i in 0..<4 {
                let upper = turn(node, for: i, by: 100)
                if !deadends.contains(upper) && !visited[upper, default: false] {
                    q.append(upper)
                    visited[upper, default: false].toggle()
                }
                
                let down = turn(node, for: i, by: -100)
                if !deadends.contains(down) && !visited[down, default: false] {
                    q.append(down)
                    visited[down, default: false].toggle()
                }
            }
        }
        
        return -1
    }
}

// 2039ms, 18.1MB
class Solution {
    var visited: [String: Bool] = [:]
    
    func turn(_ original: String, for indexNumber: Int, by upOrDown: Int) -> String {
        guard indexNumber < 4 && upOrDown != 0 else { return "" }
        
        let index: String.Index = original.index(original.startIndex, offsetBy: indexNumber)
        var number = Int(String(original[index]))!
        if upOrDown > 0 {
            number = number + 1 > 9 ? 0
                                    : number + 1
        } else {
            number = number - 1 < 0 ? 9
                                    : number - 1
        }
        
        var substring1 = original[original.startIndex..<index]
//        print("left \(substring1)")
        
        let nextIndex = original.index(index, offsetBy: 1)
        var substring2 = original[nextIndex..<original.endIndex]
//        print("right \(substring2)")
//        print("result \(substring1)\(number)\(substring2)")
        
        return "\(substring1)\(number)\(substring2)"
    }
    
    func openLock(_ deadends: [String], _ target: String) -> Int {
        var lock: String = "0000"
        if target == "0000" {
            return 0
        }
        
        var count = 0
        
        if deadends.contains("0000") {
            return -1
        }
        
        var q: [String] = [lock]
        visited[lock, default: false].toggle()
        while !q.isEmpty {
            for pendingLock in q {
                let node = q.removeFirst()
//                print("visited: \(node)")
                if node == target {
//                    print("found \(node)")
                    return count
                }
                
                for i in 0..<4 {
                    let upper = turn(node, for: i, by: 100)
                    if !deadends.contains(upper) && !visited[upper, default: false] {
                        q.append(upper)
                        visited[upper, default: false].toggle()
                    }
                    
                    let down = turn(node, for: i, by: -100)
                    if !deadends.contains(down) && !visited[down, default: false] {
                        q.append(down)
                        visited[down, default: false].toggle()
                    }
                }
            }
            
            count += 1 // 한 번 더 돌려야 한다
        }
        
        return -1
    }
}

let solution = Solution()

// Test 1
/*
 ["0201","0101","0102","1212","2002"]
 "0202"
 
 6
 */
let result1 = solution.openLock(["0201","0101","0102","1212","2002"], "0202")
print("answer: \(result1)")

//: [Next](@next)
