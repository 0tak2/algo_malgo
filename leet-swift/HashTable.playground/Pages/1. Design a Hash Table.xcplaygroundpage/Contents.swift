//: [Previous](@previous)

import Foundation

// MARK: - Design HashSet
// https://leetcode.com/explore/learn/card/hash-table/182/practical-applications/1139/
// 해시셋을 구현해본다.
// 해시함수를 간단한 나머지 연산 함수로 했다. 다른 함수를 쓴다면 더 빠를 것이다.

// 110ms, 23.5MB
class MyHashSet {
    static let defaultBuckets = 100000
    private var buckets = [[Int]]()

    init() {
        (0..<MyHashSet.defaultBuckets).map { [self] _ in
            self.buckets.append([])
        }
    }
    
    private func hash(_ key: Int) -> Int {
        return key % buckets.count
    }
    
    func add(_ key: Int) {
        let bucketNumber = hash(key)
        if !buckets[bucketNumber].contains(key) {
            buckets[bucketNumber].append(key)
        }
    }
    
    func remove(_ key: Int) {
        let bucketNumber = hash(key)
        if buckets[bucketNumber].contains(key) {
            buckets[bucketNumber].removeAll { $0 == key }
        }
    }
    
    func contains(_ key: Int) -> Bool {
        let bucketNumber = hash(key)
        return buckets[bucketNumber].contains(key)
    }
}

// MARK: - Design HashMap
// https://leetcode.com/explore/learn/card/hash-table/182/practical-applications/1140/
// 간단한 해시맵을 구현한다.

// 118ms, 23MB
class MyHashMap {
    static let defaultBuckets = 100000
    private var buckets = [[(Int, Int)]]() // key, value

    init() {
        (0..<MyHashMap.defaultBuckets).map { [self] _ in
            self.buckets.append([])
        }
    }
    
    private func hash(_ key: Int) -> Int {
        return key % buckets.count
    }
    
    func put(_ key: Int, _ value: Int) {
        let bucketNumber = hash(key)
        
        let contains = buckets[bucketNumber].contains(where: { (k, v) in
            return k == key
        })
        
        if contains {
            buckets[bucketNumber].removeAll(where: { (k, v) in
                return k == key
            })
        }
        
        buckets[bucketNumber].append((key, value))
    }
    
    func get(_ key: Int) -> Int {
        let bucketNumber = hash(key)
        let findResult = buckets[bucketNumber].first { (k, v) in
            return key == k
        }
        
        if let findResult = findResult {
            return findResult.1
        }
        return -1
    }
    
    func remove(_ key: Int) {
        let bucketNumber = hash(key)
        
        let contains = buckets[bucketNumber].contains(where: { (k, v) in
            return k == key
        })
        
        if contains {
            buckets[bucketNumber].removeAll(where: { (k, v) in
                return k == key
            })
        }
    }
}

//: [Next](@next)
