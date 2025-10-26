func josephus(_ n: Int, _ k: Int) -> [Int] {
    var removeOrder = [Int]()

    var people = [Int]()
    for i in 1...n {
        people.append(i)
    }
    var nextPopIndex = k - 1

    while !people.isEmpty {
        let popped = people.remove(at: nextPopIndex)
        removeOrder.append(popped)

        if !people.isEmpty {
            nextPopIndex = (nextPopIndex + (k - 1)) % people.count
        }
    }

    return removeOrder
}

let inputs = readLine()!.split(separator:" ").map { Int($0)! }
let result = josephus(inputs[0], inputs[1])
print("<\(result.map { "\($0)" }.joined(separator: ", "))>")
