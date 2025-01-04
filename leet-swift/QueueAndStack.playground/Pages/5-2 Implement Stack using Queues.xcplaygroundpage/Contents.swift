//: [Previous](@previous)

import Foundation

// Implement Stack using Queues
// https://leetcode.com/explore/learn/card/queue-stack/239/conclusion/1387/
/**
 ### 분석
 두 개의 큐를 이용해 스택을 구현
 
 ### 접근
 큐 두개로 스택을 만드는 것과 달리 방법이 잘 떠오르지 않아서 찾아봤다.
 참고: https://algo.monster/liteproblems/225
 
 한 개의 큐는 항상 비워두고, 한 개의 큐에는 반전된 순서로 원소를 보관하는 컨셉이다. 각각을 쓰기용 큐, 읽기용 큐로 쓴다.
 
 우선 두 개의 큐를 q1, q2라고 하자.
 push가 실행되면 q1에 일단 새 원소를 넣고, q2의 원소를 모두 pop해 q1에 넣는다.
 그 후 q1과 q2를 스왑한다. 그럼 q1이 빈 큐가 되고, q2는 반전된 순서로 원소들이 위치한다.
 
 예로 다음 연산를 도해하면,
 push 123 -> push 456 -> push 789
 
 ```
 ==== push: 123 ====
 
 (push to q1)
     head       tail
 q1  123
 q2
 
 (swap)
     head       tail
 q1
 q2  123
 ```
 
 ```
 ==== push: 456 ====
 
 (push to q1)
     head       tail
 q1  456
 q2  123
 
 (transfer all elements of q2 to q1)
     head       tail
 q1  456   123
 q2
 
 (swap)
     head       tail
 q1
 q2  456   123
 ```
 
 ```
 ==== push: 789 ====
 
 (push to q1)
     head       tail
 q1  789
 q2  456   123
 
 (transfer all elements of q2 to q1)
     head       tail
 q1  789   456   123
 q2
 
 (swap)
     head       tail
 q1
 q2  789   456   123
 ```
 
 pop을 할 때에는 q2가 이미 반전되어 있기 때문에 q2에서 그대로 pop하면 된다.
 */

// 0ms, 17.9MB
class MyStack {
    var q1 = MyQueue()
    var q2 = MyQueue()

    init() {
        
    }
    
    func push(_ x: Int) {
        q1.push(x)
        
        while !q2.empty() {
            q1.push(q2.pop())
        }
        
        var temp = q1
        q1 = q2
        q2 = temp
    }
    
    func pop() -> Int {
        q2.pop()
    }
    
    func top() -> Int {
        q2.top()
    }
    
    func empty() -> Bool {
        q2.empty()
    }
}

class MyQueue {
    var data = [Int]()
    
    func push(_ x: Int) {
        data.append(x)
    }
    
    func pop() -> Int {
        if empty() {
            return 0
        }
        return data.removeFirst()
    }
    
    func empty() -> Bool {
        return data.isEmpty
    }
    
    func top() -> Int {
        guard let first = data.first else { return 0 }
        return first
    }
}

//: [Next](@next)
