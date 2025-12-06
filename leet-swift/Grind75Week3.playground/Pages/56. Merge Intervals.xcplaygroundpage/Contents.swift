//: [Previous](@previous)

import Foundation

/// https://leetcode.com/problems/merge-intervals/
class Solution {
    func merge(_ intervals: [[Int]]) -> [[Int]] {
        guard intervals.count > 0 else { return [[]] }

        var sorted = intervals.sorted { $0[0] < $1[0] }

        var output = [[Int]]()
        output.append(sorted.first!)
        for interval in sorted {
            let prev = output.last!
            let curr = interval

            if curr[0] > prev[1] { // 현재시작점 > 이전끝점
                // 겹치지 않음
                output.append(curr)
            } else {
                // 겹침
                output.removeLast()
                output.append([
                    min(prev[0], curr[0]),
                    max(prev[1], curr[1])
                ])
            }
        }

        return output
    }
}

//: [Next](@next)
