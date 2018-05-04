public class OffByN implements CharacterComparator {
    int n;

    public OffByN(int N) {
        n = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = Math.abs(x - y);
        return diff == n;
    }
}
