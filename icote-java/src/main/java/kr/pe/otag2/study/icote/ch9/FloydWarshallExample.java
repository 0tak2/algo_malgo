package kr.pe.otag2.study.icote.ch9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FloydWarshallExample {
    public static void main(String[] args) throws IOException {
        // 입력 형식
        // 전체 노드 수
        // 간선 수
        // 출발점 도착점 비용
        // 출발점 도착점 비용
        // ...반복...

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int totalNodes = Integer.parseInt(br.readLine());
        int totalEdges = Integer.parseInt(br.readLine());

        int[][] graph = new int[totalNodes + 1][totalNodes + 1]; // 행=출발점 열=도착점 셀=비용
        for (int i=1; i<=totalNodes; i++) {
            for (int j=1; j<=totalNodes; j++) {
                if (i == j) {
                    graph[i][j] = 0; // 자기 자신에서 자기 자신으로 가는 경우 비용이 0이 필요하다고 정의
                    continue;
                }
                graph[i][j] = Integer.MAX_VALUE; // 그 외의 경우에는 무한대로 초기화
            }
        }

        for (int i=0; i<totalEdges; i++) {
            String[] line = br.readLine().split(" ");
            int from = Integer.parseInt(line[0]);
            int to = Integer.parseInt(line[1]);
            int cost = Integer.parseInt(line[2]);

            graph[from][to] = cost;
        }

        // 중간에 N번 노드를 거쳐가는 경우가 최단거리인 경우 갱신
        // 점화식: D_ab = min(D_ab, D_ak + D_kb)
        for (int i=1; i<=totalNodes; i++) {
            for (int a=1; a<=totalNodes; a++) {
                for (int b=1; b<=totalNodes; b++) {
                    int target = graph[a][i] == Integer.MAX_VALUE || graph[i][b] == Integer.MAX_VALUE
                            ? Integer.MAX_VALUE
                            : graph[a][i] + graph[i][b];
                    graph[a][b] = Integer.min(graph[a][b], target);
                }
            }
        }

        for (int i=1; i<=totalNodes; i++) {
            // 첫 행 출력 시작
            if (i == 1) {
                System.out.print("거리\t\t");
                for (int k=1; k<=totalNodes; k++) {
                    System.out.print("[" + k + "]" + "\t\t");
                }
                System.out.print("(도착점)");
                System.out.println();
            }
            // 첫 행 출력 끝

            for (int j=1; j<=totalNodes; j++) {
                // 첫 열 출력 시작
                if (j == 1) System.out.print("[" + i + "]" + "\t\t");
                // 첫 열 출력 끝

                System.out.print(graph[i][j] + "\t\t");
            }
            System.out.println();
        }
        System.out.println("(시작점)");
    }
}
