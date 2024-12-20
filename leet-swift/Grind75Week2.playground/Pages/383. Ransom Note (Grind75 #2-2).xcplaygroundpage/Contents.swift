//: [Previous](@previous)

import Foundation

// MARK: - 383. Ransom Note (Grind75 #2-2)
// https://leetcode.com/problems/ransom-note
/**
 ### 분석
 영문 소문자 알파벳으로 구성된 두 개의 문자열 ransomNote, magazine이 주어진다. magazine를 이루는 글자를 이용해 ransomNote를 만들 수 있는지 여부를 판단한다.
 magazine를 이루는 글자는 한 번씩만 사용할 수 있다.
 
 ### 접근
 1. 먼저 magazine을 이루는 글자들을 순회하면서 빈도수 테이블을 만들어둔다.
 2. ransomNote를 이루는 글자들을 순회하면서 빈도수 테이블에서 해당 글자의 빈도를 찾아본다. nil이거나 0이면 바로 false를 반환한다.
 3. 모든 글자를 순회했으면 true를 반환한다.
 
 - 시간이 느린 편이다. 더 개선할 수 있는지 고민 필요.
 -> 샘플 풀이들을 살펴보니 딕셔너리 대신 배열로 빈도수를 표현하는 예제가 있었다. 어짜피 소문자 알파벳이므로 26개짜리 정수 배열을 쓰면 된다
   다만 스위프트에서 문자열을 다루는게 까다롭기 때문에 실제 인터뷰에서 쓸만한 풀이인지는 잘 모르겠다...
 
 */

// 30ms, 17.69 MB
func canConstruct(_ ransomNote: String, _ magazine: String) -> Bool {
    var freq: [Character: Int] = [:]

    for c in magazine {
        freq[c, default: 0] += 1
    }
    
    for c in ransomNote {
        if let fr = freq[c], fr > 0 {
            freq[c]! -= 1
        } else {
            return false
        }
    }
    
    return true
}

func canConstructSample(_ ransomNote: String, _ magazine: String) -> Bool {
    var record = [Int](repeating: 0, count: 26)
    for char in magazine.unicodeScalars {
        record[Int(char.value - "a".unicodeScalars.first!.value)] += 1
    }
    for char in ransomNote.unicodeScalars {
        record[Int(char.value - "a".unicodeScalars.first!.value)] -= 1
    }
    for r in record {
        if r < 0 {
            return false
        }
    }
    return true
}


//: [Next](@next)
