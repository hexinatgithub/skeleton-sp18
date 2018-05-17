package byog.Core.RandomMap;

import byog.Core.Room;
import byog.Core.Size;
import byog.lab5.Position;

import java.util.Random;

public class RoomGenerator {
    HallWay hallway;
    Random random;

    public RoomGenerator(HallWay hallway, Random random) {
        this.hallway = hallway;
        this.random = random;
    }

    public Room generateRoom(int width, int height) {
        Position endPoint = getEndPoint();

        if (hallway.vector == HallWayVector.Left) {
            endPoint.X += width * hallway.vector.X;
            endPoint.Y += height * hallway.vector.Y;
        } else if (hallway.vector == HallWayVector.Down) {
            endPoint.Y += height * hallway.vector.Y;
        }else {
            endPoint.X += hallway.vector.X;
            endPoint.Y += hallway.vector.Y;
        }

        return new Room(endPoint, width, height);
    }

    public Room generateRoom(Size size) {
        return generateRoom(size.X, size.Y);
    }

    public Position getEndPoint() {
        // hallway direct to Left, just return the bottom Left point.
        if (hallway.vector == HallWayVector.Left) {
            return hallway.bottomLeftPosition();
        } else if (hallway.vector == HallWayVector.Down) {
            return hallway.bottomLeftPosition();
        }

        Position p = hallway.bottomLeftPosition();
        // offset from start position.
        p.X = p.X + hallway.vector.X * (hallway.getSize().X - 1);
        p.Y = p.Y + hallway.vector.Y * (hallway.getSize().Y - 1);
        return p;
    }

    public Position getStartPoint() {
        if (hallway.vector == HallWayVector.Left) {
            return hallway.bottomRightPosition();
        } else if (hallway.vector == HallWayVector.Down) {
            return hallway.upperLeftPosition();
        }

        return hallway.bottomLeftPosition();
    }
}
