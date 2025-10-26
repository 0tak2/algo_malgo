let findingTarget = 23
let findingNumbers = [2, 5, 8, 12, 16, 23, 38, 56, 72, 91]

func isExistingTargetNumberBinary(target: Int, from array: [Int]) -> Bool {
    var midIndex = array.count / 2
    var findingStart = 0
    var findingEnd = array.count - 1

    while findingStart <= findingEnd {
        let mid = array[midIndex]

        // print("findingStart \(findingStart)")
        // print("findingEnd \(findingEnd)")
        // print("midIndex \(midIndex)\n")
        // print("mid \(mid)")

        if mid == target {
            return true
        } else if mid < target {
            findingStart = midIndex + 1 // 인덱스!!! mid가 아니라 midIndex임
        } else if mid > target {
            findingEnd = midIndex - 1
        }

        midIndex = (findingStart + findingEnd) / 2
    }

    return false
}

let result = isExistingTargetNumberBinary(target: findingTarget, from: findingNumbers)
print(result)
