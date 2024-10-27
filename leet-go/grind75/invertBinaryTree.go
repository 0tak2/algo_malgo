package grind75

// Invert Binary Tree
// Grind75 #6
//
// https://leetcode.com/problems/invert-binary-tree/
//
// 재귀함수로 현재 노드가 nil이 될 때 까지 Left와 Right의 포인터를 스왑하도록 했다.
//
// 0ms, 3.99MB
func invertTree(root *TreeNode) *TreeNode {
	if root == nil {
		return nil
	}

	cursor := root
	invertChild(cursor)

	return root
}

func invertChild(node *TreeNode) {
	if node == nil {
		return
	}

	tmp := node.Left
	node.Left = node.Right
	node.Right = tmp

	invertChild(node.Left)
	invertChild(node.Right)
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}
