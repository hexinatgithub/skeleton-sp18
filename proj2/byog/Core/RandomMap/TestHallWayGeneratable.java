package byog.Core.RandomMap;

import byog.lab5.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestHallWayGeneratable {
    @Test
    public void testGenerateHallWay() {
        // left hallway
        Position p = new Position(10, 10);
        HallWayVector vector = HallWayVector.Left;
        HallWayGeneratable generatable = new HallWayGeneratable(p, vector);
        HallWay hw = generatable.generateHallWay(10);

        assertEquals(10, hw.getSize().X);
        assertEquals(1, hw.getSize().Y);
        assertEquals(new Position(1, 10), hw.bottomLeftPosition());

        // right hall way
        vector = HallWayVector.Right;
        generatable = new HallWayGeneratable(p, vector);
        hw = generatable.generateHallWay(10);
        assertEquals(10, hw.getSize().X);
        assertEquals(1, hw.getSize().Y);
        assertEquals(new Position(10, 10), hw.bottomLeftPosition());

        // up hall way
        vector = HallWayVector.Up;
        generatable = new HallWayGeneratable(p, vector);
        hw = generatable.generateHallWay(10);
        assertEquals(1, hw.getSize().X);
        assertEquals(10, hw.getSize().Y);
        assertEquals(new Position(10, 10), hw.bottomLeftPosition());

        // down hall way
        vector = HallWayVector.Down;
        generatable = new HallWayGeneratable(p, vector);
        hw = generatable.generateHallWay(10);
        assertEquals(1, hw.getSize().X);
        assertEquals(10, hw.getSize().Y);
        assertEquals(new Position(10, 1), hw.bottomLeftPosition());
    }
}
