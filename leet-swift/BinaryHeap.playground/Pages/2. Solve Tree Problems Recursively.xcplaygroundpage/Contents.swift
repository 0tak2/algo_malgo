//: [Previous](@previous)

import Foundation

// MARK: - Maximum Depth of Binary Tree
// 가장 깊이가 깊은 노드의 레벨을 구한다

// 첫 시도: BFS (레벨 순회)
// 0ms, 20.1MB
func maxDepth(_ root: TreeNode?) -> Int {
    guard let root = root else { return 0 }
    
    var q = [(TreeNode, Int)]()
    q.append((root, 1))
    var maxDepth = 1
    
    while !q.isEmpty {
        let (node, level) = q.removeFirst()
        
        maxDepth = max(maxDepth, level)
        
        if let left = node.left {
            q.append((left, level + 1))
        }
        
        if let right = node.right {
            q.append((right, level + 1))
        }
    }
    
    return maxDepth
}

// 리트코드 예시 코드
// 너무 가독성이 좋다. 이 챕터 주제 자체가 재귀적으로 풀어보기라서 여기 문제들은 재귀함수를 고려해보면 좋을 것 같다.
// 위와 같이 큐를 이용한 BFS와 성능을 비교하면 어떻게 될까?
// 0ms, 20.2MB -> 실행 시간과 점유 메모리는 우선 비슷하다. 어짜피 돌기는 다 돌아야 답을 낼 수 있기 때문에 여러 케이스에 대한 평균 성능은 비슷할 것.
func maxDepth(_ root: TreeNode?) -> Int {
    guard let root = root else { return 0 }
    
    let leftDepth = maxDepth(root.left)
    let rightDepth = maxDepth(root.right)
    
    return max(leftDepth, rightDepth) + 1
}

//: [Next](@next)
