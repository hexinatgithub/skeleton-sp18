package hw2;

//import edu.princeton.cs.introcs.StdOut;

import edu.princeton.cs.introcs.StdStats;
//import edu.princeton.cs.introcs.Stopwatch;

public class PercolationStats {
    private int T;
    private int N;
    private double[] fractions;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.T = T;
        this.N = N;
        this.fractions = PercolationsStatsUtil.experiment(pf, N, T);
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double mean = mean();
        double stddev = stddev();
        return mean - (1.96 * stddev) / Math.sqrt(T);
    }

    // high endpoint of 95%
    public double confidenceHigh() {
        double mean = mean();
        double stddev = stddev();
        return mean + (1.96 * stddev) / Math.sqrt(T);
    }

//    private static void main(String[] args) {
//        Stopwatch timer = new Stopwatch();
//        PercolationStats stats = new PercolationStats(20, 100, new PercolationFactory());
//        double high = stats.confidenceHigh();
//        double low = stats.confidenceLow();
//        StdOut.printf("high: %f, low: %f\n", high, low);
//        double time = timer.elapsedTime();
//        StdOut.printf("elapse: (%.2f seconds)\n", time);
//    }
}
