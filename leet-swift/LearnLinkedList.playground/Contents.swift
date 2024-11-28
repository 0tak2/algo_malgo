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
    public init(_ val: Int, _ next: ListNode) {
        self.val = val
        self.next = next
    }
}

// for debug
extension ListNode {
    func printAll() -> Void {
        var curr: ListNode? = self
        while let el = curr {
            print(el.val, terminator: "")
            curr = el.next
            if curr != nil {
                print(" -> ", terminator: "")
            }
        }
        print()
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

// MARK: - 3. Classic Problems

// MARK: Reverse Linked List
/**
 https://leetcode.com/explore/learn/card/linked-list/219/classic-problems/1205/
 
 ### 분석
 주어진 링크드리스트를 뒤집어 새로운 head를 반환하면 된다.
 문제 말미에 반복문으로도, 재귀함수로도 구현할 수 있다고 되어있다.
 
 ### 접근
 - 차례대로 순회하면서 다음을 반복한다.
   1. 현재 원소의 next를 이전 원소로 지정한다.
   2. 현재 원소를 원래 다음 원소로, 이전 원소를 현재 원소로 업데이트한다.
 
 ### 재귀함수로 고쳐보기
 - 도져히 모르겠어서 샘플 답변을 참고했다.
 - 노드를 인자로 받는 함수 reverseList에 시작 노드를 넘겨 호출하면,
   1. 해당 노드, 그 다음 노드가 모두 nil이 아닌지 확인
   2. 모두 nil이 아니라면 (아직 마지막 노드에 도달하지 않은 상태)
     (1) reverseList에 현재 노드를 넘겨 재귀 호출. 반환값 newHead를 잘 보관 (newHead는 마지막 노드 - 3단계를 보면 이해가 됨)
     (2) 다음 노드의 next를 현재 노드로, 현재 노드의 next를 nil로 설정하고, newHead를 반환
   3. 둘 중 하나가 nil이라면 (재귀호출이 거듭된 상태로, 마지막 노드에 도달한 상태
     - 그대로 현재 노드를 반환 (즉, 마지막 노드가 반환됨)
     - 이 반환 값이 이전 스택 프레임의 newHead가 되고, 이 값은 스택 프레임이 팝될 때마다 반환되어 최초 스택 프레임의 반환 값이 됨.
 */

// 0ms, 17.8MB
func reverseList(_ head: ListNode?) -> ListNode? {
    var cur: ListNode? = head
    var prv: ListNode? = nil
    
    while let el = cur {
        let nextEl: ListNode? = cur?.next
        el.next = prv
        prv = cur
        cur = nextEl
    }
    
    return prv
}

func reverseListRecursive(_ head: ListNode?) -> ListNode? {
    guard let head, let headNext = head.next else { return head }
    let newHead = reverseListRecursive(headNext)

    headNext.next = head
    head.next = nil

    return newHead
}

let result1 = reverseList(ListNode(
    1,
    ListNode(
        2,
        ListNode(
            3,
            ListNode(
                4,
                ListNode(5)
            )
        )
    )
))
result1?.printAll()

let result2 = reverseListRecursive(ListNode(
    1,
    ListNode(
        2,
        ListNode(
            3,
            ListNode(
                4,
                ListNode(5)
            )
        )
    )
))
result2?.printAll()

// MARK: Remove Linked List Elements
/**
 https://leetcode.com/explore/learn/card/linked-list/219/classic-problems/1207/
 
 ### 분석
 리스트에서 val이 주어진 값과 같다면 모두 제거하고 head를 반환하면 된다.
 
 ### 접근
 1. 역시 이전 노드를 기억하면서 1칸씩 탐색
 2. 현재 노드의 val이 주어진 값과 같으면, 이전 노드의 next를 현재 노드가 아닌 현재 노드의 next로 지정
 
 - 첫 노드부터 삭제해야하는 경우, 이전 노드가 없으므로 head 자체를 현재 노드로 옮겨줘야 한다. 이 부분 처리에서 애를 조금 먹었다.
 - 재귀함수로 고치려면?
 */

// 0ms, 18MB
func removeElements(_ head: ListNode?, _ val: Int) -> ListNode? {
    var cur: ListNode? = head
    var prv: ListNode? = nil
    var newHead: ListNode? = head
    
    while let el = cur {
        if el.val == val {
            prv?.next = el.next
            cur = el.next
            
            if prv == nil { // 이전 노드가 없는 경우 head를 당겨준다
                newHead = cur
            }
            
            continue
        }
        
        prv = el
        cur = el.next
    }
    
    return newHead
}

// MARK: - Odd Even Linked List
/**
 https://leetcode.com/explore/learn/card/linked-list/219/classic-problems/1208/
 
 ### 분석
 리스트 각각 노드에 대하여, (index + 1)이 홀수인 노드를 앞으로, 짝수인 노드를 뒤로 재배치하도록 하는 문제이다.
 
 ### 접근
 1. 홀수 인덱스 노드와 짝수 인덱스 노드 각각을 가리키는 커서를 따로 둔다.
 2. 리스트를 순회하면서, 현재 인덱스를 계산하고, 인덱스가 홀수면 홀수 노드 커서의 next에 현재 노드를 연결한다. 짝수면 짝수 노드 커서의 next에 연결한다.
 
 - 아이디어 자체는 간단했고 코드에도 오류가 없었는데 처음 답안을 제출했을 때 시간초과가 발생했다.
 - 마지막 노드의 next가 앞선 다른 노드를 가리키게 되면서 순환 리스트가 되어, leetcode에서 답안을 판정하면서 시간초과가 난 것이었다.
 */

// 0ms, 18.3MB
func oddEvenList(_ head: ListNode?) -> ListNode? {
    guard let head = head else { return nil }
    
    if head.next == nil { // 요소가 한 개인 리스트
        return head
    }
    
    var oddCur: ListNode? = head
    var evenCur: ListNode? = head.next
    let evenStart: ListNode = evenCur!
    
    if evenCur?.next == nil { // 요소가 딱 두 개인 리스트
        return head
    }
    
    var cur: ListNode? = evenCur!.next // 세 번째 노드를 할당해두고 시작
    oddCur!.next = nil // 1, 2번 노드의 next는 끊어둠
    evenCur!.next = nil
    var count: Int = 2
    while let node = cur {
        count += 1
        if count % 2 == 1 { // odd
            oddCur!.next = node
            oddCur = node
            // print("odd node: \(node.val)")
        } else { // even
            evenCur!.next = node
            evenCur = node
            // print("even node: \(node.val)")
        }
        
        cur = node.next
        node.next = nil // 원래 next는 끊어준다.
    }
    
    oddCur!.next = evenStart
    
    return head
}

let result3 = oddEvenList(ListNode(
    1,
    ListNode(
        2,
        ListNode(
            3,
            ListNode(
                4,
                ListNode(5)
            )
        )
    )
))
result3?.printAll()

let result4 = oddEvenList(ListNode(
    1,
    ListNode(
        2,
        ListNode(3)
    )
))
result4?.printAll()

// MARK: - Palindrome Linked List
/**
 https://leetcode.com/explore/learn/card/linked-list/219/classic-problems/1209/
 
 ### 분석
 링크드리스트가 회문인지 확인하는 문제이다. 거꾸로 읽든 똑바로 읽든 동일한가?
 문제 말미에  시간복잡도 O(n), 공간복잡도 O(1)로 해결 가능한지 묻고 있다.
 
 ### 처음 접근
 - 단방향 리스트이기 때문에 단순히 투포인터를 쓸 수는 없다.
 - 각 노드를 순회하면서 하나의 문자열에 concat 하고, 문자열을 뒤집었을 때 원래 값과 동일한지를 검색하는 방식으로 해결했다.
 - 이렇게 되면 우선 속도가 너무 느리며, Swift에서 문자열은 메모리를 가변적으로 사용하므로 공간복잡도가 O(n)이 된다는 한계가 있다.
 
 ### 다른 풀이들을 보고 다시 풀이
 - 성능이 빠른 풀이들도 배열에 값들을 넣어두고, 이 배열에 대해 투포인터로 확인하는 방식들이 대부분이었다.
 - 결국 공간복잡도는 O(n)이 된다. (전체 리스트 노드를 임시 배열에 모두 넣기 때문에 인풋 수만큼 임시 배열의 길이가 길어진다.)
 - 어쨌든 이런 식으로도 풀어보았다.
 */

// 1. String
// 123ms, 23.8MB
func isPalindromeSlow(_ head: ListNode?) -> Bool {
    var number: String = ""
    
    var cur: ListNode? = head
    while let node = cur {
        number += String(node.val)
        cur = node.next
    }
    
    return number == String(number.reversed())
}

// 2. Array
// 21ms, 23.8MB
func isPalindrome(_ head: ListNode?) -> Bool {
    var numbers: [Int8] = [] // 조건에 `0 <= Node.val <= 9`가 있으므로 8비트 정수 범위에 들어간다.
    
    var cur: ListNode? = head
    while let node = cur {
        numbers.append(Int8(node.val))
        cur = node.next
    }
    
    var left: Int = 0
    var right: Int = numbers.count - 1
    while left < right {
        if numbers[left] != numbers[right] {
            return false
        }
        left += 1
        right -= 1
    }
    
    return true
}

let result5 = isPalindrome(ListNode(
    1,
    ListNode(
        2,
        ListNode(
            2,
            ListNode(1)
        )
    )
))
print(result5)
