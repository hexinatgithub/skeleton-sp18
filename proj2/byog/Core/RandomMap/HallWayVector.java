package byog.Core.RandomMap;

public class HallWayVector {
    public int X;
    public int Y;

    // four direction vector
    public static HallWayVector Up = new HallWayVector(0, 1);
    public static HallWayVector Down = new HallWayVector(0, -1);
    public static HallWayVector Left = new HallWayVector(-1, 0);
    public static HallWayVector Right = new HallWayVector(1, 0);

    private HallWayVector(int x, int y) {
        X = x;
        Y = y;
    }

    @Override
    public boolean equals(Object obj) {
        HallWayVector other = (HallWayVector)obj;
        return other.Y == Y && other.X == X;
    }
}
