package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.Position;

public class MapTileBuilder implements MapBuilder {
    public final int width;
    public final int height;
    public final TETile[][] mapTile;

    public MapTileBuilder(int width, int height) {
        this.width = width;
        this.height = height;
        mapTile = initializeMapTile(width, height);
    }

    private static TETile[][] initializeMapTile(int width, int height) {
        TETile[][] tiles = new TETile[width][height];
        // fill world with Tile of type NOTHING
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = Tileset.NOTHING;
            }
        }
        return tiles;
    }

    @Override
    public void insertRoom(Room room) {
        // insert four room wall
        insertWall(room);

        // insert room floor
        insertRoomSpace(room);
    }

    @Override
    public void insertWall(Room room) {
        Room.Wall wall = room.wall();
        insertWall(wall.bottomLeftPosition(), wall.upperLeftPosition());
        insertWall(wall.upperLeftPosition(), wall.upperRightPosition());
        insertWall(wall.upperRightPosition(), wall.bottomRightPosition());
        insertWall(wall.bottomRightPosition(), wall.bottomLeftPosition());
    }

    private void insertWall(Position startP, Position endP) {
        if (startP.X != endP.X && startP.Y != endP.Y) {
            throw new RuntimeException("wall should be horizontal or vertical.");
        }

        if (startP.X == endP.X) {
            insertVWall(startP, endP);
        } else {
            insertHWall(startP, endP);
        }
    }

    // insert horizontal wall.
    private void insertHWall(Position startP, Position endP) {
        // x direct
        int xDrt = endP.X - startP.X > 0 ? 1 : -1;
        Position p;
        for (int i = 0; i <= Math.abs(endP.X - startP.X); i++) {
            int offset = xDrt * i;
            p = new Position(startP.X + offset, startP.Y);
            if (mapTile[p.X][p.Y] == Tileset.NOTHING) {
                mapTile[p.X][p.Y] = Tileset.WALL;
            }
        }
    }

    // insert vertical wall.
    private void insertVWall(Position startP, Position endP) {
        // y direct
        int yDrt = endP.Y - startP.Y > 0 ? 1 : -1;
        Position p;
        for (int i = 0; i <= Math.abs(endP.Y - startP.Y); i++) {
            int offset = yDrt * i;
            p = new Position(startP.X, startP.Y + offset);
            if (mapTile[p.X][p.Y] == Tileset.NOTHING) {
                mapTile[p.X][p.Y] = Tileset.WALL;
            }
        }
    }

    // insert room floor
    private void insertRoomSpace(Room room) {
        for (int i = 0; i < room.space.size.X; i++) {
            for (int j = 0; j < room.space.size.Y; j++) {
                Position p = new Position(room.space.position.X + i, room.space.position.Y + j);
                mapTile[p.X][p.Y] = room.floor;
            }
        }
    }

    @Override
    public TETile[][] getMapTile() {
        return mapTile;
    }
}
