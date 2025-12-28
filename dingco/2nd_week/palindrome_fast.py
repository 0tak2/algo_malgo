def isPalindrome(word, leftIndex, rightIndex):
    while leftIndex < rightIndex:
        if word[leftIndex] != word[rightIndex]:
            return False
        leftIndex += 1
        rightIndex -= 1
    return True

def solution(word):
    leftIndex = 0
    rightIndex = len(word) - 1

    while leftIndex < rightIndex:
        if word[leftIndex] != word[rightIndex]:
            case1 = isPalindrome(word, leftIndex+1, rightIndex)
            case2 = isPalindrome(word, leftIndex, rightIndex-1)
            return "1" if case1 or case2 else "2"
        
        leftIndex += 1
        rightIndex -= 1
    
    return "0"

numberOfLines = int(input())
result = []
for _ in range(numberOfLines):
    line = input()
    result.append(solution(line))
print("\n".join(result))
