//: [Previous](@previous)

import Foundation

// Binary Tree Inorder Traversal
// https://leetcode.com/explore/learn/card/queue-stack/232/practical-application-stack/1383/
/**
 ### 분석
 트리가 주어지면, 해당 트리를 중위 순회하는 순서를 반환하는 문제이다.
 중위순회란, 특정 노드 root의 자식이 left와 right일 때, left -> root -> right의 순서대로 탐색하는 방법을 말한다.
 재귀함수보다는 반복문을 사용해보라고 되어있다.
 
 ### 접근
 Solution을 참고했다. 재귀함수를 사용하는 방법과 스택을 이용하는 방법을 모두 다룬다.
 재귀함수를 이용하는 방법은 이해가 쉬웠다.
 스택을 이용하는 경우도 코드만 봤을 떄는 이해하기 어려운 구석이 있지만, 재귀함수 코드를 스택으로 변환했다고 생각하니 이해는 됐다. (아마도...)
 */

// LeetCode 상에서 컴파일 타임 아웃.
// Compile timed out. Please retry again. If this error keeps occurring, please report it to us.
// TLE도 아니고 아예 컴파일에 실패했다. 로컬에서는 잘 된다.
class SolutionRecursive {
    func doJob(_ root: TreeNode?, _ result: inout [Int]) {
        if let root = root {
            doJob(root.left, &result)
            result.append(root.val)
            doJob(root.right, &result)
        }
    }
    
    func inorderTraversal(_ root: TreeNode?) -> [Int] {
        var result = [Int]()
        
        doJob(root, &result)
        
        return result
    }
}

// 0ms, 17.7 MB
class Solution {
    func inorderTraversal(_ root: TreeNode?) -> [Int] {
        guard let root = root else { return [] }
        
        var result = [Int]()
        
        var st: [TreeNode] = []
        var curr: TreeNode? = root
        while curr != nil || !st.isEmpty {
            while let left = curr {
                st.append(left)
                curr = left.left
            }
            
            let lastLeft = st.removeLast()
            result.append(lastLeft.val)
            curr = lastLeft.right
        }
        
        return result
    }
}


// Definition for a binary tree node.
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


// Test
let solution = Solution()
let result = solution.inorderTraversal(TreeNode(1,
                                   nil,
                                   TreeNode(2,
                                            TreeNode(3),
                                            nil)
                                  ))
print(result)

//: [Next](@next)
