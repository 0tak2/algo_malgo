//: [Previous](@previous)

import Foundation

// Decode String
// https://leetcode.com/explore/learn/card/queue-stack/239/conclusion/1379/
/**
 ### 분석
 특정한 방식으로 인코딩된 문자열을 적절하게 디코딩하는 것이 요구사항이다.
 반복되는 문자열을 반복횟수[문자열]과 같은 형태로 줄여놓았다.
 [, ]안에는 또 다른 축약형이 중첩될 수 있다.
 
 ### 구현
 스택을 사용하면 되겠다는 아이디어는 떠올랐는데 구체적인 방법을 생각하기가 어려워서 답안을 참고했다. [참고자료](https://walkccc.me/LeetCode/problems/394/)
 
 까다로운 지점
 스택에 넣을 시점은 "["를 발견했을 때인데, 이때에는 반복 횟수는 알 수 있어도, 어떤 문자열을 반복해야하는 지는 아직 알 수 없다.
 반복할 문자열은 "]"를 발견한 시점에서 알 수 있다.
 
 따라서 닫힐 때("]") 문자열 연산을 해야할 수밖에 없고, 이를 위해 열릴 때{"[") 스택에 현재까지 작업해둔 문자열과 해당 시점에서 알 수 있는 정보인 반복 횟루를 넣어둔다.
 
 구체적으로는 다음과 같다.
 1. "["가 나오면 스택에 이전까지의 작업했던 문자열과 반복 횟수를 추가한다.
 2. 기존의 작업용 문자열 변수는 ""으로 초기화하고, "]"가 나올 때까지 작업용 문자열 변수에 문자들을 추가한다.
 3. "]"가 나오면 스택에서 pop해서 이전 문자열에 2에서 추가해뒀던 문자들을 반복 횟수만큼 반복해 현재 작업용 변수에 할당한다.
 
 으 머리깨진다...
 */

// 0ms, 17.8MB
class Solution {
    func decodeString(_ s: String) -> String {
        var st = [Token]()
        
        var currentRepeatCount = 0
        var workingStr = ""
        for c in s {
            if c >= "0" && c <= "9" {
                // 기존 숫자를 높은 자리로 올리고 새로운 숫자를 더한다
                currentRepeatCount = currentRepeatCount * 10 + Int(String(c))!
            }
            
            if c == "[" {
                // 현재 시점의 문자열과 반복 수를 같이 보관
                let newToken = Token(prevStr: workingStr, repeatCount: currentRepeatCount)
                
                st.append(newToken)
                
                // 작업용 변수 다시 초기화
                workingStr = ""
                currentRepeatCount = 0
            }
            
            if c == "]" {
                let last = st.removeLast()
                workingStr = last.prevStr + workingStr * last.repeatCount
//                print("prevStr \(last.prevStr) repeatCount \(last.repeatCount) workingStr \(workingStr)")
            }
            
            if c >= "a" && c <= "z" {
                workingStr += String(c)
            }
        }
        
        return workingStr
    }
    
    struct Token {
        let prevStr: String
        let repeatCount: Int
    }
}

extension String {
    static func * (lhs: String, rhs: Int) -> String {
        var result = ""
        for _ in 0..<rhs {
            result += lhs
        }
        return result
    }
}

let solution = Solution()

// Test 1
let result1 = solution.decodeString("3[a]2[bc]")
assert(result1 == "aaabcbc")

// Test 2
let result2 = solution.decodeString("3[a2[c]]")
assert(result2 == "accaccacc")

// Test 3
let result3 = solution.decodeString("2[abc]3[cd]ef")
assert(result3 == "abcabccdcdcdef")


//: [Next](@next)
