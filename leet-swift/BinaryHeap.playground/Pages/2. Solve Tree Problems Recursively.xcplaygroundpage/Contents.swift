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

// MARK: - Symmetric Tree
// 트리가 좌우 대칭인지 판별하는 문제이다
// 좌우 대칭을 로직으로 어떻게 판별할지 고민하다가 웹을 참고했다
// https://www.geeksforgeeks.org/symmetric-tree-tree-which-is-mirror-image-of-itself/
// 재귀 자체를 짜는 것은 어렵지 않았지만, 다음 아이디어를 떠올리는 것이 어려웠다.
/**
 return checkSymmetric(left?.left, right?.right) // 왼쪽 자식의 왼쪽 자식과 오른쪽 자식의 오른쪽 자식 비교
        && checkSymmetric(left?.right, right?.left) // 반대 비교
 */
// 0ms, 19.8MB
class SymmetricTreeSolution {
    func checkSymmetric(_ left: TreeNode?, _ right: TreeNode?) -> Bool {
        // 끝에 도달하는 경우 판별한다
        if left == nil && right == nil {
            return true
        }
        
        if left == nil || right == nil || left?.val != right?.val {
            return false
        }
        
        // 아직 아니라면 재귀호출한다
        return checkSymmetric(left?.left, right?.right) // 왼쪽 자식의 왼쪽 자식과 오른쪽 자식의 오른쪽 자식 비교
               && checkSymmetric(left?.right, right?.left) // 반대 비교
    }
    
    func isSymmetric(_ root: TreeNode?) -> Bool {
        guard let root = root else { return false }
        return checkSymmetric(root.left, root.right)
    }
}
//: [Next](@next)
