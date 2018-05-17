package byog.Core.RandomMap;

import byog.Core.Map;
import byog.Core.RandomUtils;
import byog.Core.Room;
import byog.Core.Size;
import byog.lab5.Position;

import java.util.Random;

public class MapGenerator {
    public static final int WIDTH = 60;
    public static final int HEIGHT = 30;
    private Random random;

    public MapGenerator(int seed) {
        random = new Random(seed);
    }

    public Map generateRandomMap() {
        Map map = new Map(WIDTH, HEIGHT);
        Position startPoint = randomPoint();
        Room room = generateRandomRoom(startPoint);
        map.addRoom(room);
        generateRandomHallWay(map, room);
        return map;
    }

    public Room generateRandomRoom(Position p) {
        int x = RandomUtils.uniform(random, 1, 6);
        int y = RandomUtils.uniform(random, 1, 6);
        return new Room(p, x, y);
    }

    public void generateRandomRoom(Map map, HallWay hallWay) {
        RoomGenerator generator = new RoomGenerator(hallWay, random);
        for (int i = 0; i < 10; i++) {
            Room room = generator.generateRoom(randomSize());
            if (canAddRoom(map, hallWay, room)) {
                map.addRoom(room);
                generateRandomHallWay(map, room);
                break;
            }
        }
    }

    public void generateRandomHallWay(Map map, Room room) {
        HallWayGenerator generator = new HallWayGenerator(room, random);
        int num = RandomUtils.uniform(random, 4, 5);
        HallWayGeneratable[] generatable = generator.randomHallWay(num);
        for (HallWayGeneratable gtb : generatable) {
            for (int i = 0; i < 10; i++) {
                int length = RandomUtils.uniform(random, 1, 6);
                HallWay hallWay = gtb.generateHallWay(length);
                if (canAddRoom(map, room, hallWay)) {
                    map.addRoom(hallWay);
                    generateRandomRoom(map, hallWay);
                    break;
                }
            }
        }
    }

    private Position randomPoint() {
        int x = RandomUtils.uniform(random, 1, WIDTH / 2);
        int y = RandomUtils.uniform(random, 1, HEIGHT / 2);
        return new Position(x, y);
    }

    private boolean canAddRoom(Map map, Room fromRoom, Room newRoom) {
        for (Room or : map.rooms) {
            if (or != fromRoom && or.overlap(newRoom)) {
                return false;
            }
        }
        return !map.isExceedWorld(newRoom);
    }

    private Size randomSize() {
        int x = RandomUtils.uniform(random, 2, 6);
        int y = RandomUtils.uniform(random, 2, 6);
        return new Size(x, y);
    }
}
