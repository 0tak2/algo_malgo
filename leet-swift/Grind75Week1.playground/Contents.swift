import Swift

// MARK: - 235. Lowest Common Ancestor of a Binary Search Tree (Grind75 #10)
// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree
/**
 # 문제 분석
 이진 검색 트리와, 해당 트리의 특정 두 노드가 주어질 때, lowest common ancestor (LCA)를 찾는 문제
 이진 검색 트리 T의 두 노드 p, q의 LCA는 p, q 모두를 자손으로 갖는 가장 낮은 T의 노드로 정의된다.
 
 # 처음 접근 (Leet235Slow)
 1. root에서 시작해 각각 p, q를 탐색하면서 경로를 기억해둔다. 순서를 가지는 정점들의 리스트면 적당할 것 같다.
 2. 두 경로를 비교하면서, 공통 정점이면서 각 리스트에서 가장 늦게 등장하는 정점을 반환한다.
 -> 구현하기는 쉬웠지만 속도가 매우 느렸다. 57ms, Beats 5.19%
 
 # 개선 1 (Leet235Enhanced1)
 p, q 각 정점에 대해 따로 따로 경로를 찾아갈 필요가 있을까?
 LCA를 찾는 작업인 만큼 상당한 정점을 공유하므로, 한 번 순회할 때 p, q를 같이 찾아볼 수 있을 것 같다.
 
 1. root에서 시작해 자손 노드 각각을 순회한다.
 2. p, q 중 하나를 먼저 찾으면 그 경로를 기억해둔다.
 3. 다음을 찾으면 그 경로를 기억해둔다.
 4. 두 경로를 비교하면서, LCA를 찾는다.
 
 -> 살짝 나아졌지만 여전히 비슷한 성능이 나온다. 54ms, Beats 11.04%
 
 # 개선 2 (Leet235Enhanced2)
 위와 같이 단순히 탐색 방식의 문제가 아님을 확인했다. -> 이진 검색 트리가 주어진다는 것에 시선을 돌렸다.
 이진 검색 트리에서 조상과 두 자식의 대소관계에 집중해보면, child1 < parent < child2의 관계가 성립한다.
 즉, 무조건 완전 탐색할 필요가 없고, p와 q의 값에 따라서 탐색하면 된다.
 
 1. root에서 시작해 각 노드와 p, q간의 대소관계를 확인한다.
   1-1. 노드가 p, q보다 모두 작으면, 노드의 오른쪽 자식(현재 노드보다 무조건 크다)을 다음 노드로 하여 다시 비교한다.
   1-2. 노드가 p, q보다 모두 크면, 노드의 왼쪽 자식(현재 노드보다 무조건 작다)을 다음 노드로 하여 다시 비교한다.
   1-3. 모든 노드의 값은 서로 다르기 때문에, 위 두 경우에 해당하지 않으면, p < node < q 또는 q < node < p를 만족한다. -> 이 노드를 반환한다.
 
 -> 성능이 많이 향상되었고, (48ms, Beats 78.57%) 코드가 간소화되었다.
 탐색할 때부터 많이 최적화되었고, 이전 풀이의 경로 비교가 없어지면서 효율화되었다.
 */

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

func logArray(_ arr: [TreeNode]) -> String {
    var str = ""
    for node in arr {
        str += String(node.val) + " "
    }
    return str
}


/**
 235. Lowest Common Ancestor of a Binary Search Tree (Grind75 #10)
 느린 풀이
 
 p, q를 각각 찾으며 경로를 기록하고, 경로 상에서 LCA를 찾아낸다.
 
 57ms, 16.44MB
 */
class Leet235Slow {
    private func findTarget(parent: TreeNode, for target: TreeNode, memo route: [TreeNode]) -> [TreeNode] {
//        print("findTarget parent=\(parent.val) for=\(target.val)")
        if let child = parent.left {
            var leftRoute: [TreeNode] = route
            leftRoute.append(child)
            
            if (child.val == target.val) {
//                print("found: left", child.val, target.val)
                return leftRoute
            }
            
            let resultLeft = findTarget(parent: child, for: target, memo: leftRoute)
            if resultLeft.count > 0 {
//                print("return left side: \(logArray(resultLeft))")
                return resultLeft
            }
        }
        
        if let child = parent.right {
            var rightRoute: [TreeNode] = route
            rightRoute.append(child)
            
            if (child.val == target.val) {
//                print("found: right", child.val, target.val)
                return rightRoute
            }
            
            let resultRight = findTarget(parent: child, for: target, memo: rightRoute)
            if resultRight.count > 0 {
//                print("return right side: \(logArray(resultRight))")
                return resultRight
            }
        }
        
        return []
    }
    
    private func startFind(from root: TreeNode, for target: TreeNode) -> [TreeNode] {
        var route: [TreeNode] = []
        route.append(root)
        
        if root.val == target.val {
//            print("Route for TreeNode(\(target.val)): \(logArray(route))")
            return route
        }
        
        let foundRoute: [TreeNode] = findTarget(parent: root, for: target, memo: route)
//        print("Route for TreeNode(\(target.val)): \(logArray(foundRoute))")
        return foundRoute
    }
    
    func lowestCommonAncestor(_ root: TreeNode?, _ p: TreeNode?, _ q: TreeNode?) -> TreeNode? {
        guard let root, let p, let q else { return nil }
        
//        print("========== p ==========")
        let pRoute: [TreeNode] = startFind(from: root, for: p)
//        print("========== q ==========")
        let qRoute: [TreeNode] = startFind(from: root, for: q)
        
        for pNode in pRoute.reversed() {
//            print("pNode: \(pNode.val)")
            for qNode in qRoute.reversed() {
//                print("qNode: \(qNode.val)")
                if pNode.val == qNode.val {
                    return pNode
                }
            }
        }
        
        return nil
    }
}

/**
 235. Lowest Common Ancestor of a Binary Search Tree (Grind75 #10)
 개선 1
 
 p, q를 같이 찾으며 경로를 기록하고, 경로 상에서 LCA를 찾아낸다.
 
 54ms, 16.50MB
 */
class Leet235Enhanced1 {
    private var found1: Bool = false
    private var found2: Bool = false
    
    private var route1: [TreeNode] = []
    private var route2: [TreeNode] = []
    
    private func find(p: TreeNode, t1: TreeNode, t2: TreeNode, prev: [TreeNode]) -> Void {
        var newRoute: [TreeNode];
        if let left = p.left {
            newRoute = prev
            newRoute.append(left)
            
            if !found1 && left.val == t1.val {
//                print("fonud: left: t1: \(t1.val)")
                found1 = true
                route1 = newRoute
            }
            
            if !found2 && left.val == t2.val {
//                print("fonud: left: t2: \(t2.val)")
                route2 = newRoute
            }
            
            if found1 && found2 {
                return
            }
            
            find(p: left, t1: t1, t2: t2, prev: newRoute)
        }
        
        if let right = p.right {
            newRoute = prev
            newRoute.append(right)
            
            if !found1 && right.val == t1.val {
//                print("fonud: right: t1: \(t1.val)")
                found1 = true
                route1 = newRoute
            }
            
            if !found2 && right.val == t2.val {
//                print("fonud: right: t2: \(t2.val)")
                found2 = true
                route2 = newRoute
            }
            
            if found1 && found2 {
                return
            }
            
            find(p: right, t1: t1, t2: t2, prev: newRoute)
        }
    }
    
    func lowestCommonAncestor(_ root: TreeNode?, _ p: TreeNode?, _ q: TreeNode?) -> TreeNode? {
        guard let root, let p, let q else { return nil }
        
        if root.val == p.val {
            route1 = [root]
            found1 = true
        }
        
        if root.val == q.val {
            route2 = [root]
            found2 = true
        }
        
        find(p: root, t1: p, t2: q, prev: [root])
        
//        print(logArray(route1))
//        print(logArray(route2))
        
        for n1 in route1.reversed() {
//            print("n1: \(n1.val)")
            for n2 in route2.reversed() {
//                print("n2: \(qNode.val)")
                if n1.val == n2.val {
                    return n1
                }
            }
        }
        
        return nil
    }
}

/**
 235. Lowest Common Ancestor of a Binary Search Tree (Grind75 #10)
 개선 2
 
 p, q와 그 조상의 대소관계에 근거해서 노드를 찾아본다
 
 48ms, 36.91MB
 */
class Leet235Enhanced2 {
    func lowestCommonAncestor(_ root: TreeNode?, _ p: TreeNode?, _ q: TreeNode?) -> TreeNode? {
        guard let n = root, let p, let q else { return nil }
        
        if n.val < p.val && n.val < q.val {
            return lowestCommonAncestor(n.right, p, q)
        } else if n.val > p.val && n.val > q.val {
            return lowestCommonAncestor(n.left, p, q)
        }
        
        return n
    }
}

// MARK: Test Case 1, 2
var root: TreeNode = TreeNode(
    6,
    TreeNode(
        2,
        TreeNode(0),
        TreeNode(
            4,
            TreeNode(3),
            TreeNode(5)
        )
    ),
    TreeNode(
        8,
        TreeNode(7),
        TreeNode(9)
    )
)

var sol1 = Leet235Enhanced2()
let answer1: TreeNode? = sol1.lowestCommonAncestor(root, TreeNode(2), TreeNode(8))
print(answer1!.val)

var sol2 = Leet235Enhanced2()
let answer2: TreeNode? = sol2.lowestCommonAncestor(root, TreeNode(2), TreeNode(4))
print(answer2!.val)

// MARK: Wrond Case 1
var sol2_1 = Leet235Enhanced2()
let answer2_1: TreeNode? = sol2_1.lowestCommonAncestor(root, TreeNode(3), TreeNode(5))
print(answer2_1!.val)

// MARK: Test Case 3
root = TreeNode(
    2,
    TreeNode(1),
    nil
)

var sol3 = Leet235Enhanced2()
let answer3: TreeNode? = sol3.lowestCommonAncestor(root, TreeNode(2), TreeNode(1))
print(answer3!.val)

// MARK: - 110. Balanced Binary Tree (Grind75 #11)
// https://leetcode.com/problems/balanced-binary-tree
/**
 # 문제 분석
 주어진 이진 트리의 높이 균형(height-balanced)이 맞는지 판별하면 되는 문제이다.
 어떤 이진 트리가 높이 균형이 맞다는 것은, 해당 트리의 모든 노드에 대해, 왼쪽/오른쪽 자식 트리(subtrees)의 높이 차이가 1이하인 경우를 말한다.
 
 # 접근
 1. 루트 노드부터 순회하면서 왼쪽, 오른쪽 노드 각각에 대해 깊이(높이)를 센다.
 2. 깊이를 셀 때에는 왼쪽, 오른쪽 각 자식 노드부터 leaf 노드까지의 깊이를 센 후, 최댓값을 반환하면 된다. -> 재귀의 꼴이 됨
 
 -> 이렇게 되면 이중 루프와 다름 없어지게 된다 (isBalanced >> countDepth)
 다만 성능 상 나쁘지 않게 나왔다. (0ms, Beats 100%; 흠 좀 이상하다--너무 잘 나옴)
 시간이 되면 최적화해보면 좋을 것 같다.
 */

// 0ms, 18.24MB
class Leet110 {
    func countDepth(_ parent: TreeNode?, _ prevDepth: Int) -> Int {
        guard let node = parent else { return prevDepth }
        
        let leftDepth: Int
        let rightDepth: Int

        if let left = node.left {
            leftDepth = countDepth(left, prevDepth + 1)
        } else {
            leftDepth = prevDepth
        }

        if let right = node.right {
            rightDepth = countDepth(right, prevDepth + 1)
        } else {
            rightDepth = prevDepth
        }

        return max(leftDepth, rightDepth)
    }

    func isBalanced(_ root: TreeNode?) -> Bool {
        guard let node = root else { return true }

        let leftDepth: Int
        let rightDepth: Int

        if let left = node.left {
            leftDepth = countDepth(node.left, 1)
        } else {
            leftDepth = 0
        }

        if let right = node.right {
            rightDepth = countDepth(node.right, 1)
        } else {
            rightDepth = 0
        }

        return abs(leftDepth - rightDepth) <= 1 && isBalanced(node.left) && isBalanced(node.right)
    }
}

// MARK: - 141. Linked List Cycle (Grind75 #12)
// https://leetcode.com/problems/linked-list-cycle
/**
 # 문제 분석
 링크드 리스트가 주어지면, 해당 리스트가 순환하는지 여부를 판단하면 되는 문제이다.
 문제 말미에 단서로, 공간복잡도를 O(1)로 풀 수 있는 방법을 요구하고 있다.
 
 # 처음 접근 (Leet141)
 탐색할 때 마다, 현재 노드의 next를 수정하는 방법을 생각하였다. 예컨대 모든 노드의 next를 head(첫번째 노드)로 설정하는 것이다.
 next가 head일 경우는 인위적으로 조작한 것이 아니라면, 존재하지 않는다.
 
 1. 루트 노드부터 순회한다. 반복문의 조건은 노드가 nil이 아닌 경우 실행되도록 설정한다. (while node != nil)
    1-1. next가 head인지 검사한다.
    1-2. 맞다면 true를 반환한다.
    1-3. 아니라면 next를 head로 설정한 뒤, 실제 next로 노드를 지정한 후 다음 루프로 넘어간다.
 
 -> 그러나 이 방법은 원본 리스트를 수정한다는 문제가 있다. 단순 코딩테스트라면 문제가 없겠지만, 인터뷰 문제라면 공격이 있을 수 있겠다는 생각이 들었다.
   실제로 이 코드를 사용하려면, 실행하기 전에 원본 배열을 복사해둬야할 것이다.
 
 # Floyd’s Cycle Detection Algorithm (Leet141_floyd)
 찾아보니 순환 감지(Cycle Detection)은 CS에서 종종 다뤄지는 토픽으로 보이고, 플로이드의 알고리즘이 유명했다.
 - 플로이드의 알고리즘은 포인터 두 개를 이용해 리스트를 순회하는데, 포인터별로 순회 속도가 다르다.
 - 포인터 한 개는 요소를 두 개씩 순회하고, 다른 포인터는 요소를 한 개씩 순회한다.
 - 만약
    - 주어진 리스트가 순환 리스트가 아니라면, 빠른 포인터 먼저 nil를 가리키게 된다.
    - 순환 리스트라면, 언젠가 두 포인터는 같은 요소를 가리키게 된다.
 
 */

public class ListNode {
    public var val: Int
    public var next: ListNode?
    public init(_ val: Int) {
        self.val = val
        self.next = nil
    }
}

// 25ms, 16.58MB
class Leet141 {
   func hasCycle(_ head: ListNode?) -> Bool {
       guard let head else { return false }

       var node: ListNode? = head
       while let cur = node {
           if let next = cur.next {
               if next === head {
                   return true
               }
               
               let nextNode: ListNode? = next
               cur.next = head
               node = nextNode
           } else {
               node = nil
           }
       }

       return false
   }
}

// 25ms, 16.33MB
class Leet141_floyd {
    func hasCycle(_ head: ListNode?) -> Bool {
        guard let head else { return false }
        
        var fast: ListNode?
        var slow: ListNode? = head
        
        // fast에는 두 스텝 앞의 노드를 할당해둔다
        // TODO: 한 스텝 앞을 할당해도 상관 없을 것 같다. 성능 상의 차이가 생길까?
        if head.next == nil {
            return false
        }
        
        if let next = head.next, let nextNext = next.next {
            fast = nextNext
        } else {
            return false
        }
        
        while let node1 = fast, let node2 = slow {
            if node1 === node2 {
                return true
            }
            
            if let oneStepNext = node1.next, let twoStepNext = oneStepNext.next {
                fast = twoStepNext
            } else {
                fast = node1.next
            }
            
            slow = node2.next
        }
        
        return false
    }
}

// MARK: - 232. Implement Queue using Stacks (Grind75 #13)
// https://leetcode.com/problems/implement-queue-using-stacks
/**
 ## 분석
 두 개의 스택을 이용해 FIFO 큐를 만들어야 한다.
 
 ## 접근
 스택을 두 개 이용하면 순서를 반전시킬 수 있다. 예컨대 1, 2, 3을 순차적으로 큐에 넣고 빼는 작업을 두 스택 st1, st2를 이용해 수행한다면,
 
 우선 st1에 값을 넣는다.
 
 ```
 st1.offer(1); st1.offer(2); st1.offer(3);
 // 구분           front                        tail
 // st1           1               2               3
 // st2                        [empty]
 ```
 
 
 st1로부터 모든 값을 pop하고 직후에 st2에 넣는다.
 
 ```
 st2.offer(st.pop()); st2.offer(st.pop()); st2.offer(st.pop());
 // 구분          front                        tail
 // st1                       [empty]
 // st2          3               2               1
 ```
 
 st2에 대해 pop을 수행하면 FIFO로 값을 뺄 수 있다.
 
 ## 고민
 다만 push와 pop이라는 연산이 일괄적으로 순차 수행되는게 아니라, 각각 분리되어 수행될 수 있다는 것을 고민해야 한다.
 이 경우에도 하나의 스택(st1)을 push 전용으로 사용하고, 다른 스택(st2)을 pop 전용으로 사용한다고 하면 순서가 보장되는 것을 확인할 수 있었다.
 단 st1에서 st2로 순서를 뒤집어 넣어주는 작업이 pop이나 peek 연산 전, st2가 빈 경우에만 선행되면 된다. (아래 구현 내용에서 MyQueue::transform의 호출 위치 참고)
 */

// 0ms, 17.70 MB

// 우선 스택을 구현한다.
class MyStack {
    private var data: [Int] = []

    init() {}

    func offer(_ x: Int) {
        data.append(x)
    }

    func pop() -> Int {
        return data.removeLast()
    }

    func peek() -> Int {
        return data[data.count - 1]
    }

    func empty() -> Bool {
        return data.isEmpty
    }
}

class MyQueue {
    private var st1: MyStack = MyStack()
    private var st2: MyStack = MyStack()
    
    init() {
        
    }
    
    func push(_ x: Int) {
        st1.offer(x)
    }
    
    func pop() -> Int {
        if !st2.empty() {
            return st2.pop()
        }
        
        // st2가 비어있으면 st1에서 모두 pop하여 추가
        transfer()
        
        return st2.pop()
    }
    
    func peek() -> Int {
        if !st2.empty() {
            return st2.peek()
        }
        
        // st2가 비어있으면 st1에서 모두 pop하여 추가
        transfer()
        
        return st2.peek()
    }
    
    func empty() -> Bool {
        return st1.empty() && st2.empty()
    }
    
    private func transfer() {
        while !st1.empty() {
            st2.offer(st1.pop())
        }
    }
}

print("==== 232 DEBUG ====")
let q = MyQueue()
q.push(1)
q.push(2)
q.push(3)
print(q.empty())
print(q.pop())
q.push(4)
print(q.pop())
q.push(5)
print(q.pop())
q.push(6)
print(q.empty())
print(q.pop())
print(q.pop())
print(q.pop())
print(q.empty())
/*
 false
 1
 2
 3
 false
 4
 5
 6
 true
 */
