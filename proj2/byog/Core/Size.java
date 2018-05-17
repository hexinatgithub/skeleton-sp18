package byog.Core;

public class Size {
    public int X;
    public int Y;

    public Size(int x, int y) {
        X = x;
        Y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Size other = (Size)obj;
        return other.Y == Y && other.X == X;
    }
}
