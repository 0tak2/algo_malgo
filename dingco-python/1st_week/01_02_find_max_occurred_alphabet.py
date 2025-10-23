# 처음 직접 본 것
def find_max_occurred_alphabet_1(string):
    occurs = {}

    for c in string:
        if c == " ":
            continue

        if c in occurs:
            occurs[c] += 1
        else:
            occurs[c] = 1
    
    max_occur = 0
    max_key = None
    for k, v in occurs.items():
        if v > max_occur:
            max_occur = v
            max_key = k
    
    if max_key is None:
        print("Something Wrong...")
        max_key = "a"

    return max_key

# 교재 보고 다시 풀어본 것
# 문자의 개수는 26개이므로 배열을 생성
def find_max_occurred_alphabet_2(string):
    occurs = [0] * 26

    for c in string:
        if c == " ":
            continue

        index = ord(c) - ord("a")
        occurs[index] += 1
    
    max_occurs = 0
    max_index = -1
    for i, v in enumerate(occurs):
        if v > max_occurs:
            max_occurs = v
            max_index = i

    max_character = chr(max_index + ord("a"))

    return max_character

# 교재 풀이

def find_max_occurred_alphabet_answer(string):
    alphabet_occurrence_array = [0] * 26

    for char in string:
        if not char.isalpha():
            continue
        arr_index = ord(char) - ord('a')
        alphabet_occurrence_array[arr_index] += 1

    max_occurrence = 0
    max_alphabet_index = 0
    for index in range(len(alphabet_occurrence_array)):
        alphabet_occurrence = alphabet_occurrence_array[index]
        if alphabet_occurrence > max_occurrence:
            max_occurrence = alphabet_occurrence
            max_alphabet_index = index

    return chr(max_alphabet_index + ord('a'))

result = find_max_occurred_alphabet_2
print("정답 = i 현재 풀이 값 =", result("hello my name is dingcodingco")) # 1, 2번 풀이가 답이 다름. 1번은 Dict에 들어오는 순서대로 순회, 2번은 알파벳 순서대로 순회하기 때문임
print("정답 = e 현재 풀이 값 =", result("we love algorithm"))
print("정답 = b 현재 풀이 값 =", result("best of best youtube"))

"""
str.isalpha()
특정 문자열의 문자 여부를 판단한다



"""