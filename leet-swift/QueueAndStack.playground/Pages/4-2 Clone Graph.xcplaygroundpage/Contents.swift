//: [Previous](@previous)

import Foundation

// Clone Graph
// https://leetcode.com/explore/learn/card/queue-stack/232/practical-application-stack/1392/
/**
 ### 분석
 그래프를 deep copy하는 문제이다. 이때 노드끼리 양방향 매핑된 것을 그대로 복사해야한다.
 노드의 val은 유니크한 값이기 때문에 id처럼 이용할 수 있을 것 같다.
 
 ### 접근
 전체 노드를 방문하는 것 자체는 단순히 DFS로 가능하다. 다만 노드끼리 서로 양방향으로 연결되기 때문에
 일반적인 구현처럼 방문 배열에 노드를 넣어서 한 번씩 방문하도록 하면 적절하게 복사가 안된다.
 단방향 연결까지는 복사되어도 양방향 연결이 누락될 것이다.
 
 따라서 방문 배열에 [출발 노드, 도착 노드]를 넣어 단방향 간선 자체를 표현할 수 있도록 했다.
 [2, 3]과 [3, 2]를 다른 방문 기록으로 취급해 결과적으로 양방향 연결이 된다.
 
 ### 고민
 특정 val(id처럼 이용)의 노드에 대한 레퍼런스를 딕셔너리(해시맵)로 보관해두었다. 방문 배열과 함께 O(n)의
 공간복잡도를 가지는 자료구조가 늘어나게 된 것인데 줄일 수 있는 방법은 없었는지 모르겠다.
 */

// 52ms, 16.6MB
class Solution {
    func cloneGraph(_ node: Node?) -> Node? {
        guard let head = node else { return nil }
        
        let copiedHead = Node(head.val)
        var copiedNodes: [Int: Node] = [copiedHead.val: copiedHead]
        
        var st = [head]
        var visited = [[Int]]() // [node1, node2]
        while !st.isEmpty {
            let original = st.removeLast()
            let copied = copiedNodes[original.val]!
            // print("visited \(copied.val)")
            
            for n in original.neighbors {
                guard let n = n else { continue }
                
                guard !visited.contains([original.val, n.val]) else {
                    continue
                }
                
                if copiedNodes[n.val] == nil {
                    copiedNodes[n.val] = Node(n.val)
                }
                
                let copiedNeighbor = copiedNodes[n.val]!
                copied.neighbors.append(copiedNeighbor)
                // print("appended \(copied.val) -> \(copiedNeighbor.val)")
                
                st.append(n)
                
                visited.append([original.val, n.val])
            }
        }
        
        return copiedHead
    }
}

/**
 Definition for a Node.
 */
public class Node {
    public var val: Int
    public var neighbors: [Node?]
    public init(_ val: Int) {
        self.val = val
        self.neighbors = []
    }
}

//: [Next](@next)
