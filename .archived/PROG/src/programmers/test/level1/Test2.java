package programmers.test.level1;

import java.util.ArrayList;

class Node {
	public int y;
	public int x;
	
	public Node(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Test2 {
    public int[] solution(String[] wallpaper) {
        int[] answer = new int[4];
       	Node st = new Node(wallpaper.length, wallpaper[0].length() - 1);
       	Node ed = new Node(0, 0);
        
        for (int i=0; i<wallpaper.length; i++) {
        	String row = wallpaper[i];
        	for (int j=0; j<row.length(); j++) {
        		char ch = row.charAt(j);
        		if (ch == '#') {
        			if (st.x > j) {
        				st.x = j;
        			}
        			if (st.y > i) {
        				st.y = i;
        			}
        			if (ed.x < j) {
        				ed.x = j;
        			}
        			if (ed.y < i) {
        				ed.y = i;
        			}
        		}
        	}
        }
        
        answer[0] = st.y;
        answer[1] = st.x;
        answer[2] = ed.y + 1;
        answer[3] = ed.x + 1;
        
        return answer;
    }
    
    public static void main(String[] args) {
		Test2 t = new Test2();
		
		String[] case1 = {".#...", "..#..", "...#."};
		System.out.println(t.solution(case1)[0]);
		System.out.println(t.solution(case1)[1]);
		System.out.println(t.solution(case1)[2]);
		System.out.println(t.solution(case1)[3]);
		
//		String[] case2 = {"..........", ".....#....", "......##..", "...##.....", "....#....."};
//		System.out.println(t.solution(case2));;
		String[] case2 = {"..........", ".....#....", "......##..", "...##.....", "....#....."};
		System.out.println(t.solution(case2)[0]);
		System.out.println(t.solution(case2)[1]);
		System.out.println(t.solution(case2)[2]);
		System.out.println(t.solution(case2)[3]);
//		
//		String[] case3 = {".##...##.", "#..#.#..#", "#...#...#", ".#.....#.", "..#...#..", "...#.#...", "....#...."};
//		System.out.println(t.solution(case3));;
//		
//		String[] case4 = {"..", "#."};
//		System.out.println(t.solution(case4));;
	}
}