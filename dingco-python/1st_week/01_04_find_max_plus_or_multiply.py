def find_max_plus_or_multiply_trial(array):
    result = array[0]

    for number in array[1:]:
        sum_result = result + number
        multiply_result = result * number
        result = max(sum_result, multiply_result)

    return result

def find_max_plus_or_multiply_answer(array):
    result = array[0]

    for number in array[1:]:
        if result <= 1 or number <= 1:
            result = result + number
        else:
            result = result * number

    return result


result = find_max_plus_or_multiply_answer
print("정답 = 728 현재 풀이 값 =", result([0,3,5,6,1,2,4]))
print("정답 = 8820 현재 풀이 값 =", result([3,2,1,5,9,7,4]))
print("정답 = 270 현재 풀이 값 =", result([1,1,1,3,3,2,5]))
