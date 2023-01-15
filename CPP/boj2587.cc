/* https://www.acmicpc.net/problem/2587 */

#include <iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int input[5];
    int sum = 0, avg, center = 0;
    int max = 0, max_prv = 0;

    for (int i = 0; i < 5; ++i) {
        cin >> input[i];
        sum += input[i];
        if (input[i] >= max)
            max = input[i];
    }

    for (int i = 0; i < 5; ++i) {
        if (input[i] <= max && input[i] >= max_prv)
            max_prv = input[i];
    }

    for (int i = 0; i < 5; ++i) {
        if (input[i] <= max_prv && input[i] >= center)
            center = input[i];
    }

    avg = sum / 5;
    
    cout << avg << '\n' << center;

    return 0;
}
