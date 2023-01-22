/* https://www.acmicpc.net/problem/10808 */
#include <bits/stdc++.h>
using namespace std;

int main(void) {
    ios::sync_with_stdio(0);
    cin.tie(0);

    string s;
    int arr[26] = {0};

    cin >> s;

    for(char c : s) {
        int index = c - 'a';
        arr[index]++;
    }

    for(int a : arr) {
        cout << a << ' ';
    }

    return 0;
}
