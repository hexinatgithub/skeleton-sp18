package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.Position;

import java.awt.*;

public class Room {
    // room space
    public Rectangle space;
    // room floor tile
    public TETile floor;
    public static int index = 0;

    public Room(Position position, int width, int height) {
        space = new Rectangle(position, width, height);
//        char c = (char)('1' + index);
//        TETile tile= new TETile(c, Color.white, Color.black, "player");
//        this.floor = tile;
        this.floor = Tileset.FLOOR;
    }

    public boolean overlap(Room room) {
        return wall().overlap(room.wall());
    }

    public Position bottomLeftPosition() {
        return space.bottomLeftPosition();
    }

    public Position upperRightPosition() {
        return space.upperRightPosition();
    }

    public Position upperLeftPosition() {
        return space.upperLeftPosition();
    }

    public Position bottomRightPosition() {
        return space.bottomRightPosition();
    }

    public Size getSize() {
        return space.getSize();
    }

    protected Wall wall() {
        return new Wall();
    }

    protected class Wall {
        public Rectangle wallRect;

        public Wall() {
            Position position = new Position(space.position.X - 1, space.position.Y - 1);
            int width = space.size.X + 2;
            int height = space.size.Y + 2;
            wallRect = new Rectangle(position, width, height);
        }

        public boolean overlap(Wall wall) {
            return wallRect.collision(wall.wallRect);
        }

        public Position bottomLeftPosition() {
            return wallRect.bottomLeftPosition();
        }

        public Position upperRightPosition() {
            return wallRect.upperRightPosition();
        }

        public Position upperLeftPosition() {
            return wallRect.upperLeftPosition();
        }

        public Position bottomRightPosition() {
            return wallRect.bottomRightPosition();
        }
    }
}
