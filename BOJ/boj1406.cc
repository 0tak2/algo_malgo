/* https://www.acmicpc.net/problem/1406 */

#include <bits/stdc++.h>
using namespace std;

const int MX = 10001;
char dat[MX] = {0};
int pre[MX] = {-1};
int nxt[MX] = {-1};
int unused = 1;

void push_left(int idx, char val) {
}

void erase(int idx) {
}

int main(void) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    string origin;
    int N;

    cin >> origin >> N;
    
    for (int i=0; i<origin.size(); i++) {
        dat[i+1] = origin.at(i);
        pre[i+1] = i;
        if (i != origin.size()-1)
            nxt[i+1] = i+2;
        unused++;
    }

    for (int i=0; i<N; i++) {
        char cmd;
        char target;

        cin >> cmd;
        if (cmd == 'P')
            cin >> target;

    }

}
