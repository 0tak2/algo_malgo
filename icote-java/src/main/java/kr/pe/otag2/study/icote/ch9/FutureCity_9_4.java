package kr.pe.otag2.study.icote.ch9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FutureCity_9_4 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs1 = br.readLine().split(" ");
        int totalNodes = Integer.parseInt(inputs1[0]); // <= 100
        int totalEdges = Integer.parseInt(inputs1[1]);

        int[][] graph = new int[totalNodes+1][totalNodes+1];
        for (int i=1; i<=totalNodes; i++) {
            for (int j=1; j<=totalNodes; j++) {
                graph[i][j] = i==j ? 0 : Integer.MAX_VALUE;
            }
        }

        for (int i=0; i<totalEdges; i++) {
            int[] inputEdge = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::valueOf)
                    .toArray();

            graph[inputEdge[0]][inputEdge[1]] = 1;
            graph[inputEdge[1]][inputEdge[0]] = 1;
        }

        String[] inputs2 = br.readLine().split(" ");
        int dest = Integer.parseInt(inputs2[0]);
        int pause = Integer.parseInt(inputs2[1]);

        // 플로이드 워셜
        // N번 노드를 거쳐 a에서 b로 가는 최단 거리로 테이블 갱신
        // 전체 노드 개수가 100개 이하 => 대략 100^3번 < 시간 제한 1초 (대략 10^9번)
        for (int n=1; n<=totalNodes; n++) {
            for (int a=1; a<=totalNodes; a++) {
                for (int b=1; b<=totalNodes; b++) {
                    int calculated = graph[a][n] == Integer.MAX_VALUE || graph[n][b] == Integer.MAX_VALUE
                            ? Integer.MAX_VALUE
                            : graph[a][n] + graph[n][b];
                    graph[a][b] = Integer.min(calculated, graph[a][b]);
                }
            }
        }

        long result = (long)graph[1][pause] + graph[pause][dest]; // 경유지까지 최단거리 + 경유지부터 목적지까지 최단거리
        if (result >= Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println();
    }
}
