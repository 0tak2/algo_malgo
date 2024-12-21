//: [Previous](@previous)

import Foundation

// 169. Majority Element (Grind75 #2-6)
// https://leetcode.com/problems/majority-element
/**
 ### 분석
 숫자들의 배열이 주어진다. 숫자들은 중복될 수 있으며, 특정 숫자가 과반수보다 많이 존재한다는 것이 보장된다.
 The majority element is the element that appears more than ⌊n / 2⌋ times
 과반수 숫자가 무엇인지 반환하면 된다.
 
 이때 시간복잡도는 선형(`O(T * n)`의 꼴?)으로, 공간복잡도는 `O(1)`로 할 수 있냐는 단서가 붙어있다.
 
 ### 처음 접근
 처음에는 빈도 테이블을 만들어 간단하게 구현했지만, 이 경우 공간복잡도는 최대 O(n)이 된다.
 
 ### [보이어-무어의 과반수 투표 알고리즘](https://en.wikipedia.org/wiki/Boyer–Moore_majority_vote_algorithm)
 이미 이 문제를 해결하기 위한 알고리즘이 80년대 나왔다. 이 문제의 조건이 해당 알고리즘의 조건과 완전히 동일하다.
 
 수도 코드로 써보면 다음과 같다.
 ```
 init int majority = 0
 init int count = 0
 for x in nums:
    if count == 0, majority = x
    if x == majority, count++
    else count--
 return majority
 ```
 
 - 이 알고리즘은 우선 처음 등장하는 수를 후보로 잡고, count를 0부터 센다.
    - 동일한 수가 다음에 등장하면 count를 1 증가시키고,
      다른 수가 다음에 등장하면 count를 1 감소시킨다.
    - 만약 count가 0이 됐으면 현재 가리키는 수를 새로운 후보로 잡고 계속 진행한다.
 - 최종 후보를 반환한다.
 
 아주 간단하지만 증명이 필요 없을 정도로 직관적이다. 어떤 숫자의 빈도가 다른 숫자의 등장으로 인해 0으로 상쇄된다면,
 그 수는 절대 과반수 원소일 수가 없다는 것이다.
 
 이 알고리즘은 데이터를 한 차례 순회한 후 다시 집계할 필요가 없다는 점에서 [스트리밍 알고리즘](https://en.wikipedia.org/wiki/Streaming_algorithm)의
 일종으로 분류된다.
 
 다만, 과반수 초과(freq > length / 2)의 원소가 등장하지 않는 경우 단순히 임의의 원소가 나오게 된다는 점에서
 주의할 필요는 있다.
 */

// 0ms, 17.53MB
class SolutionWithDictionary {
    func majorityElement(_ nums: [Int]) -> Int {
        var freq: [Int: Int] = [:]

        for i in nums {
            freq[i, default: 0] += 1
        }

        let limit = nums.count / 2
        for (i, f) in freq {
            if f > limit {
                return i
            }
        }

        return -1
    }
}

// 0ms, 17.86MB
class SolutionBoyerMoore  {
    func majorityElement(_ nums: [Int]) -> Int {
        var majority = 0
        var count = 0

        for i in nums {
            if count == 0 {
                majority = i
            }

            if i == majority {
                count += 1
            } else {
                count -= 1
            }
        }

        return majority
    }
}

//: [Next](@next)
