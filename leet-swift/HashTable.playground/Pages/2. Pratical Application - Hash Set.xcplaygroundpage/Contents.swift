//: [Previous](@previous)

import Foundation

// MARK: - Contains Duplicate
// https://leetcode.com/explore/learn/card/hash-table/183/combination-with-other-algorithms/1112/
// 11ms, 22.5MB
func containsDuplicate(_ nums: [Int]) -> Bool {
    let set = Set(nums)
    return nums.count != set.count
}

// MARK: - Single Number
// https://leetcode.com/explore/learn/card/hash-table/183/combination-with-other-algorithms/1176/
// 0ms, 19.7MB
// 네이버 면접에서 꺠졌던 문제. 해시테이블을 써도 되지만, XOR 연산으로 빠르게 가능하다.
// 자기 자신과 XOR 연산을 하면 0이 된다. 그럼 모든 값을 순회하며 XOR 연산하면 홀수 개 있는 수가 남는다.
func singleNumber(_ nums: [Int]) -> Int {
    var start = nums.first!
    
    for num in nums[1..<nums.count] {
        start = start ^ num
    }
    
    return start
}

//: [Next](@next)
