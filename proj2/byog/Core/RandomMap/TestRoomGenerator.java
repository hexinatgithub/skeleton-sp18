package byog.Core.RandomMap;

import byog.Core.Rectangle;
import byog.Core.Room;
import byog.Core.Size;
import byog.lab5.Position;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class TestRoomGenerator {
    @Test
    public void testTwoEnd() {
        Position p = new Position(10, 10);
        HallWay hw = new HallWay(p, 5, 1, HallWayVector.Left);
        RoomGenerator rg = new RoomGenerator(hw, new Random(131));
        assertEquals(new Position(14, 10), rg.getStartPoint());
        assertEquals(new Position(10, 10), rg.getEndPoint());

        hw = new HallWay(p, 1, 5, HallWayVector.Down);
        rg = new RoomGenerator(hw, new Random(131));
        assertEquals(new Position(10, 14), rg.getStartPoint());
        assertEquals(new Position(10, 10), rg.getEndPoint());

        hw = new HallWay(p, 1, 5, HallWayVector.Up);
        rg = new RoomGenerator(hw, new Random(131));
        assertEquals(new Position(10, 10), rg.getStartPoint());
        assertEquals(new Position(10, 14), rg.getEndPoint());

        hw = new HallWay(p, 5, 1, HallWayVector.Right);
        rg = new RoomGenerator(hw, new Random(131));
        assertEquals(new Position(10, 10), rg.getStartPoint());
        assertEquals(new Position(14, 10), rg.getEndPoint());
    }

    @Test
    public void testGenerateRoom() {
        Position p = new Position(10, 10);

        // test Left hallway
        HallWay hw = new HallWay(p, 5, 1, HallWayVector.Left);
        RoomGenerator rg = new RoomGenerator(hw, new Random(131));
        Room r = rg.generateRoom(new Size(2, 3));

        assertEquals(r.space, new Rectangle(new Position(8, 10), 2, 3));

        // test Right hallway
        hw = new HallWay(p, 5, 1, HallWayVector.Right);
        rg = new RoomGenerator(hw, new Random(131));
        r = rg.generateRoom(2, 3);

        assertEquals(r.space, new Rectangle(new Position(15, 10), 2, 3));

        // test up hallway
        hw = new HallWay(p, 1, 5, HallWayVector.Up);
        rg = new RoomGenerator(hw, new Random(131));
        r = rg.generateRoom(2, 3);

        assertEquals(r.space, new Rectangle(new Position(10, 15), 2, 3));

        // test down hallway
        hw = new HallWay(p, 1, 5, HallWayVector.Down);
        rg = new RoomGenerator(hw, new Random(131));
        r = rg.generateRoom(2, 3);

        assertEquals(r.space, new Rectangle(new Position(10, 7), 2, 3));
    }
}
