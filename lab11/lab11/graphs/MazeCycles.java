package lab11.graphs;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private boolean cycleFound;

    public MazeCycles(Maze m) {
        super(m);
        cycleFound = false;
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        if (maze.N() == 0)
            return;
        dfs2(0);
    }

    // Helper methods go here
    private void dfs2(int v) {
        marked[v] = true;
        announce();

        for (int u : maze.adj(v)) {
            if (marked[u] && edgeTo[v] != u) {
                edgeTo[u] = v;
                cycleFound = true;
                clearPath(u);
                announce();
                break;
            }

            if (!marked[u]) {
                edgeTo[u] = v;
                announce();
                dfs2(u);
            }

            if (cycleFound) break;
        }
    }

    private void clearPath(int cycleV) {
        int[] tmp = new int[maze.V()];
        for (int i = 0; i < maze.V(); i += 1) {
            tmp[i] = Integer.MAX_VALUE;
        }

        int tv = cycleV;
        while (edgeTo[tv] != cycleV) {
            tmp[tv] = edgeTo[tv];
            tv = edgeTo[tv];
        }
        tmp[tv] = cycleV;
        edgeTo = tmp;
    }

}

