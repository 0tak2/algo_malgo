/**
 * 올바른 괄호
 * https://school.programmers.co.kr/learn/courses/30/lessons/12909
 * 
 * 스택 활용
 */
package programmers.level2;

import java.util.*;

class Paren12909 {
    boolean solution(String s) {
        Stack<Integer> st = new Stack<>();
        
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == '(')
                st.push(0);
            else if (s.charAt(i) == ')') {
                try {
                    st.pop();
                } catch (Exception e) {
                    return false;
                }
            }    
        }
        
        if (!st.empty()) return false;
        return true;
    }
}