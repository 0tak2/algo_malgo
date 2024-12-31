//: [Previous](@previous)

import Foundation

// Daily Temperatures
// https://leetcode.com/explore/learn/card/queue-stack/230/usage-stack/1363/
/**
 ### 분석
 일별 온도가 배열로 주어질 떄, 각 일자별로 며칠 기다리면 온도가 상승하는지를 배열로 반환한다.
 
 ### 접근
 이중루프를 이용한 방법만 떠올랐다. 각 일자별 온도에 대해 다시 그 다음 날부터의 데이터를 순회하면서 처음으로 온도가 상승하는 날을 찾는 방식이다.
 예상했듯이 너무 느렸다.
 
 ### 스택 사용
 인터넷에서 솔루션을 찾아 참고했다 -> https://algo.monster/liteproblems/739
 스택을 사용해 전체적으로 데이터를 1회 순회하면서, 다음을 반복한다. 스택에는 특정 일자의 인덱스를 담을 것이다.
   1. 스택이 빌 떄까지 다음을 반복한다.
     - 현재 온도가 스택의 tail가 가리키는 온도보다 높다면
       - tail을 pop한다
       - 현재 인덱스에서 tail을 빼 경과 일자를 계산하고, 정답 배열에 넣는다.
   2. 현재 일자를 스택에 추가한다.
 
 이렇게 되면 최근 일자의 온도부터 현재 온도와 비교하게 된다. 인덱스를 저장하기 때문에 경과한 일자도 계산할 수 있다.
 
 큐를 쓰면 왜 안될까? - 스택에 오래 남아있는 데이터의 경우 더 높은 온도가 오랫동안 등장하지 않았다는 것이다.
 따라서 가장 최근 데이터인 tail부터 비교하고, 그 다음 데이터를 비교해야 한다.
 그렇지 않고 큐의 방식으로 head의 온도와 현재 온도를 비교하면 최근 일자 온도(tail)보다 현재 온도가 높아졌는데도 오래된 일자 온도(head)보다는 높지 않아 처리가 다음으로 밀리는 경우가 생긴다.
 
 ### 개선
 솔루션 코드를 보면서 코드를 깔끔하게 다듬었다.
 */

// 3898ms, 25.6MB
class SolutionSlow {
    func dailyTemperatures(_ temperatures: [Int]) -> [Int] {
        var result = [Int]()
        for (index, temp) in temperatures.enumerated() {
            var days = 0
            var found = false
            for target in temperatures[index+1..<temperatures.count] {
                days += 1
                if target > temp {
                    found.toggle()
                    break
                }
            }
            result.append(found ? days : 0)
        }
        return result
    }
}

// 8ms, 25.7MB
class SolutionMyself {
    func dailyTemperatures(_ temperatures: [Int]) -> [Int] {
        var st = [Int]()
        
        var result = Array(repeating: 0, count: temperatures.count)
        
        for (i, t) in temperatures.enumerated() {
            while !st.isEmpty {
                if let lastIndex = st.last {
                    let queuedTemp = temperatures[lastIndex]

                    if t > queuedTemp {
                        result[lastIndex] = i - lastIndex
                        if !st.isEmpty {
                            st.removeLast()
                        }
                    } else {
                        break
                    }
                }
            }
            
            st.append(i)
        }
        
        return result
    }
}

// 4ms, 25.6MB
class Solution {
    func dailyTemperatures(_ temperatures: [Int]) -> [Int] {
        var st = [Int]()
        
        var result = Array(repeating: 0, count: temperatures.count)
        
        for (i, t) in temperatures.enumerated() {
            while !st.isEmpty && t > temperatures[st.last!] {
                result[st.last!] = i - st.last!
                st.removeLast()
            }
            
            st.append(i)
        }
        
        return result
    }
}

//: [Next](@next)
