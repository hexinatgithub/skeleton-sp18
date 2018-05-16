package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.Position;

import java.util.Random;

public class MapGenerator {
    public static final int WIDTH = 60;
    public static final int HEIGHT = 60;
    private Random random;

    public MapGenerator(int seed) {
        random = new Random(seed);
    }

    private void generateRandomMap(Map map, Position startPoint, int tryTime) {
        if (tryTime == 0) {
            return;
        }

        Room room = generateRandomRoom(startPoint);

        if (!map.canAddRoom(room)) {
            generateRandomMap(map, startPoint, tryTime - 1);
            return;
        }

        map.addRoom(room);
        Position[] bps = branchFromRoom(room, RandomUtils.uniform(random, 5));
        for (Position bp : bps) {
            generateRandomMap(map, bp);
        }
    }

    private void generateRandomMap(Map map, Position startPoint) {
        generateRandomMap(map, startPoint, 4);
    }

    public Map generateRandomMap() {
        Map map = new Map(WIDTH, HEIGHT);
        // start point to generate room.
        Position startPoint = randomStartPoint();
        generateRandomMap(map, startPoint);
        return map;
    }

    private Position randomStartPoint() {
        int x = RandomUtils.uniform(random, 1, WIDTH);
        int y = RandomUtils.uniform(random, 1, HEIGHT);
        return new Position(1, 1);
    }

    private Room generateRandomRoom(Position position) {
        int roomWidth = RandomUtils.uniform(random, 1, 6);
        int roomHeight = RandomUtils.uniform(random, 1, 6);
        return new Room(position, roomWidth, roomHeight);
    }

    /**
     * Branch from a room to generate new Room.
     *
     * @param room      a Room
     * @param numBranch not beyond 4, if larger than 4 then numBranch will set to 4.
     * @return a Position Array contain the branched Room start Position.
     */
    private Position[] branchFromRoom(Room room, int numBranch) {
        Position p1, p2;
        Position[][] branchs = new Position[4][];
        Wall wall = room.wall();
        Position[] result = new Position[numBranch];

        // bottom branch line
        p1 = wall.bottomLeftPosition();
        p1.X += 1;
        p2 = wall.bottomRightPosition();
        p2.X -= 1;
        Position[] bb = {p1, p2};
        branchs[0] = bb;

        // upper branch line
        p1 = wall.upperLeftPosition();
        p1.X += 1;
        p2 = wall.upperRightPosition();
        p2.X -= 1;
        Position[] ub = {p1, p2};
        branchs[1] = ub;

        // left branch line
        p1 = wall.bottomLeftPosition();
        p1.Y += 1;
        p2 = wall.upperLeftPosition();
        p2.Y -= 1;
        Position[] fb = {p1, p2};
        branchs[2] = fb;

        // right branch line
        p1 = wall.bottomRightPosition();
        p1.Y += 1;
        p2 = wall.upperRightPosition();
        p2.Y -= 1;
        Position[] rb = {p1, p2};
        branchs[3] = rb;

        RandomUtils.shuffle(random, branchs);

        // from each side generator a branch
        for (int i = 0; i < numBranch; i++) {
            Position rp = branchRandomSelect(branchs[i][0], branchs[i][1]);
            result[i] = rp;
        }

        return result;
    }

    private Position branchRandomSelect(Position startP, Position endP) {
        if (startP.X != endP.X && startP.Y != endP.Y) {
            throw new RuntimeException("branch select should be horizontal or vertical.");
        }

        if (startP.X == endP.X) {
            return verticalRandomSelect(startP, endP);
        }

        return horizontalRandomSelect(startP, endP);
    }

    // Random select a horizontal position between start and end position, inclusion two position.
    private Position horizontalRandomSelect(Position startP, Position endP) {
        // x direct.
        int xDrt = endP.X - startP.X > 0 ? 1 : -1;
        // x size.
        int xSize = Math.abs(endP.X - startP.X);
        // offset from start position.
        int offset = RandomUtils.uniform(random, Math.max(xSize, 1)) * xDrt;
        return new Position(startP.X + offset, startP.Y);
    }

    private Position verticalRandomSelect(Position startP, Position endP) {
        // y direct.
        int yDrt = endP.Y - startP.Y > 0 ? 1 : -1;
        // x size.
        int ySize = Math.abs(endP.Y - startP.Y);
        // offset from start position.
        int offset = RandomUtils.uniform(random, Math.max(ySize, 1)) * yDrt;
        return new Position(startP.X, startP.Y + offset);
    }
}
