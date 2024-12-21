//: [Previous](@previous)

import Foundation

// 206. Reverse Linked List (Grind75 #2-5)
// https://leetcode.com/problems/reverse-linked-list
// Learn - Linked List에 이미 등장했던 문제

func reverseList(_ head: ListNode?) -> ListNode? {
    guard let head, let headNext = head.next else { return head }
    let newHead = reverseList(headNext)

    headNext.next = head
    head.next = nil

    return newHead
}

//: [Next](@next)
