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

// MARK: - Intersection of Two Arrays
// https://leetcode.com/explore/learn/card/hash-table/183/combination-with-other-algorithms/1105/
// 두 Int 배열이 주어졌을 때 교집합을 구하는 문제이다. 반환하는 배열에는 값이 중복되면 안된다.

// 혼자 풀기
// 마땅히 효율적인 솔루션이 생각이 나지 않았다. 각 인풋 배열을 Set으로 만들고 인덱스를 대상 수로 한 카운트 테이블을
// 만들어서 겹치는 값을 샜다.
// 0ms, 20.2MB
func intersection(_ nums1: [Int], _ nums2: [Int]) -> [Int] {
    let set1 = Set(nums1)
    let set2 = Set(nums2)
    
    var countTable = [Int]()
    for _ in (0...1000) {
        countTable.append(0)
    }
    
    for set in [set1, set2] {
        for i in set {
            countTable[i] += 1
        }
    }
    
    var result = [Int]()
    countTable.enumerated().map { (index, item) in
        if item == 2 {
            result.append(index)
        }
    }
    return result
}

// 샘플 답변
// 두 배열 중 하나를 기준 배열로 잡고 다른 배열을 순회해가며 기준 배열에 존재하는 값인지 판별하고,
// 존재하는 값이라면 Set에 담도록 했다.
// 기존 배열에 존재하는 값인지 판별할 떄에는 이진 탐색을 했다.
func existIn(_ nums: [Int], _ target: Int) -> Bool {
    var left = 0
    var right = nums.count - 1
    
    while left <= right {
        let mid = left + (right - left) / 2
        if nums[mid] == target {
            return true
        } else if nums[mid] < target {
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    return false
}

func intersection2(_ nums1: [Int], _ nums2: [Int]) -> [Int] {
     var a = nums1.sorted()
     var b = nums2.sorted()

     var res: Set<Int> = []
    
    for i in 0..<b.count {
        let target = b[i]
        if existIn(a, target) {
            res.insert(target)
        }
    }

    return Array(res)
}

// MARK: - Happy Number
// https://leetcode.com/explore/learn/card/hash-table/183/combination-with-other-algorithms/1131/
// 해피넘버인지 판별하는 문제이다.
// 해피넘버는 자릿수 각 수를 제곱해 더하는 일을 반복했을 때 1이 되는 수를 말한다.
// 1이 안되는 경우 다시 자기 자신이 되어 무한 루프가 되는데, 이런 수는 언해피넘버라고 한다. 신기하다.

// Trial 1
// 처음에는 무한루프라는 것에서 플로이드 알고리즘으로 풀었다.
// 3ms, 20.1MB
func separateDigits(_ n: Int) -> [Int] {
    return String(n).compactMap { $0.wholeNumberValue }
}

func nextNumber(_ n: Int) -> Int {
    let digits = separateDigits(n)
    
    var result = 0 // reduce로 하면 될텐데 자동완성 없이 인자 순서와 레이블을 모르겠다...
    for d in digits {
        result += d * d
    }
    
    return result
}

func isHappy(_ n: Int) -> Bool {
    var slow = n
    var fast = nextNumber(n)
    
    while slow != fast {
        slow = nextNumber(slow)
        fast = nextNumber(nextNumber(fast))
        
        print("slow: \(slow)")
        print("fast: \(fast)")
    }
    
    print()
    return slow == 1
}

// Trial 2
// 이게 왜 해시셋 문제로 나오지? 의아했는데 제출된 샘플 코드를 보니
// 해시셋에 계산했던 수를 담아서 다시 나오는지를 보는 식으로 사용했다.
// 플로이드 알고리즘은 두 개의 포인터를 쓰면서 이중으로 계산하기 떄문에
// 이 방식이 공간복잡도는 높아도 평균적인 처리 속도는 빨라지는 것 같다.
// 1ms, 20.2MB
func isHappy2(_ n: Int) -> Bool {
    var cur = n
    
    var seen = Set<Int>()
    
    while !seen.contains(cur) {
        seen.insert(cur)
        cur = nextNumber(cur)
    }
    
    return cur == 1
}

//: [Next](@next)
