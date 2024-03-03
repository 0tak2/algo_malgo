package kr.pe.otag2.study.icote.ch10;

public record Edge (
        int node1,
        int node2,
        int cost
) implements Comparable<Edge> {
    @Override
    public int compareTo(Edge o) {
        return Integer.compare(cost, o.cost());
    }
}
