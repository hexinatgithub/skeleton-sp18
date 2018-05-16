package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.Position;

import java.util.Random;

public class Room {
    // room space
    public Rectangle space;
    // room floor tile
    public TETile floor;
    public static int index = 0;

    public Room(Position position, int width, int height) {
        space = new Rectangle(position, width, height);
        TETile[] floors = {Tileset.GRASS, Tileset.FLOWER, Tileset.LOCKED_DOOR, Tileset.SAND, Tileset.TREE};
        this.floor = floors[index % 5];
    }

    public boolean overlap(Room room) {
        return space.collision(room.space);
    }

    public Wall wall() {
        Position bottomLeftWall = new Position(space.position.X - 1, space.position.Y - 1);
        int wallWidth = space.size.X + 2;
        int wallHeight = space.size.Y + 2;
        return new Wall(bottomLeftWall, wallWidth, wallHeight);
    }
}
