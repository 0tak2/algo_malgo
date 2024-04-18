package kr.pre.otag2.study.acmicpc.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Virus_2606_BFS {
    private static List<List<Integer>> net;
    private static boolean[] visited;
    private static final int START_COM = 1;

    private static int bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(START_COM);
        visited[START_COM] = true;
        int count = 1;

        while (!q.isEmpty()) {
            int computerId = q.poll();
            List<Integer> connectedWithThis = net.get(computerId);

            for (int idx : connectedWithThis) {
                if (visited[idx]) {
                    continue;
                }

                visited[idx] = true;
                count++;
                q.add(idx);
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numOfComputers = Integer.parseInt(br.readLine());
        int numOfEdges = Integer.parseInt(br.readLine());

        net = new ArrayList<>(numOfComputers);
        for (int i = 0; i <= numOfComputers; i++) {
            net.add(new ArrayList<>());
        }

        visited = new boolean[numOfComputers + 1];

        for (int i = 0; i < numOfEdges; i++) {
            String[] inputs = br.readLine().split(" ");
            int comAId = Integer.parseInt(inputs[0]);
            int comBId = Integer.parseInt(inputs[1]);

            List<Integer> connectedWithA = net.get(comAId);
            connectedWithA.add(comBId);

            List<Integer> connectedWithB = net.get(comBId);
            connectedWithB.add(comAId);
        }

        System.out.println(bfs() - 1);
    }
}
