/* https://www.acmicpc.net/problem/2490 */

#include <iostream>

using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);

	for (int i = 0; i < 3; ++i) {
		int up = 0, down = 0;

		for (int j = 0; j < 4; ++j) {
			int input;
			cin >> input;

			if (input == 0)
				++down;
			else if (input == 1)
				++up;
		}

		if (down == 1)
			cout << 'A';
		else if (down == 2)
			cout << 'B';
		else if (down == 3)
			cout << 'C';
		else if (down == 4)
			cout << 'D';
		else if (up == 4)
			cout << 'E';

		cout << '\n';
	}

	return 0;
}
