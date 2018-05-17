package byog.Core;

import byog.lab5.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestRoom {
    @Test
    public void testOverlap() {
        Room r1 = new Room(new Position(1, 1), 2, 3);
        Room r2 = new Room(new Position(3, 3), 6, 5);

        // y overlap?
        assertTrue(r2.overlap(r1));

        r2 = new Room(new Position(3, 4), 6, 5);
        assertTrue(r2.overlap(r1));

        r2 = new Room(new Position(3, 6), 6, 5);
        assertFalse(r2.overlap(r1));

        // x overlap
        r1 = new Room(new Position(1, 3), 2, 3);
        r2 = new Room(new Position(3, 3), 6, 5);

        assertTrue(r2.overlap(r1));

        r2 = new Room(new Position(5, 3), 6, 5);
        assertFalse(r2.overlap(r1));
    }

    @Test
    public void testWall() {
        Room r1 = new Room(new Position(1, 1), 1, 2);
        Room.Wall wall = r1.wall();
        assertEquals(wall.wallRect, new Rectangle(new Position(0, 0), 3, 4));
    }

    @Test
    public void testCornerPosition() {
        Room r = new Room(new Position(1, 1), 1, 2);
        assertEquals(r.bottomLeftPosition(), new Position(1, 1));
        assertEquals(r.bottomRightPosition(), new Position(1, 1));
        assertEquals(r.upperLeftPosition(), new Position(1, 2));
        assertEquals(r.upperRightPosition(), new Position(1, 2));
    }
}
