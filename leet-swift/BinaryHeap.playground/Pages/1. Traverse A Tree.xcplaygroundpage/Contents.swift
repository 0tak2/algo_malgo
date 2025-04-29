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
// https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/928/
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

//: [Next](@next)
