package byog.Core;

import byog.lab5.Position;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestMap {
    @Test
    public void testCanAddRoom() {
        Map map = new Map(100, 100);

        // exceed world
        Room r1 = new Room(new Position(0, 0), 1, 2);
        assertFalse(map.canAddRoom(r1));
        r1 = new Room(new Position(90, 90), 10, 2);
        assertFalse(map.canAddRoom(r1));
        r1 = new Room(new Position(90, 90), 1, 10);
        assertFalse(map.canAddRoom(r1));
        r1 = new Room(new Position(-1, 10), 1, 10);
        assertFalse(map.canAddRoom(r1));

        // fit in world
        Room r2 = new Room(new Position(1, 1), 1, 2);
        assertTrue(map.canAddRoom(r2));

        // overlap with other room
        Room r3 = new Room(new Position(3, 4), 1, 4);
        map.addRoom(r3);
        Room r4 = new Room(new Position(3, 3), 4, 1);
        map.addRoom(r4);
        Room r5 = new Room(new Position(2, 3), 5, 4);
        assertFalse(map.canAddRoom(r5));
        assertEquals(2, map.rooms.size());
    }
}
