package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;
import java.util.List;

public class Percolation {
    private SiteState[][] GridState;
    private WeightedQuickUnionUF GridUF1;
    private WeightedQuickUnionUF GridUF2;
    private int N;
    private int numberOfOpenSites;

    private enum SiteState {
        Open,
        Blocked
    }

    private class SitePosition {
        private int row;
        private int col;

        public SitePosition(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N <= 0");
        }

        GridState = new SiteState[N][N];
        for (int i = 0; i < GridState.length; i++) {
            SiteState[] row = GridState[i];
            for (int j = 0; j < row.length; j++) {
                row[j] = SiteState.Blocked;
            }
        }
        GridUF1 = new WeightedQuickUnionUF(N * N + 2);
        GridUF2 = new WeightedQuickUnionUF(N * N + 1);
        this.N = N;
        numberOfOpenSites = 0;
    }

    private boolean inGrid(int row, int col) {
        return (0 <= row && row < N) &&
                (0 <= col && col < N);
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!inGrid(row, col)) {
            throw new IndexOutOfBoundsException();
        }

        if (isOpen(row, col)) {
            return;
        }

        int index = xyTo1D(row, col);
        GridState[row][col] = SiteState.Open;
        numberOfOpenSites++;
        for (SitePosition p: openNeighbors(row, col)) {
            int nIndex = xyTo1D(p.row, p.col);
            GridUF1.union(index, nIndex);
            GridUF2.union(index, nIndex);
        }

        if (row == 0) {
            GridUF1.union(index, virtualTopSite1D());
            GridUF2.union(index, virtualTopSite1D());
        }

        if (row == N - 1) {
            GridUF1.union(index, virtualBottomSite1D());
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!inGrid(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return GridState[row][col] == SiteState.Open;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!inGrid(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return GridUF2.connected(xyTo1D(row, col), virtualTopSite1D());
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return GridUF1.connected(virtualTopSite1D(), virtualBottomSite1D());
    }

    private List<SitePosition> openNeighbors(int row, int col) {
        List<SitePosition> result = new ArrayList<>();
        int tmpRow, tmpCol;
        // top
        tmpRow = row - 1;
        tmpCol = col;
        if (inGrid(tmpRow, tmpCol) && isOpen(tmpRow, tmpCol)) {
            result.add(new SitePosition(tmpRow, tmpCol));
        }
        // down
        tmpRow = row + 1;
        tmpCol = col;
        if (inGrid(tmpRow, tmpCol) && isOpen(tmpRow, tmpCol)) {
            result.add(new SitePosition(tmpRow, tmpCol));
        }
        // left
        tmpRow = row;
        tmpCol = col - 1;
        if (inGrid(tmpRow, tmpCol) && isOpen(tmpRow, tmpCol)) {
            result.add(new SitePosition(tmpRow, tmpCol));
        }
        // right
        tmpRow = row;
        tmpCol = col + 1;
        if (inGrid(tmpRow, tmpCol) && isOpen(tmpRow, tmpCol)) {
            result.add(new SitePosition(tmpRow, tmpCol));
        }

        return result;
    }

    private int virtualTopSite1D() {
        return xyTo1D(N, 0);
    }

    private int virtualBottomSite1D() {
        return xyTo1D(N, 1);
    }

    private int xyTo1D(int row, int col) {
        return row * N + col;
    }

    // use for unit testing (not required)
    public static void main(String[] args) {

    }
}
