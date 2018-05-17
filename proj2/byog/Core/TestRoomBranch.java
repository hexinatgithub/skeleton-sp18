package byog.Core;

import byog.lab5.Position;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestRoomBranch {
    @Test
    public void testGenerateRoom() {
        Position p = new Position(10, 10);
        Vector v = Vector.upRight;
        RoomBranch branch = new RoomBranch(null, p, v);
        Room room = branch.generateRoom(10, 10);

        assertEquals(room.space, new Rectangle(p, 10, 10));
    }
}
