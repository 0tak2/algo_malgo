//: [Previous](@previous)

import Foundation

// Design Circular Queue
// https://leetcode.com/explore/learn/card/queue-stack/228/first-in-first-out-data-structure/1337/
class MyCircularQueue {
    private var data: [Int]
    private var head = 0
    private var tail = -1 // 최초 상태에서만
    private var length = 0
    private var capacity: Int {
        data.count
    }
    
    init(_ k: Int) {
        data = Array(repeating: -1, count: k)
    }
    
    /**
     tail을 한 칸 당기고 그 자리에 값을 쓴다
     */
    func enQueue(_ value: Int) -> Bool {
        print("before enQueue head: \(head) tail: \(tail)")
        if isFull() {
            print("failed")
            return false
        }
        
        if tail + 1 == capacity {
            tail = 0
        } else {
            tail += 1
        }
        data[tail] = value
        
        length += 1
        print("enqued")
        print("after enQueue head: \(head) tail: \(tail)")
        print(data)
        return true
    }
    
    /**
     head가 가리키는 원소를 지우고 head를 한 칸 당긴다
     */
    func deQueue() -> Bool {
        print("before deQueue head: \(head) tail: \(tail)")
        if isEmpty() {
            print("failed")
            return false
        }
        
        data[head] = -1
        if head + 1 == capacity {
            head = 0
        } else {
            head += 1
        }
        
        length -= 1
        print("dequed")
        print("after deQueue head: \(head) tail: \(tail)")
        print(data)
        return true
    }
    
    func Front() -> Int {
        return data[head]
    }
    
    func Rear() -> Int {
        if tail < 0 {
            return -1
        }
        
        return data[tail]
    }
    
    func isEmpty() -> Bool {
        return length == 0
    }
    
    func isFull() -> Bool {
        // head, tail이 역방향으로 인접해있거나 끝과 끝에 있는 경우
        return length >= capacity
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * let obj = MyCircularQueue(k)
 * let ret_1: Bool = obj.enQueue(value)
 * let ret_2: Bool = obj.deQueue()
 * let ret_3: Int = obj.Front()
 * let ret_4: Int = obj.Rear()
 * let ret_5: Bool = obj.isEmpty()
 * let ret_6: Bool = obj.isFull()
 */

// Test
func test(commands: [String], vals: [[Int]], step: Int = Int.max) -> [Any?] {
    var q = MyCircularQueue(0)
    var result: [Any?] = []
    
    for (i, c) in commands.enumerated() {
        if i > step {
            break
        }
        
        let v = vals[i].first
        let ret: Any?
        
        switch c {
        case "MyCircularQueue":
            q = MyCircularQueue(v ?? -1)
            ret = nil
        case "enQueue":
            ret = q.enQueue(v ?? -1)
        case "deQueue":
            ret = q.deQueue()
        case "Front":
            ret = q.Front()
        case "Rear":
            ret = q.Rear()
        case "isFull":
            ret = q.isFull()
        case "isEmpty":
            ret = q.isEmpty()
        default: continue
        }
        
        result.append(ret)
    }
    
    return result
}

// Test 1
/*
 ["MyCircularQueue","enQueue","enQueue","enQueue","enQueue","Rear","isFull","deQueue","enQueue","Rear"]
 [[3],[1],[2],[3],[4],[],[],[],[4],[]]
 [null,true,true,true,false,3,true,true,true,4]
 */
print("====== test 1 ======")
let result1 = test(commands: ["MyCircularQueue","enQueue","enQueue","enQueue","enQueue","Rear","isFull","deQueue","enQueue","Rear"],
                   vals: [[3],[1],[2],[3],[4],[],[],[],[4],[]])
//                   step: 100)
print(result1)

// Test 2
/*
 ["MyCircularQueue","enQueue","Rear","Rear","deQueue","enQueue","Rear","deQueue","Front","deQueue","deQueue","deQueue"]
 [[6],[6],[],[],[],[5],[],[],[],[],[],[]]
 [null,true,6,6,true,true,5,true,-1,false,false,false]
 */
print("====== test 2 ======")
let result2 = test(commands: ["MyCircularQueue","enQueue","Rear","Rear","deQueue","enQueue","Rear","deQueue","Front","deQueue","deQueue","deQueue"],
                   vals: [[6],[6],[],[],[],[5],[],[],[],[],[],[]])
//                   step: 100)
print(result2)

// Test 3
/*
 ["MyCircularQueue","enQueue","deQueue","Front","deQueue","Front","Rear","enQueue","isFull","deQueue","Rear","enQueue"]
 [[3],[7],[],[],[],[],[],[0],[],[],[],[3]]
 [null,true,true,-1,false,-1,-1,true,false,true,-1,true]
 */
print("====== test 3 ======")
let result3 = test(commands: ["MyCircularQueue","enQueue","deQueue","Front","deQueue","Front","Rear","enQueue","isFull","deQueue","Rear","enQueue"],
                   vals: [[3],[7],[],[],[],[],[],[0],[],[],[],[3]])
//                   step: 100)
print(result3)

// Test 4
/*
 ["MyCircularQueue","Rear","enQueue","enQueue","enQueue","Rear","isFull","deQueue","enQueue"]
 [[3],[],[2],[3],[4],[],[],[],[4]]
 [null,-1,true,true,true,4,true,true,true]
 */
print("====== test 4 ======")
let result4 = test(commands: ["MyCircularQueue","Rear","enQueue","enQueue","enQueue","Rear","isFull","deQueue","enQueue"],
                   vals: [[3],[],[2],[3],[4],[],[],[],[4]])
//                   step: 100)
print(result4)

//: [Next](@next)
