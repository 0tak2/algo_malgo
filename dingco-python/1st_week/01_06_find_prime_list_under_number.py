input = 20

# O(n^2)
def find_prime_list_under_number_1(number):
    ret = []

    for i in range(2, number+1):
        # print("i: " + str(i))
        for j in range(2, i):
            # print("j: " + str(j))
            if i % j == 0:
                # print("is not prime\n")
                break
        else: # break 없이 모두 돌았음
            ret.append(i)

    return ret

def find_prime_list_under_number_2(number):
    primes = []

    for i in range(2, number+1):
        for j in primes: # 이전까지 나온 소수들로 나눴을 때 안나눠지면 역시 소수
            if i % j == 0:
                # print("is not prime\n")
                break
        else:
            primes.append(i)

    return primes

# 자연수 N이 소수이려면 N이 N의 제곱근보다 크지 않은 어떤 소수로도 나눠지지 않음을 밝히면 된다.
# 몫과 나누는 수 중 하나는 반드시 제곱근보다 작기 때문
def find_prime_list_under_number(number):
    primes = []

    for i in range(2, number+1):
        for j in primes: # 이전까지 나온 소수들로 나눴을 때 안나눠지면 역시 소수
            if j * j <= i and i % j == 0:
                # print("is not prime\n")
                break
        else:
            primes.append(i)

    return primes

result = find_prime_list_under_number(input)
print(result)

# 소수는 자기 자신과 1 이외에는 어떤 수로도 나눌 수 없다