package hw2;

import edu.princeton.cs.introcs.StdRandom;

public class PercolationsStatsUtil {
    /**
     * Open a N * N grid until it percolate.
     * @param perc N * N grid.
     * @param N grid edge size.
     * @return fraction of open sites in grid.
     */
    public static double openUntilPercolate(Percolation perc, int N) {
        int allSites = N * N;
        int row, col;
        while (!perc.percolates()) {
            // find not open site
            while (true) {
                row = StdRandom.uniform(N);
                col = StdRandom.uniform(N);
                if (!perc.isOpen(row, col)) {
                    break;
                }
            }
            perc.open(row, col);
        }
        return ((double) perc.numberOfOpenSites() / allSites);
    }

    /**
     * Run experiment T time and return a array contain T experiment fraction.
     * @param pf PercolationFactory to make grid.
     * @param N grid edge size.
     * @param T Run experiment T times.
     * @return A array contain T experiment fractions of open site.
     */
    public static double[] experiment(PercolationFactory pf, int N, int T) {
        Percolation perc;
        double[] fractions = new double[T];
        for (int i = 0; i < T; i++) {
            perc = pf.make(N);
            fractions[i] = openUntilPercolate(perc, N);
        }
        return fractions;
    }
}
