package byog.Core;

public class Vector {
    public int X;
    public int Y;

    // four direction vector
    public static Vector upRight = new Vector(1, 1);
    public static Vector downRight = new Vector(1, -1);
    public static Vector upLeft = new Vector(-1, 1);
    public static Vector downLeft = new Vector(-1, -1);

    private Vector(int x, int y) {
        X = x;
        Y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Vector other = (Vector)obj;
        return other.Y == Y && other.X == X;
    }
}
