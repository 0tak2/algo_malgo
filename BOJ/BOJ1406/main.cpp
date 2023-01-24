/* https://www.acmicpc.net/problem/1406 */

#include <bits/stdc++.h>
using namespace std;

int main(void) {
    ios::sync_with_stdio(0);
    cin.tie(0);

    list<char> li;
    auto cursor = li.end();

    string origin;
    int N;

    cin >> origin >> N;
    
    for (int i=0; i<origin.size(); i++) {
        li.push_back(origin.at(i));
    }

    for (int i=0; i<N; i++) {
        char cmd;
        char target;

        cin >> cmd;
        if (cmd == 'P') {
            cin >> target;
            li.insert(cursor, target);
        } else if (cmd == 'L') {
            if (cursor != li.begin())
                cursor--;
        } else if (cmd == 'D') {
            if (cursor != li.end())
                cursor++;
        } else if (cmd == 'B') {
            if(cursor != li.begin()) {
                cursor--;
                cursor = li.erase(cursor);
            }
        }
    }

    for (char c : li)
        cout << c;

    cout << '\n';
    
    return 0;
}