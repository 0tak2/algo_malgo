/* https://www.acmicpc.net/problem/1406 */

#include <bits/stdc++.h>
using namespace std;

const int MX = 600001;
char dat[MX] = {0};
int pre[MX];
int nxt[MX];
int unused = 1;
int cursor = 0;

void push(int idx, char val) {
    dat[unused] = val;
    pre[unused] = idx;
    nxt[unused] = nxt[idx];

    if (nxt[idx] != -1)
        pre[nxt[idx]] = unused;
    nxt[idx] = unused;

    cursor = unused;
    unused++;
}

void erase(int idx) {
    if (idx == 0)
        return;

    nxt[pre[idx]] = nxt[idx];
    if (nxt[idx] != -1)
        pre[nxt[idx]] = pre[idx];
    cursor = pre[idx];
}

void show() {
    int cursor = nxt[0];
    while(cursor != -1) {
        cout << dat[cursor];
        cursor = nxt[cursor];
    }
}

int main(void) {
    ios::sync_with_stdio(0);
    cin.tie(0);

    fill(pre, pre+MX, -1);
    fill(nxt, nxt+MX, -1);

    string origin;
    int N;

    cin >> origin >> N;
    
    nxt[0] = 1;
    for (int i=0; i<origin.size(); i++) {
        dat[i+1] = origin.at(i);
        pre[i+1] = i;
        if (i != origin.size()-1) {            
            nxt[i+1] = i+2;
        } 
        cursor = i + 1;
        unused++;
    }

    for (int i=0; i<N; i++) {
        char cmd;
        char target;

        cin >> cmd;
        if (cmd == 'P') {
            cin >> target;
            push(cursor, target);
        } else if (cmd == 'L') {
            if (pre[cursor] != -1) 
                cursor = pre[cursor];
        } else if (cmd == 'D') {
            if (nxt[cursor] != -1)
                cursor = nxt[cursor];
        } else if (cmd == 'B') {
            erase(cursor);
        }

    }

    show();

    return 0;
}
