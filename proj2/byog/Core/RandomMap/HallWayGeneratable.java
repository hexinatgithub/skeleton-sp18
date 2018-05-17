package byog.Core.RandomMap;

import byog.Core.Size;
import byog.lab5.Position;

public class HallWayGeneratable {
    Position position;
    HallWayVector vector;

    public HallWayGeneratable(Position position, HallWayVector vector) {
        this.position = position;
        this.vector = vector;
    }

    public HallWay generateHallWay(int length) {
        Position p = new Position(0, 0);

        if (vector == HallWayVector.Left) {
            p.X = position.X + vector.X * (length - 1);
            p.Y = position.Y;
            return new HallWay(p, length, 1, vector);
        } else if (vector == HallWayVector.Down) {
            p.X = position.X;
            p.Y = position.Y + vector.Y * (length - 1);
            return new HallWay(p, 1, length, vector);
        } else if (vector == HallWayVector.Up) {
            p.X = position.X;
            p.Y = position.Y;
            return new HallWay(p, 1, length, vector);
        } else {
            p.X = position.X;
            p.Y = position.Y;
            return new HallWay(p, length, 1, vector);
        }
    }
}
