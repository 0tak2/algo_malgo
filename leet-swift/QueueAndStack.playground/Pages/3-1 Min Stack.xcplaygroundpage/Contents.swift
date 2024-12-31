//: [Previous](@previous)

import Foundation

// Min Stack
// https://leetcode.com/explore/learn/card/queue-stack/230/usage-stack/1360/
/**
 ### 분석
 스택을 구현하되, 현재 스택의 최솟값을 반환하는 메서드 getMin도 구현해야 한다.
 
 ### 접근
 힌트를 참고했다. "Consider each node in the stack having a minimum value."
 push할 때 추가할 값 뿐 아니라 추가하기 전 시점의 최소값을 같이 추가하도록 했다.
 나중에 pop을 할 때 해당 값이 최솟값이면 그 노드에 같이 보관해둔 이전 최솟값을 현재 최솟값으로 재할당해둔다.
 
 ### 고민
 공간복잡도가 많이 늘어났는데, 전체 범위에서 하위권이다. 최적화할 수 있는지 따져봐야 한다.
 */

// 6ms, 18.8MB
class MinStack {
    private var data: [Node]
    private var minVal: Int = Int.max
    
    init() {
        data = []
    }
    
    func push(_ val: Int) {
        let node = Node(val: val, prevMin: minVal)
        
        if val < minVal {
            minVal = val
        }
        
        data.append(node)
    }
    
    func pop() {
        let popped = data.removeLast()
        if popped.val == minVal {
            minVal = popped.prevMin
        }
    }
    
    func top() -> Int {
        data.last!.val
    }
    
    func getMin() -> Int {
        return minVal
    }
    
    struct Node {
        let val: Int
        let prevMin: Int
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * let obj = MinStack()
 * obj.push(val)
 * obj.pop()
 * let ret_3: Int = obj.top()
 * let ret_4: Int = obj.getMin()
 */

let st = MinStack()
st.push(0)
st.push(1)
st.push(0)
print(st.getMin())
st.pop()
print(st.getMin())

//: [Next](@next)
