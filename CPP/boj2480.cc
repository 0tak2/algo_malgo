/* https://www.acmicpc.net/problem/2480 */
#include <iostream>

using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	
	int a, b, c, max;
	cin >> a >> b >> c;

	if (a == b && b == c && c == a)
		cout << 10000 + a * 1000;
	else if ((a == b && b != c) || (b == c && b != a)) 
		cout << 1000 + b * 100;
	else if (a == c && a != b) // then, c != b
		cout << 1000 + a * 100;
	else if (a != b && b != c && c != a) {
		if (a >= b && a >= c)
			max = a;
		else if (b >= a && b >= c)
			max = b;
		else if (c >= a && c >= b)
			max = c;
		
		cout << max * 100;
	}

	return 0;
}
