package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class BOJ19637 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		int numOfNick = Integer.parseInt(input[0]);
		int numOfChar = Integer.parseInt(input[1]);
		
		Map<Integer, String> nickMap = new TreeMap<>();
		
		for (int i=0; i<numOfNick; i++) {
			input = br.readLine().split(" ");
			nickMap.put(Integer.parseInt(input[1]), input[0]);
		}
		
		for (int i=0; i<numOfChar; i++) {
			int power = Integer.parseInt(br.readLine());
			
			for (Entry<Integer, String> entry : nickMap.entrySet()) {
				int prevLimit = -1;
				
			    if (entry.getKey() > power && prevLimit != -1) {
			    	System.out.println(entry.getValue());
			    	break;
			    } else {
			    	prevLimit = entry.getKey();
			    }
			}
		}
	}
}
