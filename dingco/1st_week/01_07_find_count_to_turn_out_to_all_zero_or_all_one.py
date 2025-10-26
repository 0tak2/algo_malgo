input = "011110"


def find_count_to_turn_out_to_all_zero_or_all_one(string):
    count_to_make_all_0 = 0
    count_to_make_all_1 = 0

    count_to_make_all_0 = 1 if string[0] == '1' else 0 # 일단 첫 글자가 0이면 모두 1로 만들기 위해 적어도 1번은 뒤집어야 함
    count_to_make_all_1 = 1 if string[0] == '0' else 0

    for i in range(1, len(string) - 1):
        if string[i] == string[i-1]:
            continue

        # 문자가 바뀜
        if string[i] == '0': # 바뀔 때 새로 바뀐 수가 0인지, 1인지에 따라 해당하는 카운터를 증가시킴
            count_to_make_all_1 += 1
        else:
            count_to_make_all_0 += 1
        

    return min(count_to_make_all_1, count_to_make_all_0)


result = find_count_to_turn_out_to_all_zero_or_all_one(input)
print(result)
