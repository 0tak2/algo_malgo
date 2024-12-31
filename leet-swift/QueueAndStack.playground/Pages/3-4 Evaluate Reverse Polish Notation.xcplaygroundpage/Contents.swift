//: [Previous](@previous)

import Foundation

// Evaluate Reverse Polish Notation
// https://leetcode.com/explore/learn/card/queue-stack/230/usage-stack/1394/
/**
 ### 분석
 후위 표기법으로 숫자와 연산자가 입력되면, 적절하게 계산하는 문제이다. 전통적인 스택 문제이다.
 */

// 0ms, 17.7MB
class Solution {
    func evalRPN(_ tokens: [String]) -> Int {
        var st = [Int]()
        
        for t in tokens {
            switch t {
            case "+":
                let b = st.removeLast()
                let a = st.removeLast()
                st.append(a + b)
            case "-":
                let b = st.removeLast()
                let a = st.removeLast()
                st.append(a - b)
            case "*":
                let b = st.removeLast()
                let a = st.removeLast()
                st.append(a * b)
            case "/":
                let b = st.removeLast()
                let a = st.removeLast()
                st.append(a / b)
            default: // 숫자로 간주
                st.append(Int(t)!)
            }
        }
        
        return st.last!
    }
}

//: [Next](@next)
