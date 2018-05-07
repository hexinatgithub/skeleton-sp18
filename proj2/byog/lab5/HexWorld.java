package byog.lab5;

import org.junit.Test;

import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    public static int hexRowWidth(int size, int rows) {
        int effectiveI = rows;
        if (rows >= size) {
            effectiveI = 2 * size - 1 - effectiveI;
        }

        return size + 2 * effectiveI;
    }

    private static int hexRowOffSet(int size, int rows) {
        int effectiveI = rows;
        if (rows >= size) {
            effectiveI = 2 * size - 1 - effectiveI;
        }
        return -effectiveI;
    }

    public static void addHexagon(TETile[][] world, Position position, int size) {
        if (size < 2) {
            throw new IllegalArgumentException("Hexagon size must be large than 1");
        }

        int totalRows = 2 * size;
        for (int yi = 0; yi < totalRows; yi += 1) {
            int rowY = position.Y + yi;
            int rowStartX = position.X + hexRowOffSet(size, yi);
            Position rowPosition = new Position(rowStartX, rowY);
            int rowWidth = hexRowWidth(size, yi);
            addRow(world, rowPosition, rowWidth);
        }
    }

    private static void addRow(TETile[][] world, Position position, int rowWidth) {
        for (int xi = 0; xi < rowWidth; xi += 1) {
            world[position.X + xi][position.Y] = Tileset.WALL;
        }
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(60, 30);

        // initialize tiles
        TETile[][] world = new TETile[60][30];
        for (int x = 0; x < 60; x += 1) {
            for (int y = 0; y < 30; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        addHexagon(world, new Position(7, 7), 5);

        // draws the world to the screen
        ter.renderFrame(world);
    }
}
