DIMENSION = 8
DX = [-2, -2, +2, +2, -1, -1, +1, +1]
DY = [-1, +1, -1, +1, -2, +2, -2, +2]
TOTAL_CMD = len(DX)
# 2L 1U
# 2L 1D
# 2R 1U
# 2R 1D
# 1L 2U
# 1L 2D
# 1R 2U
# 1R 2D

result = 0;
x, y = list(input())
x = ord(x) - ord('a')
y = int(y) - 1

for i in range(TOTAL_CMD):
    next_x = x + DX[i]
    next_y = y + DY[i]

    if next_x < 0 or next_x >= DIMENSION or next_y < 0 or next_y >= DIMENSION:
        continue

    result += 1

print(result)
