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

// Binary Tree Preorder Traversal
// https://leetcode.com/explore/lean/card/data-structure-tree/134/traverse-a-tree/928/
// 0ms, 19.8MB
class BinaryTreePreorderTraversalSolution {
    func preorderTraversal(_ root: TreeNode?) -> [Int] {
        guard let root = root else { return [] }
        
        var result = [Int]()
        var stack: [TreeNode] = [root]
        
        while !stack.isEmpty {
            let pop = stack.removeLast()
            result.append(pop.val)
            
            if let right = pop.right {
                stack.append(right) // 오른쪽 노드를 먼저 넣는다. 나중에 빠지도록.
            }
            
            if let left = pop.left {
                stack.append(left) // 왼쪽 노드를 뒤에 넣는다. 먼저 빠지도록.
            }
        }
        
        return result
    }
}

// Binary Tree Inorder Traversal
// https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/929/
// 할 때마다 어렵다...
// 0ms, 19.6MB
class BinaryTreeInorderTraversalSolution {
    func inorderTraversal(_ root: TreeNode?) -> [Int] {
        var result = [Int]()
        var stack = [TreeNode]()
        var current = root

        while current != nil || !stack.isEmpty {
            // 왼쪽 노드 끝까지 탐색
            while let node = current {
                stack.append(node)
                current = node.left
            }
            
            // 왼쪽 끝에 도달했으면 pop 후 처리
            let node = stack.removeLast()
            result.append(node.val)
            
            // 오른쪽 서브트리로 이동
            current = node.right
        }

        return result
    }
}

let root1 = TreeNode(1,
                     nil,
                     TreeNode(2,
                              TreeNode(3),
                              nil))
let test1 = BinaryTreeInorderTraversalSolution().inorderTraversal(root1) // [1, 3, 2]
print(test1)

// Binary Tree Postorder Traversal
// https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/930/
// L, R을 바꿔서 순회하고 전체 방문 리스트를 뒤집는다
// 0ms, 19.7MB
class BinaryTreePostorderTraversalSolution {
    func postorderTraversal(_ root: TreeNode?) -> [Int] {
        guard let root = root else { return [] }
        
        var result = [Int]()
        var stack: [TreeNode] = [root]
        
        while !stack.isEmpty {
            let node = stack.removeLast()
            result.append(node.val)
            
            if let left = node.left {
                stack.append(left)
            }
            
            if let right = node.right {
                stack.append(right)
            }
        }
        
        return result.reversed()
    }
}

//: [Next](@next)
