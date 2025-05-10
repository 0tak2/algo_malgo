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

// MARK: - Construct Binary Tree from Inorder and Postorder Traversal
// https://leetcode.com/explore/learn/card/data-structure-tree/133/conclusion/942/
// 어떤 이진 트리를 중위순회한 결과와 후위순회한 결과가 주어졌을 때, 원래 이진 트리를 반환하면 된다.

/**
 도저히 모르겠어서 검색했다.
 https://algo.monster/liteproblems/106
 
 핵심 힌트
 후위순회 결과의 마지막 값은 루트 노드의 값이다 -> 후위순회 배열 마지막 요소를 통해 루트 노드를 알 수 있다.
 루트 노드를 알면, 중위순회 배열로부터 루트노드의 왼쪽과 오른쪽 자식들을 알 수 있다.
 
 방법
 1. postorder 배열에서 마지막 값을 찾는다 -> 루트 노드
 2. inorder 배열과 postorder 배열에서 루트 노드 값을 기준으로 왼쪽과 오른쪽을 파티션한다. postorder 배열의 파티셔닝이 조금 까다롭다. 코드 참고.
 3. postoreder 배열에서 파티션된 값들을 찾고, 각 파티션에서 재귀적으로 이 작업을 수행한다
 */

// 18ms, 90.2MB
// 조금 느린 편이다. 다른 제출 코드를 보니 맵을 통해 최적화했다.
func buildTree(inorder: [Int], postorder: [Int]) -> TreeNode? {
    guard !postorder.isEmpty else {
        return nil
    }
    
    let rootVal = postorder.last!
    let root = TreeNode(rootVal)
    
    guard let sliceIndex = inorder.lastIndex(of: rootVal),
          sliceIndex >= 0 && sliceIndex < inorder.count else {
        return nil
    }
    
    let inorderLeft = inorder[0..<sliceIndex]
    let inorderRight = inorder[sliceIndex+1..<inorder.endIndex]
    let postorderLeft = postorder[0..<sliceIndex]
    let postorderRight = postorder[sliceIndex..<postorder.endIndex-1] // sliceIndex는 inorder 기준이므로 1을 더하지 않고 바로 다음노드부터 자른다. 마지막 원소가 기준 노드이므로 제외한다.
//    print("inorderLeft: \(inorderLeft)")
//    print("inorderRight: \(inorderRight)")
//    print("postorderLeft: \(postorderLeft)")
//    print("postorderRight: \(postorderRight)")
//    print("====")
    
    root.left = buildTree(Array(inorderLeft), Array(postorderLeft))
    root.right = buildTree(Array(inorderRight), Array(postorderRight))
    
    return root
}

let input1 = TreeNode(
    3,
    TreeNode(9),
    TreeNode(
        20,
        TreeNode(15),
        TreeNode(7)
    )
)
let answer1 = buildTree(inorder: [9, 3, 15, 20, 7], postorder: [9, 15, 7, 20, 3])
dump(answer1)

// MARK: - Construct Binary Tree from Preorder and Inorder Traversal
// https://leetcode.com/explore/learn/card/data-structure-tree/133/conclusion/943/
// 앞선 문제와 거의 유사하다. 다만 전위순회와 중위순회 배열이 주어진다는 점이 다르다.

/**
 이전 문제와 비슷한 접근 방식을 따라보려고 한다.
 - 전위순회 배열의 첫 요소가 루트 노드이다.
 - 그럼 중위순회 배열에서 왼쪽 자식 배열과 오른쪽 자식 배열을 알아낼 수 있다
 - 이전 코드에서 슬라이스만 조금 바꾸면 될 것 같다.
 */

// 22ms, 90.8MB
func buildTree(_ preorder: [Int], _ inorder: [Int]) -> TreeNode? {
    guard !preorder.isEmpty else {
        return nil
    }
    
    let rootVal = preorder.first!
    let root = TreeNode(rootVal)
    
    guard let sliceIndex = inorder.lastIndex(of: rootVal),
          sliceIndex >= 0 && sliceIndex < inorder.count else {
        return nil
    }
    
    let inorderLeft = inorder[0..<sliceIndex]
    let inorderRight = inorder[sliceIndex+1..<inorder.endIndex]
    let preorderLeft = preorder[1..<sliceIndex+1] // 0번 요소는 기준 노드이므로 포함하지 않는다. sliceIndex는 기준 노드이므로 다음 노드까지 자른다.
    let preorderRight = preorder[sliceIndex+1..<preorder.endIndex]
//    print("inorderLeft: \(inorderLeft)")
//    print("inorderRight: \(inorderRight)")
//    print("preorderLeft: \(preorderLeft)")
//    print("preorderRight: \(preorderRight)")
//    print("====")
    
    root.left = buildTree(Array(preorderLeft), Array(inorderLeft))
    root.right = buildTree(Array(preorderRight), Array(inorderRight))
    
    return root
}

//: [Next](@next)
