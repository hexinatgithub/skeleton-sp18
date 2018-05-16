package byog.lab5;

public class Position {
    public int X;
    public int Y;

    public Position() {
        X = 0;
        Y = 0;
    }

    public Position(int x, int y) {
        X = x;
        Y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Position other = (Position) obj;
        return other.X == X && other.Y == Y;
    }
}
