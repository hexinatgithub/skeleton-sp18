package byog.Core;

import byog.TileEngine.TETile;

public interface MapBuilder {

    void insertRoom(Room room);

    void insertWall(Room room);

    TETile[][] getMapTile();
}
