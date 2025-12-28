def solution(word):
    leftIndex = 0
    rightIndex = len(word) - 1

    isPsudoPalindrome = False

    while leftIndex < rightIndex and leftIndex < len(word) and rightIndex >= 0:
        c1 = word[leftIndex]
        c2 = word[rightIndex]

        if c1 == c2:
            leftIndex += 1
            rightIndex -= 1
            continue

        if isPsudoPalindrome:
            return "2" # 한 번 유사 회문임이 판별되었으면 이후에는 완전회문이어야 한다

        nextLeftIndex = leftIndex + 1
        if word[nextLeftIndex] == word[rightIndex]:
            leftIndex = nextLeftIndex + 1
            rightIndex -= 1
            isPsudoPalindrome = True
            continue

        nextRightIndex = rightIndex - 1
        if word[leftIndex] == word[nextRightIndex]:
            leftIndex += 1
            rightIndex = nextRightIndex - 1
            isPsudoPalindrome = True
            continue

        return "2"
    
    return "1" if isPsudoPalindrome else "0"

numberOfLines = int(input())
result = []
for _ in range(numberOfLines):
    line = input()
    result.append(solution(line))
print("\n".join(result))
