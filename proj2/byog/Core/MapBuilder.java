package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public interface MapBuilder {

    void insertRoom(Room room);

    void insertWall(Room room);

    TETile[][] getMapTile();
}
