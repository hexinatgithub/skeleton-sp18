package byog.Core;

import byog.lab5.Position;

public class RoomBranch {
    Position position;
    Vector vector;
    Room room;

    public RoomBranch(Room room, Position position, Vector vector) {
        this.room = room;
        this.position = position;
        this.vector = vector;
    }

    public Room generateRoom(int width, int height) {
        int x = position.X;
        int y = position.Y;

        if (vector.X < 0) {
            x = x + vector.X * (width - 1);
        }

        if (vector.Y < 0) {
            y = y + vector.Y * (height - 1);
        }

        Position p = new Position(x, y);
        return new Room(p, width, height);
    }
}
