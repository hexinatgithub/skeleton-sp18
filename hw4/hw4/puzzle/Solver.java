package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    private static class SearchNode implements Comparable<SearchNode> {
        private WorldState ws;
        private int moves;
        private SearchNode parent;
        private int priority;

        public SearchNode(WorldState ws, int moves, SearchNode parent) {
            this.ws = ws;
            this.moves = moves;
            this.parent = parent;
            this.priority = priority();
        }

        @Override
        public int compareTo(SearchNode o) {
            return priority - o.priority;
        }

        private int priority() {
            return moves + ws.estimatedDistanceToGoal();
        }
    }

    private MinPQ<SearchNode> pq;
    private int moves;
    private Stack<WorldState> solution;
    private int enqueue;

    public Solver(WorldState initial) {
        pq = new MinPQ<>();
        moves = 0;
        solution = new Stack<>();
        enqueue = 0;

        SearchNode node = new SearchNode(initial, 0, null);
        pq.insert(node);
        while (true) {
            node = pq.delMin();
            if (node.ws.isGoal()) {
                moves = node.moves;
                break;
            }
            appendNeighbors(node);
        }

        SearchNode tmp = node;
        while (tmp != null) {
            solution.push(tmp.ws);
            tmp = tmp.parent;
        }
    }

    private void appendNeighbors(SearchNode n) {
        for (WorldState ws : n.ws.neighbors()) {
            if (n.parent != null && ws.equals(n.parent.ws)) continue;
            SearchNode nn = new SearchNode(ws, n.moves + 1, n);
            pq.insert(nn);
            enqueue++;
        }
    }

    public int moves() {
        return moves;
    }

    public int getEnqueue() {
        return enqueue;
    }

    public Iterable<WorldState> solution() {
        return solution;
    }
}
