dx = [-1, +1, 0, 0]
dy = [0, 0, -1, +1]
cmd = ['L', 'R', 'U', 'D']

x = 1
y = 1

dimension = int(input())
cmd_input = input().split()

for c in cmd_input:
    cmdIdx = cmd.index(c)
    next_x = x + dx[cmdIdx]
    next_y = y + dy[cmdIdx]
    if next_x < 1 or next_x > dimension or next_y < 1 or next_y > dimension:
        continue
    x = next_x
    y = next_y

print(y, x)