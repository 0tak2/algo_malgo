package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10798 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in)
		);
		
		String[][] blackboard = new String[5][15];
		
		for(int i=0; i<5; i++) {
			String[] letterArr = br.readLine().split("");
			for (int j=0; j<letterArr.length; j++) {
				blackboard[i][j] = letterArr[j];
			}
		}
		
		for (int x=0; x<15; x++) {
			for (int y=0; y<5; y++) {
			    String letter = blackboard[y][x];
			    
			    if (letter == null) continue;
			    
				System.out.print(letter);
			}
		}
	}
}
