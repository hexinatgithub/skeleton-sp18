package byog.Core;

import byog.lab5.Position;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestRoom {
    @Test
    public void testOverlap() {
        Room r1 = new Room(new Position(1, 1), 1, 2);
        Room r2 = new Room(new Position(1, 3), 2, 3);
        Room r3 = new Room(new Position(3, 4), 1, 4);
        Room r4 = new Room(new Position(3, 3), 4, 1);
        Room r5 = new Room(new Position(2, 3), 5, 4);
        assertFalse(r5.overlap(r1));
        assertTrue(r5.overlap(r2));
        assertTrue(r5.overlap(r3));
        assertTrue(r5.overlap(r4));
    }

    @Test
    public void testWall() {
        Room r1 = new Room(new Position(1, 1), 1, 2);
        Wall wall = r1.wall();
        assertEquals(wall.wallRect, new Rectangle(new Position(0, 0), 3, 4));
    }
}
