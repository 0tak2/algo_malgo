input = "abadabac"

def find_not_repeating_first_character(string):
    counts = [0] * 26

    for c in string:
        index = ord(c) - ord('a')
        counts[index] += 1
    
    # for idx, count in enumerate(counts):
    #     if count == 1:
    #         return chr(idx + ord('a'))
    # -> 위와 같이 하면 사전 순으로 먼저 처음 등장한 알파벳이 반환되어 틀린다

    for c in string:
        index = ord(c) - ord('a')
        if counts[index] == 1:
            return c

    return "_"


result = find_not_repeating_first_character
print("정답 = d 현재 풀이 값 =", result("abadabac"))
print("정답 = c 현재 풀이 값 =", result("aabbcddd"))
print("정답 =_ 현재 풀이 값 =", result("aaaaaaaa"))
