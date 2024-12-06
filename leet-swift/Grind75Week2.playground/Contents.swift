import Foundation

// MARK: - 278. First Bad Version (Grind75 #2-1)
// https://leetcode.com/problems/first-bad-version
/**
 ### 분석
 문제 여부를 알 수 없는 버전이 n개 있다. 각 버전은 이전 버전에 증분하여 릴리즈되기 때문에 문제가 시작된 버전 이후 버전 n까지는 모두 문제가 있다.
 예를 들어 n이 10이라면, 버전 1부터 버전 10까지 존재하며, 이 사이 특정 버전 k부터 n까지는 모두 문제가 있다. 이때 문제가 시작된 K버전을 찾는 문제이다.
 
 Solution 클래스의 상위 클래스에 `func isBadVersion(_ version: Int) -> Bool{}` 메서드가 있으며,
 이 메서드를 통해 특정 버전에 문제가 있는지, 없는지를 확인할 수 있다.
 
 호출 횟수를 최소화하면서도 시작 버전을 찾을 수 있는지가 핵심이다.
 
 ### 접근
 - 정렬된 버전 번호 리스트에서, 문제가 있는 버전을 찾는 것이기 때문에 이진탐색으로 접근했다.
 - 다만 일반적인 이진탐색과 달리, 서브 레인지의 시작점을 찾아야 한다. 따라서 구체적인 조건이 살짝 달라져야 한다. 다음과 같이 일반화했다.
 
 1. 먼저 중간 인덱스 center에 대해 문제 버전인지 확인
   - 중간 인덱스가 문제 버전이 아닌 경우 -> 문제 시작 버전은 무조건 중간 인덱스의 오른쪽에 위치한다.
     -> 시작 인덱스를 center + 1, 종료 인덱스를 end로 설정
   - 중간 인덱스가 문제 버전인 경우 -> 문제 시작 버전은 중간 인덱스 자체이거나, 아니면 중간 인덱스 왼쪽에 위치한다.
     -> 시작 인덱스를 center, 종료 인덱스를 end로 설정[\*]
 2. 시작, 종료 범위를 위와 같이 설정하고 이 과정을 반복한다. 시작 지점과 종료 지점이 동일해지면 해당 인덱스를 반환한다.
 \* 시작 인덱스를 위의 경우처럼 중간 인덱스를 제외해 center - 1로 지정하면 안된다.  center가 답일 수도 있기 떄문이다.
 
 ### 추가 고민
 위의 코멘트(\*)의 경우처럼, center가 문제 시작 버전 자체인 경우, 바로 반환하지 못하고 center를 포함한 왼쪽 범위를 탐색하게 된다.
 최적화의 여지가 남아있다.
 */

// 0ms, 15.30MB
class Solution : VersionControl {
    func findFirstBadVersion(start: Int, end: Int) -> Int {
        if start == end {
            return start
        }

        let center: Int = (start + end) / 2

        if isBadVersion(center) { // center가 bad면 시작점은 center 자신이거나 그 왼쪽에 있다
            return findFirstBadVersion(start: start, end: center) // center를 포함
        } else { // 시작점은 오른쪽에 있다
            return findFirstBadVersion(start: center + 1, end: end)
        }
    }

    func firstBadVersion(_ n: Int) -> Int {
        findFirstBadVersion(start: 1, end: n)
    }
}

/**
 오류 표시 방지를 위한 스텁. 문제에서는 구현체를 제공하지 않는다.
 */
class VersionControl {
    func isBadVersion(_ n: Int) -> Bool {
        return true
    }
}

