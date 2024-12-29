import UIKit

// Number of Islands
// https://leetcode.com/explore/learn/card/queue-stack/231/practical-application-queue/1374/
/**
 ### 분석
 간단한 탐색 문제
 */

// 242ms, 18.5MB
// 많이 느렸다...
class Solution {
    var visited: [[Bool]]!
    var map: [[Character]]!
    
    // 상, 하, 좌, 우
    let dys = [-1, 1, 0, 0]
    let dxs = [0, 0, -1, 1]
    
    func bfs(_ startY: Int, _ startX: Int) {
        var q: [[Int]] = [] // [ [y1, x1], [y2, x2], [y3, x3] ]
        
        q.append([startY, startX]) // 뒤로 넣고
        visited[startY][startX] = true
        
        while !q.isEmpty {
            let node = q.removeFirst()
            let (y, x) = (node[0], node[1]) // 앞에서 뺀다
            
//            print("visited \(y) \(x)")
            
            for i in 0..<4 {
                let dy = dys[i]
                let dx = dxs[i]
                
                if y + dy >= 0 && y + dy < map.count
                   && x + dx >= 0 && x + dx < map[0].count {
                   
                    let ny = y + dy
                    let nx = x + dx
                    if !visited[ny][nx] && map[ny][nx] == "1" {
//                        print("appended \(ny) \(nx)")
                        q.append([ny, nx])
                        visited[ny][nx].toggle()
                    }
                }
            }
        }
    }
    
    func numIslands(_ grid: [[Character]]) -> Int {
        map = grid
        
        let yDimension = grid.count
        let xDimension = grid[0].count
        
        visited = Array(repeating: Array(repeating: false, count: xDimension),
                        count: yDimension)
        
        var count = 0
        
        for (y, row) in grid.enumerated() {
            for (x, cell) in row.enumerated() {
                if !visited[y][x] && cell == "1" {
//                    print("entry \(y) \(x)")
                    bfs(y, x)
//                    print(visited)
                    count += 1
                }
            }
        }
        
        return count
    }
}

let solution = Solution()

// Test 1
/*
 [["1","1","0","0","0"],
 ["1","1","0","0","0"],
 ["0","0","1","0","0"],
 ["0","0","0","1","1"]]
 3
 */
let result1 = solution.numIslands([["1","1","0","0","0"],["1","1","0","0","0"],["0","0","1","0","0"],["0","0","0","1","1"]])
assert(result1 == 3)
