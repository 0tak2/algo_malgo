# y, x
MOVE_BY_DIRECTION = [(0, -1), (-1, 0), (0, +1), (+1, 0)]

yd, xd = map(int, input().split())
y, x, d = map(int, input().split())
answer = 1

tiles = []
visited = []
for i in range(yd):
    tiles.append(input().split())
    visited.append([False for j in range(len(tiles[i]))])

while True:
    moved = False

    for i in range(4): # 최대 4번 회전
        y_candidate = y + MOVE_BY_DIRECTION[d][0]
        x_candidate = x + MOVE_BY_DIRECTION[d][1]

        if 0 <= y_candidate < yd and 0 <= x_candidate < xd and tiles[y_candidate][x_candidate] == '0' and not visited[y_candidate][x_candidate]:
            y = y_candidate
            x = x_candidate
            visited[y][x] = True
            answer += 1
            moved = True
            d -= 1
            if d < 0:
                d = 3
            break

        d -= 1
        if d < 0:
            d = 3

    if moved:
        continue

    break

print(answer)
