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

public class Node {
    public var val: Int
    public var left: Node?
    public var right: Node?
    public var next: Node?
    public init(_ val: Int) {
        self.val = val
        self.left = nil
        self.right = nil
        self.next = nil
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

// MARK: - Populating Next Right Pointers in Each Node
// https://leetcode.com/explore/learn/card/data-structure-tree/133/conclusion/994/

// MARK: - Populating Next Right Pointers in Each Node II
// https://leetcode.com/explore/learn/card/data-structure-tree/133/conclusion/1016/
// 같은 레벨의 노드들을 왼쪽부터 next 프로퍼티를 통해 묶어내는 문제이다.
// I, II 모두 이 풇이로 가능하다. I은 퍼펙트 힙이고, II는 아니다. I의 경우 최적화가 가능한 것 같다.

// TODO: I 빠르게 풀기

// I: 26ms, 18.9MB
// II: 11ms, 18.8MB
func connect(_ root: Node?) -> Node? {
    guard let root = root else { return nil }
    var q = [(Node, Int)]()
    q.append((root, 1))
    
    var currentLevel = 1
    var previousNode: Node? = nil
    while !q.isEmpty {
        let (node, level) = q.removeFirst()
        // print(node.val)
        // print(level)
        // print()
        
        if let previousNode = previousNode,
           level == currentLevel {
            previousNode.next = node
        }
        
        currentLevel = level
        previousNode = node
        
        if let left = node.left {
            q.append((left, level + 1))
            
        }
        
        if let right = node.right {
            q.append((right, level + 1))
        }
    }
    
    return root
}

// MARK: - Lowest Common Ancestor of a Binary Tree
// https://leetcode.com/explore/learn/card/data-structure-tree/133/conclusion/932/
// 어떤 트리의 루트 노드와, 특정 노드 p, q가 주어졌을 때 p와 q의 LCA인 노드를 찾는다
// TODO: 헷갈린 문제 -- 다시 풀어보기

/**
 첫 루트에서 자식으로 내려가면서 LCA인지 판단한다
 재귀적으로 생각해볼 수 있다.
 */

// 28ms, 19.2MB
func lowestCommonAncestor(_ root: TreeNode?, _ p: TreeNode?, _ q: TreeNode?) -> TreeNode? {
    if root == nil || root === p || root === q {
        // root가 nil이면 리프까지 내려왔지만 찾지 못한 것
        // root가 p나 q와 동일한 경우 그 자신이 LCA
        return root
    }
    
    let leftLCA = lowestCommonAncestor(root?.left, p, q) // 현재 노드 왼쪽 서브트리에서 p를 찾는다
    let rightLCA = lowestCommonAncestor(root?.right, p, q) // 현재 노드 왼쪽 서브트리에서 q를 찾는다
    if leftLCA !== nil && rightLCA !== nil { // 둘이 서브트리 각각에 따로 있다면 여기가 LCA이다
        return root
    }
    
    return leftLCA ?? rightLCA // 한쪽이 nil이면 다른 한 쪽이 LCA이다
}

// MARK: - Serialize and Deserialize Binary Tree
// 이진 트리를 leetcode 스타일의 배열로 직렬화하거나 그로부터 역직렬화하는 클래스를 구현하는 문제이다
// BFS로 레벨 순회를 하도록 되어있다
// https://leetcode.com/explore/learn/card/data-structure-tree/133/conclusion/995/

// 41ms, 20.4MB
class Codec {
    func serialize(_ root: TreeNode?) -> String {
        let prefix = "["
        let postfix = "]"
        
        guard let root = root else { return "[]" }
        var q = [TreeNode?]()
        q.append(root)
        
        var traverse = [Int?]()
        
        while !q.isEmpty {
            let node = q.removeFirst()
            traverse.append(node?.val) // 일단 현재 노드가 nil인 경우에도 순회 배열에 넣는다
            
            if node != nil { // 현재 노드가 nil 이면 자식을 스택에 넣지 않는다
                q.append(node?.left)
                q.append(node?.right)
            }
        }
        
        while let last = traverse.last,
              last == nil {
            traverse.removeLast() // nil인 리프는 지운다.
        }
        
        let content = traverse.map { $0 == nil ? "null" : String($0!) }
            .joined(separator: ",")
        
        return "\(prefix)\(content)\(postfix)"
    }
    
    func deserialize(_ data: String) -> TreeNode? {
        guard data != "[]" else { return nil }
        
        let rangeStart = data.index(data.startIndex, offsetBy: 1) // https://developer.apple.com/documentation/swift/string/index(_:offsetby:)
        let rangeEnd = data.index(data.endIndex, offsetBy: -1)
        let content = data[rangeStart..<rangeEnd]
        
        // https://developer.apple.com/documentation/swift/string/split(separator:maxsplits:omittingemptysubsequences:)
        var traversal = content.split(separator: ",")
            .map { $0 == "null" ? nil : Int($0) }
        
        let root = TreeNode(traversal[0]!)
        
        var q: [TreeNode] = [root]
        var i = 1
        
        while !q.isEmpty && i < traversal.count {
            let node = q.removeFirst()
            
            if let leftVal = traversal[i] {
                let leftNode = TreeNode(leftVal)
                node.left = leftNode
                q.append(leftNode)
            }
            i += 1
            
            if i >= traversal.count { break }
            
            if let rightVal = traversal[i] {
                let rightNode = TreeNode(rightVal)
                node.right = rightNode
                q.append(rightNode)
            }
            i += 1
        }
        
        return root
    }
}

// Your Codec object will be instantiated and called as such:
// var ser = Codec()
// var deser = Codec()
// deser.deserialize(ser.serialize(root))

//: [Next](@next)
