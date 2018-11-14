package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    public static final int BLANK = 0;

    private int[][] tiles;
    private int size;
    private int distance;

    public Board(int[][] tiles) {
        this.size = tiles.length;
        this.tiles = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
        this.distance = manhattan();
    }

    public int tileAt(int i, int j) {
        if (inBord(i) && inBord(j)) {
            return tiles[i][j];
        }
        throw new java.lang.IndexOutOfBoundsException();
    }

    private boolean inBord(int i) {
        return 0 <= i && i <= (size - 1);
    }

    public int size() {
        return size;
    }

    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                    break;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public boolean wrongPlace(int i, int j) {
        boolean isBlank = i == size - 1 && j == size - 1;
        int expect = isBlank ? BLANK : i * size + j + 1;
        return expect != tileAt(i, j);
    }

    public int hamming() {
        int estimate = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (wrongPlace(i, j)) estimate++;
            }
        }
        return estimate;
    }

    public int goalPositionH(int tile) {
        if (tile == BLANK) {
            return size - 1;
        }
        return (tile % size) == 0 ? size - 1 : (tile % size) - 1;
    }

    public int goalPositionV(int tile) {
        if (tile == BLANK) {
            return size - 1;
        }
        return (tile % size) == 0 ? (tile / size) - 1 : (tile / size);
    }

    public int distances(int i, int j) {
        int tile = tileAt(i, j);
        if (tile == BLANK) {
            return 0;
        }
        return Math.abs(i - goalPositionV(tile)) + Math.abs(j - goalPositionH(tile));
    }

    public int manhattan() {
        int estimate = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                estimate += distances(i, j);
            }
        }
        return estimate;
    }

    @Override
    public int estimatedDistanceToGoal() {
        return distance;
    }

    @Override
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null || getClass() != y.getClass()) {
            return false;
        }

        Board b = (Board) y;
        if (size != b.size) return false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tileAt(i, j) != b.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the string representation of the board.
     * Uncomment this method.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
