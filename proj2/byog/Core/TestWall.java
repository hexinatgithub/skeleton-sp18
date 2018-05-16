package byog.Core;

import byog.lab5.Position;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestWall {
    @Test
    public void testCornerPosition() {
        Wall wall = new Wall(new Position(0, 0), 3, 4);
        assertEquals(wall.bottomLeftPosition(), new Position(0, 0));
        assertEquals(wall.bottomRightPosition(), new Position(2, 0));
        assertEquals(wall.upperLeftPosition(), new Position(0, 3));
        assertEquals(wall.upperRightPosition(), new Position(2, 3));
    }
}
