package byog.Core;

import byog.lab5.Position;

import java.util.ArrayList;
import java.util.List;

public class Map {
    public int width;
    public int height;
    public List<Room> rooms;

    /**
     * Return a initialize world fill with Tile of type NOTHING.
     */
    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.rooms = new ArrayList<>();
    }

    public boolean canAddRoom(Room room) {
        return !isExceedWorld(room) && !isOverlapWithOtherRoom(room);
    }

    private boolean isExceedWorld(Room room) {
        Wall wall = room.wall();
        Position wallBottomLeft = wall.bottomLeftPosition();
        Position wallUpperRight = wall.upperRightPosition();
        boolean isWBLExceed = wallBottomLeft.X < 0 || wallBottomLeft.Y < 0;
        boolean isWURExceed = wallUpperRight.X >= width || wallUpperRight.Y >= height;
        return  isWBLExceed || isWURExceed;
    }

    private boolean isOverlapWithOtherRoom(Room room) {
        for (Room or : rooms) {
            if (or.overlap(room)) {
                return true;
            }
        }
        return false;
    }

    public void addRoom(Room room) {
        if (!canAddRoom(room)) {
            throw new RuntimeException("room can't add to this world");
        }

        rooms.add(room);
        Room.index++;
    }

    public void constructMapTile(MapBuilder builder) {
        for (Room room: rooms) {
            builder.insertRoom(room);
        }
    }
}
