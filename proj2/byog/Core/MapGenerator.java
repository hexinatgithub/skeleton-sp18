package byog.Core;

import byog.lab5.Position;

import java.util.Random;

public class MapGenerator {
    public static final int WIDTH = 100;
    public static final int HEIGHT = 60;
    private Random random;

    public MapGenerator(int seed) {
        random = new Random(seed);
    }

    private void generateRandomMap(Map map, RoomBranch branch) {
        int width = RandomUtils.uniform(random, 1, 5);
        int height = RandomUtils.uniform(random, 1, 5);
        Room room = branch.generateRoom(width, height);

        if (!map.canAddRoom(room)) {
            return;
        }

        map.addRoom(room);
        int numBranches = RandomUtils.uniform(random, 2, 5);
        RoomBranch[] branches = new RoomBranchGenerator(room, random).randomBranch(numBranches);
        for (RoomBranch br : branches) {
            generateRandomMap(map, br);
        }
    }

    public Map generateRandomMap() {
        Map map = new Map(WIDTH, HEIGHT);
        // start point to generate room.
        RoomBranch startBranch = startBranch();
        generateRandomMap(map, startBranch);
        return map;
    }

    private RoomBranch startBranch() {
        int x = RandomUtils.uniform(random, 1, WIDTH / 2);
        int y = RandomUtils.uniform(random, 1, HEIGHT / 2);
        return new RoomBranch(new Position(x, y), Vector.upRight);
    }
}
