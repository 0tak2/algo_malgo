//: [Previous](@previous)

import Foundation

public class TreeNode {
    public var val: Int
    public var left: TreeNode?
    public var right: TreeNode?
    public init() { self.val = 0; self.left = nil; self.right = nil; }
    public init(_ val: Int) { self.val = val; self.left = nil; self.right = nil; }
    public init(_ val: Int, _ left: TreeNode?, _ right: TreeNode?) {
        self.val = val
        self.left = left
        self.right = right
    }
}

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
func maxDepthExample(_ root: TreeNode?) -> Int {
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

// MARK: - Path Sum
// https://leetcode.com/explore/learn/card/data-structure-tree/17/solve-problems-recursively/537/
// 루트에서 리프까지 더했을 때 targetSum일 수 있는지 판별하는 문제
// 잔실수로 헤매다가 마지막에 ChatGPT 힘을 빌렸다
// 0ms, 19.7MB
class PathSumSolution {
    func sum(_ node: TreeNode?, _ targetSum: Int, _ accumSum: Int) -> Bool {
        // 여기서 판별하면 leaft가 아니라 그저 끊긴 노드일 때도 비교하기 때문에 안된다.
        // guard let node = node else {
        //     return targetSum == accumSum
        // }
        // 아래처럼 그냥 false를 반환하는 것으로 변경
        guard let node = node else {
            return false
        }
        
        // 미리 판별하는 조건문을 넣었었지만 문제 조건에서 노드의 값이 음수일 수도 있으므로 제외한다.
        // if accumSum + node.val > targetSum {
        //     return false
        // }
        
        let currentAccumSum = accumSum + node.val
        if node.left == nil && node.right == nil {
            return targetSum == currentAccumSum
        }
        
        return sum(node.left, targetSum, currentAccumSum) || sum(node.right, targetSum, currentAccumSum)
    }
    
    func hasPathSum(_ root: TreeNode?, _ targetSum: Int) -> Bool {
        guard let root = root else { return false }
        return sum(root, targetSum, 0)
    }
}

//: [Next](@next)
