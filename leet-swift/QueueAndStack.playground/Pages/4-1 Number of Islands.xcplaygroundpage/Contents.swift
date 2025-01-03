//: [Previous](@previous)

import Foundation

// Number of Islands
// https://leetcode.com/explore/learn/card/queue-stack/232/practical-application-stack/1380/
/**
 큐에서 풀었던 문제. 큐를 스택으로 바꾸기만 하면 된다.
 */

// 215ms, 18.3MB
class Solution {
    // 상, 하, 좌, 우
    let dy = [-1, 1, 0, 0]
    let dx = [0, 0, -1, 1]
    
    var visited: [[Bool]]!
    
    func dfs(_ startY: Int,_ startX: Int,_ map: [[Character]]) {
        var st = [[Int]]() // [y, x]
        
        st.append([startY, startX])
        visited[startY][startX].toggle()
        
        while !st.isEmpty {
            let popped = st.removeLast()
            let (y, x) = (popped[0], popped[1])
            
            for i in 0..<4 {
                let ny = y + dy[i]
                let nx = x + dx[i]
                
                if  ny >= 0 && ny < map.count &&
                    nx >= 0 && nx < map[0].count &&
                    !visited[ny][nx] &&
                    map[ny][nx] == "1" {
                    
                    st.append([ny, nx])
                    visited[ny][nx].toggle()
                }
            }
        }
    }
    
    func numIslands(_ grid: [[Character]]) -> Int {
        visited = [[Bool]](repeating: [Bool](repeating: false, count: grid[0].count),
                            count: grid.count)
        var count = 0
        
        for (y, row) in grid.enumerated() {
            for (x, cell) in row.enumerated() {
                if !visited[y][x] && grid[y][x] == "1" {
                    dfs(y, x, grid)
                    count += 1
                }
            }
        }
        
        return count
    }
}

//: [Next](@next)
