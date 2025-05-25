//: [Previous](@previous)

import Foundation

// MARK: - Two Sum
// https://leetcode.com/explore/learn/card/hash-table/184/comparison-with-other-data-structures/1115/
// 수의 배열이 주어지면, 그 중 두 가지 원소를 뽑아 더했을 때 target을 만들 수 있는지를 판별한다.
// 배열을 순회하면서, target을 만들려면 더해야 하는 값이 맵에 있는지를 확인하도록 구현했다.
// 2ms, 20.2MB
func twoSum(_ nums: [Int], _ target: Int) -> [Int] {
    var indexForNum = Dictionary<Int, Int>() // K Number -> V Index
    var result = [Int]()
    
    nums.enumerated().map { (index, item) in
        let lookFor = target - item
        if let lookForIndex = indexForNum[lookFor] {
            result.append(index)
            result.append(lookForIndex)
        }
                           
        indexForNum[item] = index // 같은 수가 다른 인덱스로 두 번 위치하는 경우가 있기 때문에, 나중에 덮어 쓰도록 뒤에서 테이블을 업데이트했다 -> *1
    }
    
    return result
}
// *1 테이블을 앞에서 업데이트하도록 했더니, [3, 3], 6과 같은 케이스를 처리할 수 없었다.

// MARK: - Isomorphic Strings
// 두 스트링이 Isomorphic한지 판별한다.
// Isomorphic한 스트링? 두 스트링이 각각 1:1 대응되는 경우

// Try 1
// 맵을 하나 쓰고, 두 번 검증했다.
// 대칭꼴의 문자열이 들어올 떄는 틀린 답을 냈다.
func checkIsomorphicWrong(_ s: String, _ t: String) -> Bool {
    var map = [Character: Character]()
    
    let lenght = s.count
    for i in 0..<lenght {
        let sc = s[s.index(s.startIndex, offsetBy: i)]
        let tc = t[t.index(t.startIndex, offsetBy: i)]
        
        if map[sc] == nil {
            map[sc] = tc
        } else {
            return map[sc] == tc
        }
        
//        print(map)
    }
    
    return true
}

func isIsomorphicWrong(_ s: String, _ t: String) -> Bool {
    return checkIsomorphicWrong(s, t) && checkIsomorphicWrong(t, s)
}

print(isIsomorphicWrong("egg", "add")) // expected: true, actual: true
print(isIsomorphicWrong("aaeaa", "uuxyy")) // expected: false, actual: true => 대칭인 경우 틀린다.
print(isIsomorphicWrong("paper", "title"))// expected: true, actual: true

// Try 2
// 맵을 두 개 써서 해결했다. 다만 시간이 무지하게 오래걸린다.
// 4843ms, 20.3MB
func isIsomorphic(_ s: String, _ t: String) -> Bool {
    var map1 = [Character: Character]()
    var map2 = [Character: Character]()
    
    for i in 0..<s.count {
        let sc = s[s.index(s.startIndex, offsetBy: i)]
        let tc = t[t.index(t.startIndex, offsetBy: i)]
        
        if let counterpart1 = map1[sc], counterpart1 != tc { // 따로 따로 확인해야한다. [Ex] aaeaa <=> uuxyy
            return false
        }
        
        if let counterpart2 = map2[tc], counterpart2 != sc {
            return false
        }
        
        map1[sc] = tc
        map2[tc] = sc
    }
    
    return true
}

print(isIsomorphic("egg", "add")) // expected: true, actual: true
print(isIsomorphic("aaeaa", "uuxyy")) // expected: false, actual: false
print(isIsomorphic("paper", "title")) // expected: true, actual: true

// MARK: - Minimum Index Sum of Two Lists
// https://leetcode.com/explore/learn/card/hash-table/184/comparison-with-other-data-structures/1177/
// 두 스트링 배열에서 겹치는 원소를 찾고, 각각 배열에서의 인덱스를 더한 뒤 그 값이 최소인 원소들의 배열을 반환한다.
// 문제에서 시키는 대로 구현했다. 속도는 아주 느린 편.
// 19ms, 20.1MB
func findRestaurant(_ list1: [String], _ list2: [String]) -> [String] {
    var inverseMap1 = [String: Int]() // String => Index of list1
    var inverseMap2 = [String: Int]() // String => Index of list2
    
    list1.enumerated().map { (index, item) in inverseMap1[item] = index }
    list2.enumerated().map { (index, item) in inverseMap2[item] = index }
    
    var sumAndString = [Int: [String]]()
    let intersectionSet = Set(list1).intersection(Set(list2))
    intersectionSet.map { item in
        let index1 = inverseMap1[item]!
        let index2 = inverseMap2[item]!
        
        var existingArray = sumAndString[index1 + index2, default: []]
        existingArray.append(item)
        sumAndString[index1 + index2] = existingArray
    }
    
    let minIndexSum = sumAndString.keys.min() ?? 0
    
    return sumAndString[minIndexSum]!
}

// MARK: - First Unique Character in a String
// https://leetcode.com/explore/learn/card/hash-table/184/comparison-with-other-data-structures/1120/
// 문자열 중 유일하게 한 번 등장하는 문자들을 찾고, 그중 문자열에서의 인덱스가 가장 작은 문자를 반환하는 문제이다.
// 인덱스 다루는게 너무 번거롭다. IDE 없이는 풀기 어려울 것 같다.
// 84ms, 20.2MB
func firstUniqChar(_ s: String) -> Int {
    var countMap = [Character: Int]()
    
    for c in s {
        countMap[c] = countMap[c, default: 0] + 1
    }
    
    let uniqueValues = countMap.filter { (character, count) in
        count == 1
    }
    
    var uniqueValueIndices = [Int]()
    for (c, _) in uniqueValues {
        if let index = s.firstIndex(of: c) {
            uniqueValueIndices.append(s.distance(from: s.startIndex, to: index)) // 진짜... 할많하않...
        }
    }
    
    return uniqueValueIndices.min() ?? -1
}
print(firstUniqChar("abbccdde"))

// MARK: - Intersection of Two Arrays II
// 두 배열이 주어지면, 겹치는 부분 배열을 반환한다. 원소는 겹치는 만큼 등장해야 한다.
// 셋으로 캐스팅하여 교집합연산을 하고, 기존 배열들에서 빈도를 각각 계산해 최소 빈도만큼 추가하여 반환했다.
// 2ms, 20.1MB
func getFreq(for target: Int, in list: [Int]) -> Int {
    var result = 0
    list.map { i in
        if i == target {
            result += 1
        }
    }
    return result
}

func intersect(_ nums1: [Int], _ nums2: [Int]) -> [Int] {
    var map = [Int: Int]() // number -> count
    let intersected = Set(nums1).intersection(Set(nums2))
    var result = [Int]()
    
    intersected.map { i in
        let freq = min(getFreq(for: i, in: nums1), getFreq(for: i, in: nums2))
        for _ in 0..<freq {
            result.append(i)
        }
    }
    
    return result
}

// MARK: - Contains Duplicate II
// https://leetcode.com/explore/learn/card/hash-table/184/comparison-with-other-data-structures/1121/
// 정수 배열 nums와 정수 k가 주어졌을 때, 배열에 nums[i] == nums[j], abs(i - j) <= k가 되는 i, j가 존재하는지 판별한다.
// i와 j가 k보다 작기만 하면 되기 때문에 가장 최소의 경우를 따진다. 그렇다면 인접한 동일 값을 찾아야 한다.
// => 원소를 키로 하고, 최근 인덱스를 값으로 하는 맵을 두어 순회하면서 계산했다.
// 참고: https://algo.monster/liteproblems/219
// 32ms, 26.3MB
func containsNearbyDuplicate(_ nums: [Int], _ k: Int) -> Bool {
    var map = [Int: Int]() // number => latest index
    
    for (i, n) in nums.enumerated() {
        if let latestIndex = map[n], i - latestIndex <= k {
//            print("n = \(n), i = \(i), latestIndex = \(latestIndex)")
            return true
        }
        
        map[n] = i
    }
    
    return false
}

print(containsNearbyDuplicate([1, 2, 3, 1], 3)) // true
print(containsNearbyDuplicate([1, 0, 1, 1], 1)) // true
print(containsNearbyDuplicate([1, 2, 3, 1, 2, 3], 2)) // false

//: [Next](@next)
