//: [Previous](@previous)

import Foundation

// Keys and Rooms
// https://leetcode.com/explore/learn/card/queue-stack/239/conclusion/1391/
/**
 ### 분석
 0번 방을 제외하고서 모든 방은 잠겨있다. 그리고 각 방에는 다른 방을 열 수 있는 열쇠가 있다.
 각 방의 열쇠 정보가 배열로 주어지면, 순서대로 0번 방부터 방문했을 때 모든 방을 방문할 수 있는지 여부를 반환하면 된다.
 
 ### 접근
 0번 방에서 시작해 키 들을 큐에 넣어두고, 이 큐를 가지고 bfs를 하는 탐색을 하는 방법을 고안했다.
 어렵지 않게 구현할 수 있었다.
 솔루션이 무료였는데 스택을 사용해 dfs 했다는 차이만 있고 유사헀다.
 
 */

// 0ms, 17.8MB
class Solution {
    func canVisitAllRooms(_ rooms: [[Int]]) -> Bool {
        var q = [0]
        var visited = [0]
        
        while !q.isEmpty {
            let roomId = q.removeFirst()
            let keys = rooms[roomId]
            
            for id in keys {
                if !visited.contains(id) {
                    q.append(id)
                    visited.append(id)
                }
            }
        }
        
        return visited.count == rooms.count
    }
}

//: [Next](@next)
