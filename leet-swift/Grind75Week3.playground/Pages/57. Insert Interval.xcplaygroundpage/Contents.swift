//: [Previous](@previous)

import Foundation

class Solution {
    func insert(_ intervals: [[Int]], _ newInterval: [Int]) -> [[Int]] {
        var newIntervals = intervals

        // 삽입
        // newIntervals.append(newInterval)
        // newIntervals.sort { $0[0] < $1[0] }
        var insertIndex = newIntervals.count
        
        for (index, interval) in newIntervals.enumerated() {
            if interval[0] > newInterval[0] {
                insertIndex = index
                break
            }
        }
        
        newIntervals.insert(newInterval, at: insertIndex)

        // 머지
        var output = [[Int]]()
        output.append(newIntervals[0])

        for i in 1..<newIntervals.count {
            let lastInterval = output.last!
            let lastStart = lastInterval[0]
            let lastEnd = lastInterval[1]

            let nextInterval = newIntervals[i]
            let nextStart = nextInterval[0]
            let nextEnd = nextInterval[1]

            if lastEnd < nextStart { // 안겹침
                output.append(nextInterval)
            } else { // 겹침
                let merged = [
                    min(lastStart, nextStart),
                    max(nextEnd, lastEnd)
                ]
                output.removeLast()
                output.append(merged)
            }
        }

        return output
    }
}

let solution = Solution()
let result = solution.insert([[1, 3], [6, 9]], [2, 5])
print(result) // [[1, 5], [6, 9]]

//: [Next](@next)
