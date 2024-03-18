package kr.pe.otag2.study.greedy;

import java.util.PriorityQueue;

public class MuckbangLive42891_Book {
    static class Menu implements Comparable<Menu> {
        private final int id;
        private final int cost;

        public Menu(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }

        public int getId() {
            return id;
        }

        public int getCost() {
            return cost;
        }

        @Override
        public int compareTo(Menu o) {
            return Integer.compare(cost, o.getCost());
        }
    }

    public int solution(int[] food_times, long k) {
        int answer = 0;

        PriorityQueue<Menu> q = new PriorityQueue<>(200_000);
        for (int i = 0; i < food_times.length; i++) {
            q.offer(new Menu(i + 1, food_times[i]));
        }

        // todo: 산술적으로 제외할 수 있는 케이스를 바로 제외한다
        while (!q.isEmpty()) {
            Menu m = q.peek();
            System.out.println(m.getId() + "번 필요 시간: " + m.getCost());
            int cost = m.getCost() * q.size(); // 걸리는 시간 * 남은 메뉴 수

            if (cost <= k) { // 남은 시간이 충분한 경우
                q.poll();
                System.out.println(m.getId() + "번 메뉴 완료");
                k -= cost;
                continue;
            }

            break;
        }

        return answer;
    }

    public static void main(String[] args) {
        MuckbangLive42891_Book solution = new MuckbangLive42891_Book();
        int result = solution.solution(new int[] {3, 1, 2}, 5);
        System.out.println(result);
    }
}
