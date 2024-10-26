package grind75

// Best Time to Buy and Sell Stock
// Grind75 #4
//
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
//
// prices를 순회하면서, 1) "지금까지의" 최소 가격과 현재 가격을 가지고 이익 계산, 최대 이익이라면 갱신
// 1) 최소 가격 갱신
// 위의 두 작업을 동시 수행
// O(N)
//
// 0ms, 9.85MB
func maxProfit(prices []int) int {
	minPrice := prices[0]
	maxProfit := 0

	for _, p := range prices {
		if p-minPrice > maxProfit {
			maxProfit = p - minPrice
		}

		if p < minPrice {
			minPrice = p
		}
	}

	return maxProfit
}

// 처음에 생각했던 풀이 - 완전 탐색
// time limit
// O(N^2)
func maxProfit_failed(prices []int) int {
	max := 0

	for i := 0; i < len(prices); i++ {
		for j := i; j < len(prices); j++ {
			if prices[j]-prices[i] > max {
				max = prices[j] - prices[i]
			}
		}
	}

	return max
}
