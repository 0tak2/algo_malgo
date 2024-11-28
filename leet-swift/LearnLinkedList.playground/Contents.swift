import Swift

var greeting = "Hello, playground"

// MARK: - Linked List
/**
 [Introduction to Data Structure - Linked List](https://leetcode.com/explore/learn/card/linked-list/)
 */

// MARK: - 2. Two Pointer Technique

// MARK: Linked List Cycle
// https://leetcode.com/explore/learn/card/linked-list/214/two-pointer-technique/1212/
// 이미 Grind 75에서 풀었던 문제

public class ListNode {
    public var val: Int
    public var next: ListNode?
    public init(_ val: Int) {
        self.val = val
        self.next = nil
    }
}

// 25ms, 16MB
func hasCycleOriginal(_ head: ListNode?) -> Bool {
    guard let head = head else {
        return false
    }
    
    var slow: ListNode? = head
    var fast: ListNode? = head.next
    
    while let p1 = slow, let p2 = fast {
        if p1 === p2 {
            return true
        }
        
        slow = p1.next
        
        fast = p2.next
        if let oneStepNextOfFast = fast {
            fast = oneStepNextOfFast.next
        } else {
            return false
        }
    }
    
    return false
}

// 29ms, 15.8MB
func hasCycleRefactor(_ head: ListNode?) -> Bool {
    guard let head = head else {
        return false
    }
    
    var slow: ListNode? = head
    var fast: ListNode? = head // 둘 다 head에서 시작
    
    while fast != nil && fast?.next != nil { // fast만 조사하면 충분하다.
        fast = fast?.next?.next
        slow = slow?.next
        
        if slow === fast {
            return true
        }
    }
    
    return false
}

// MARK: Linked List Cycle II
/**
 https://leetcode.com/explore/learn/card/linked-list/214/two-pointer-technique/1214/
 
 ## 분석
 이전 문제와 비슷한데, 사이클이 시작되는 노드를 반환하도록 함
 
 ## 접근
 모르겠어서 찾아봤음
 https://www.geeksforgeeks.org/find-first-node-of-loop-in-a-linked-list/
 
 ## 방법
 1. 이전 문제처럼 사이클이 있는지 판별한다
 2. 사이클이 있으면, slow 포인터를 head로 변경한다.
 3. 두 포인터 각각을 한 칸씩 전진시키면서 최초로 만나는 지점이 사이클이 시작되는 노드가 된다
 
 ## 이유
 - 다음 두 거리가 같기 때문이다...
   - fast와 slow가 처음 만난 지점부터, 사이클이 시작되는 지점까지의 거리와
   - head부터 사이클이 시작되는 지점까지의 거리
 - 따져보기: https://chatgpt.com/share/6747ab4f-4afc-800a-91dc-a19677067f17
 
 */

// 23ms, 15.8MB
func hasCycle2(_ head: ListNode?) -> (Bool, ListNode?, ListNode?) {
    var slow: ListNode? = head
    var fast: ListNode? = head
    
    while fast != nil && fast?.next != nil {
        slow = slow?.next
        fast = fast?.next?.next
        
        if slow === fast {
            return (true, slow, fast)
        }
    }
    
    return (false, slow, fast)
}

func detectCycle(_ head: ListNode?) -> ListNode? {
    var (cycle, p1, p2) = hasCycle2(head)
    
    if !cycle {
        return nil
    }
    
    p1 = head
    while p1 != nil && p2 != nil {
        if p1 === p2 {
            return p1
        }
        
        p1 = p1!.next
        p2 = p2!.next
    }
    
    return nil
}

// MARK: Intersection of Two Linked Lists
/**
 https://leetcode.com/explore/learn/card/linked-list/214/two-pointer-technique/1215/
 
 ## 분석
 주어진 리스트의 요소들에 대해 교집합이 있는지 판별하고, 교집합이 시작되는 노드를 반환
 문제 말비에 시간복잡도 O(m + n), 공간복잡도 O(1)의 답안을 요구하고 있음
 
 ## 처음 접근
 - 이중 루프를 써서 해결하는 방법은 쉽게 떠올릴 수 있었음.
     ```
     a = headA
     b = haedB
     while a is valid node
         while b is valid node
             b = headB
             if a and b refers same node
                 return true
             b = b.next
         a = a.next
     return false
     ```
 - 결과: 시간초과
 
 ## 해시테이블
 1. A 리스트를 돌면서 테이블에 각 노드를 추가한다.
 2. B 리스트를 돌면서 테이블에 있는 노드인지 확인한다.
 
 - 공간복잡도 조건 O(1)은 맞추지 못한다.
 
 ## 투 포인터
 - 시간복잡도 O(m + n), 공간복잡도 O(1) 방법은 도저히 모르겠어서 찾아봤다. https://www.geeksforgeeks.org/write-a-function-to-get-the-intersection-point-of-two-linked-lists/
 - 이 방법은 공통되는 서브리스트가 있다면, 그 길이가 같다는 점에서 착안한다.
 
 1. 두 리스트의 길이를 각각 센다. 이 값들을 lenA, lenB라고 하자.
 2. diff = | lenA - lenB |를 계산헤둔다.
 3. 각 리스트를 순회할 커서를 잡고, 더 긴 리스트의 커서는 diff만큼 당겨놓는다.
 4. 각 커서를 1씩 증가시키면서 요소를 비교한다. 그럼 공통되는 서브리스트가 있을 경우 어느 순간 겹치게 될 것이다. => 최초로 발견되는 공통 요소가 시작 노드이다.
 */

// 1. 처음 접근
// Time Limit Exceeded
func getIntersectionNodeSlow(_ headA: ListNode?, _ headB: ListNode?) -> ListNode? {
    var p1: ListNode? = headA
    var p2: ListNode? = headB
    
    while p1 != nil {
        p2 = headB
        while p2 != nil {
            if p1 === p2 {
                return p1
            }
            p2 = p2?.next
        }
        p1 = p1?.next
    }
    
    return nil
}


// 2. 해시테이블
// 112ms, 17.9MB

/**
 스위프트에서는 딕셔너리의 키로 구조체나 클래스를 사용하려면 해당 타입이 Hashable 프로토콜을 따라야한다.
 see: [Hashable](https://developer.apple.com/documentation/swift/hashable)
 
 클래스의 레퍼런스를 기준으로 해시값을 만들려면 ObjectIdentifier 구조체를 활용한다.
 ref: https://stackoverflow.com/a/34705912
 also see: [ObjectIdentifier](https://developer.apple.com/documentation/swift/objectidentifier)
 */
extension ListNode: Hashable {
    public static func == (lhs: ListNode, rhs: ListNode) -> Bool {
        return lhs.hashValue == rhs.hashValue
    }
    
    public func hash(into hasher: inout Hasher) {
        hasher.combine(ObjectIdentifier(self))
    }
}

func getIntersectionNodeWithTable(_ headA: ListNode?, _ headB: ListNode?) -> ListNode? {
    var p1: ListNode? = headA
    var visited: [ListNode: Bool] = [:]
    
    var p2: ListNode? = headB
    
    while let el = p1 {
        visited[el] = true
        p1 = el.next
    }
    
    while let el = p2 {
        if let visited = visited[el], visited {
            return el
        }
        p2 = el.next
    }
    
    return nil
}


// 3. 투 포인터
// 106ms, 17.2MB
// 생각보다 메모리 사용량이 크게 줄어들지는 않았다...

extension ListNode {
    var count: Int {
        var curr: ListNode? = self
        var count: Int = 0
        
        while let node = curr {
            count += 1
            curr = node.next
        }
        
        return count
    }
}

func getIntersectionNode(_ headA: ListNode?, _ headB: ListNode?) -> ListNode? {
    guard let headA = headA, let headB = headB else {
        print("invalid inputs")
        return nil
    }
    
    // compute diff
    var countA: Int = headA.count
    var countB: Int = headB.count
    var diff: Int = countA > countB ? countA - countB : countB - countA
    
    // adjust cursor
    var cur1: ListNode?
    var cur2: ListNode?
    
    if countA > countB {
        cur1 = headA
        cur2 = headB
        
        for i in 0..<diff {
            cur1 = cur1?.next
        }
    } else {
        cur1 = headA
        cur2 = headB
        
        for i in 0..<diff {
            cur2 = cur2?.next
        }
    }
    
    // find intersection
    while let el1 = cur1, let el2 = cur2 {
        if el1 === el2 {
            return el1
        }
        
        cur1 = cur1?.next
        cur2 = cur2?.next
    }
    
    return nil
}

// MARK: Remove Nth Node From End of List
/**
 https://leetcode.com/explore/learn/card/linked-list/214/two-pointer-technique/1296/
 
 ## 분석
 주어진 리스트의 끝에서 N번째 요소를 제거하면 된다. 문제 단서로 "Follow up: Could you do this in one pass?" 조건이 붙어있다.
 
 ## 접근
 1. 리스트의 길이를 센다. count라고 하자.
 2. 리스트를 앞에서부터 다시 순회한다. i번 요소라면, 뒤에서부터 count-i번 요소라고 할 수 있다.
 3. count-i == n <=> i == count-n이라면, 삭제한다.
 */

/*
 // redeclation
 extension ListNode {
     var count: Int {
         var count: Int = 0
 
         var cur: ListNode? = self
         while let el = cur {
             count += 1
             cur = el.next
         }
 
         return count
     }
 }
*/

// 0ms, 17.8 MB
func removeNthFromEnd(_ head: ListNode?, _ n: Int) -> ListNode? {
    guard let head = head else { return nil }
    
    var prevCur: ListNode? = nil
    var cur: ListNode? = head
    
    if head.count == 1 && n == 1 { // 길이가 1인 리스트에서 뒤에서 1번째 요소를 삭제하는 경우
        return nil
    }
    
    for i in 0..<head.count-n {
        prevCur = cur
        cur = cur?.next
    }
    
    if prevCur == nil { // 첫 번째 요소를 삭제하는 경우
        return cur?.next
    }
    
    guard let prevEl = prevCur, let el = cur else { return nil }
    // print("prevEl=\(prevEl.val) el=\(el.val)")
    prevEl.next = el.next
    
    return head
}

// MARK: - 3.Classic Problems
// TODO: 여기서부터 진행
