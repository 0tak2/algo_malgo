//: [Previous](@previous)

import Foundation

// 409. Longest Palindrome (Grind75 #2-4)
// https://leetcode.com/problems/longest-palindrome
/**
 ### 분석
 문자열 s가 주어지면, s를 이루는 문자로 만들 수 있는 가장 긴 Palindrome의 길이를 반환한다.
 s는 영어 대소문자로 이뤄지고, a-z와 A-Z는 다른 문자로 취급한다.
 
 ### 접근
 어떤 문자열이 Palindrome이라는 것은 다음 두 가지 경우로 나뉜다.
 1. 전체 길이가 홀수 -> 문자 하나는 홀수 개 있고, 나머지 문자는 짝수 개가 있는 경우 (중앙에 축이 되는 문자가 하나 있고, 나머지 문자들은 대칭을 이룸)
 2. 전체 길이가 짝수 -> 모든 문자가 짝수 개씩 있는 경우 (모든 문자가 대칭을 이룸)
 
 그렇다면 문자열을 문자 단위로 순회하면서 전체 개수를 세고, 센 결과를 토대로 위와 같이 검사하면서 조절하면 되지 않을까?
 
 ### 구현 `Solution1`
 처음에는 위의 접근 그대로 구현을 시작했는데, 잘 되지 않았다. -> 결국 Editorial이 무료여서 참고
 
 아티클에서는 위와 유사하게 접근하되, 꼭 문자열 길이가 짝/홀에 따라 분기하지 않아도 되는 새로운 아이디어를 제시한다.
 전체 길이 상관 없이, 문자열 쌍이 한 개 있으면 완성될 Palindrome의 길이가 2만큼 늘어나고, 그렇지 않은 경우 완성될 Palindrome의 길이에 합산하지 않는 것이다.
 이 과정에서 홀수인 경우가 한 번이라도 있었으면 나중에 1을 더해 반환한다. -> 홀수인 문자는 한 번에 한해 붙을 수 있기 때문에.
 
 ### Set을 활용 `Solution2`
 Editorial에는 다른 방식도 있었는데 그 중 직관적이고 간단했던 방법으로 Set을 활용한 방법이 있었다.
 freq table을 만들지 않고 Set을 이용해서 짝이 있다는 것만 확인해 계산하는 방식이다.
 시간복잡도는 Big-O로는 여전히 O(n)이지만, freq table을 만들고 다시 순회하는 위의 방법보다는 일반적으로 빠를 것이다.
 -> 실제로는 오히려 더 느린 결과가 나왔다
 
 */

// 1ms, 18.14MB
class Solution1 {
    func longestPalindrome(_ s: String) -> Int {
        var count: [Character: Int] = [:]

        for c in s {
            count[c] = count[c, default: 0] + 1
        }
        
        var ret = 0
        var oddAppeared = false
        for (_, cnt) in count {
            if cnt % 2 == 0 { // 짝수 번 등장한 문자 -> 모두 짝이 맞음
                ret += cnt
            } else { // 홀수 번 등장한 문자 -> 짝을 맞추면 문자 한 개가 남게 됨
                ret += cnt - 1
                oddAppeared = true
            }
        }
        
        // 결국 ret는 페어를 이룰 수 있는 문자들의 개수 총합이 되며, 홀수인 문자가 한 번이라도 있었다면 한 글자 추가 가능
        return oddAppeared ? ret + 1 : ret
    }
}

// 5ms, 17.91MB
class Solution2 {
    func longestPalindrome(_ s: String) -> Int {
        var set: Set<Character> = Set()

        var ret = 0

        for c in s {
            if set.contains(c) {
                ret += 2
                set.remove(c)
            } else {
                set.insert(c)
            }
        }

        // Set이 비어있지 않다는 것은 짝이 되지 않은 문자가 있다는 것. 문자 한 개가 추가될 수 있다.
        return set.isEmpty ? ret : ret + 1
    }
}

extension Int {
    func printValue() {
        print(self)
    }
}
Solution2().longestPalindrome("helloworld").printValue()
Solution2().longestPalindrome("abccccdd").printValue()
Solution2().longestPalindrome("accccdd").printValue()

//: [Next](@next)
