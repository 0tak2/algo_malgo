def josephus(n, k):
    people = list(range(1, n+1))

    remove_order = []
    next_target = k-1

    while people:
        popped = people.pop(next_target)
        remove_order.append(popped)

        if people:
            next_target = (next_target + (k-1)) % len(people)

    ret = "<" + ", ".join(map(str, remove_order)) + ">"
    print(ret)

n, k = map(int, input().split())
josephus(n, k)
