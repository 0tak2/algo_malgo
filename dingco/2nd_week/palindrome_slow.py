def isPalindrome(word):
    center = len(word) // 2

    for i in range(center):
        r = len(word) - i - 1
        if word[i] != word[r]:
            return False

    return True

def solution(word):
    if len(word) % 2 == 0:
        # 회문이거나 둘 다 아니거나
        return 0 if isPalindrome(word) else 2
    else:
        # 유사회문이거나 둘 다 아니거나
        for (i, c) in enumerate(word):
            newWord = word[0: i] + word[i+1:]
            if isPalindrome(newWord):
                return 1
        return 2

numberOfLines = int(input())
for _ in range(numberOfLines):
    line = input()
    print(solution(line))
