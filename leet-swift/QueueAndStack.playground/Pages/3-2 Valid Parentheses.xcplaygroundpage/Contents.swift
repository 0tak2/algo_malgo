//: [Previous](@previous)

import Foundation

// Valid Parentheses
// https://leetcode.com/explore/learn/card/queue-stack/230/usage-stack/1361/

// 0ms, 18MB
class Solution {
    let table: [Character: Character] = [
        ")": "(",
        "]": "[",
        "}": "{"
    ]
    
    func isValid(_ s: String) -> Bool {
        guard s.count >= 2 else { return false }
        
        var st: [Character] = []
        for c in s {
            if c == "(" || c == "[" || c == "{" {
                st.append(c)
            } else {
                if st.isEmpty {
                    return false
                }
                
                let popped = st.removeLast()
                if popped != table[c]! {
                    return false
                }
            }
        }
        
        return st.isEmpty
    }
}

class SolutionSample {
    func isValid(_ s: String) -> Bool {
        if
            s.isEmpty,
            s.count % 2 != 0,
            s.count > 10000
        {
            return false
        }

        var stack = [Character]()

        for character in s {
            switch character {
                case "(": stack.append(")")
                case "{": stack.append("}")
                case "[": stack.append("]")
            default:
                if stack.isEmpty || stack.removeLast() != character {
                    return false
                }
            }
        }

        return stack.isEmpty
    }
}

//: [Next](@next)
