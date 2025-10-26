func calcFactorial1(_ number: Int) -> Int {
    if number == 0 {
        return 1
    }

    var ret = 1

    for i in 1...input {
        ret *= i
    }

    return ret
}

func calcFactorial2(_ number: Int) -> Int { // OOM
    if number == 0 {
        return 1
    }

    if number == 1 {
        return number
    }

    return number * calcFactorial2(number-1)
}

let input = Int(readLine()!)!
print(calcFactorial1(input))
