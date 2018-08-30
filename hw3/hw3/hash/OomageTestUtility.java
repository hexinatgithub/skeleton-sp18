package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        double lower = oomages.size() / 50.0;
        double upper = oomages.size() / 2.5;
        int[] fractions = new int[M];

        for (int i = 0; i < M; i++) {
            fractions[i] = 0;
        }

        int bucketNum;
        for (Oomage o : oomages) {
            bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            fractions[bucketNum] += 1;
        }

        for (int i = 0; i < M; i++) {
            double f = fractions[i];
            if (!(lower < f && f < upper)) {
                return false;
            }
        }
        return true;
    }
}
