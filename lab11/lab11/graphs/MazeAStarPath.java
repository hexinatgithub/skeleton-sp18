package lab11.graphs;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.MinPQ;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private IndexMinPQ<Integer> minPQ;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        minPQ = new IndexMinPQ<>(maze.V());
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int sourceX = maze.toX(v);
        int sourceY = maze.toY(v);
        int targetX = maze.toX(t);
        int targetY = maze.toY(t);
        return Math.abs(sourceX - targetX) + Math.abs(sourceY - targetY);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return minPQ.delMin();
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        marked[s] = true;
        announce();
        minPQ.insert(s, h(s));

        while (!minPQ.isEmpty()) {
            int v = findMinimumUnmarked();
            if (v == t) {
                targetFound = true;
                return;
            }

            for (int u: maze.adj(v)) {
                if (!marked[u]) {
                    marked[u] = true;
                    edgeTo[u] = v;
                    distTo[u] = distTo[v] + 1;
                    minPQ.insert(u, h(u));
                    announce();
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

