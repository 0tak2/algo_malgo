finding_target = 23
finding_numbers = [2, 5, 8, 12, 16, 23, 38, 56, 72, 91]

def is_existing_target_number_binary(target, array):
    if not array:
        return False

    mid_index = len(array) // 2
    mid = array[mid_index]
    if mid == target:
        return True
    elif mid < target:
        return is_existing_target_number_binary(target, array[mid_index+1:])
    elif mid > target:
        return is_existing_target_number_binary(target, array[:mid_index])


result = is_existing_target_number_binary(finding_target, finding_numbers)
print(result)
