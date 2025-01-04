//: [Previous](@previous)

import Foundation

// Flood Fill
// https://leetcode.com/explore/learn/card/queue-stack/239/conclusion/1393/
// Duplicated: https://github.com/0tak2/algo_malgo/blob/71ee4807e34a27f71a38698c50dd17e5df675977/interview-java/src/main/java/io/github/otak2/leetcode/grind75/FloodFill.java

// 0ms, 18.1MB
class Solution {
    let dy = [-1, 1, 0, 0]
    let dx = [0, 0, -1, 1]
    
    func floodFill(_ image: [[Int]], _ sr: Int, _ sc: Int, _ color: Int) -> [[Int]] {
        var st = [[Int]]() // [ [yPos, xPos] ]
        var visited = [[Bool]](repeating: [Bool](repeating: false, count: image[0].count),
                               count: image.count)
        var result = image
        
        st.append([sr, sc])
        visited[sr][sc].toggle()
        let targetColor = image[sr][sc]
        
        while !st.isEmpty {
            let pos = st.removeLast()
            let (y, x) = (pos[0], pos[1])
            result[y][x] = color
            
            for i in 0..<4 {
                let ny = y + dy[i]
                let nx = x + dx[i]
                
                if ny >= 0 && ny < image.count
                    && nx >= 0 && nx < image[0].count
                    && image[ny][nx] == targetColor
                    && !visited[ny][nx] {
                   
                    st.append([ny, nx])
                    visited[ny][nx].toggle()
                }
            }
        }
        
        return result
    }
}

//: [Next](@next)
