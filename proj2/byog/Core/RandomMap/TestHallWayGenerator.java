package byog.Core.RandomMap;

import byog.Core.Room;
import byog.lab5.Position;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class TestHallWayGenerator {
    @Test
    public void testCandidate() {
        Position p = new Position(10, 10);
        Room room = new Room(p, 10, 10);
        HallWayGenerator generator = new HallWayGenerator(room, new Random(20));

        // test bottom
        HallWayGenerator.HallWayCandidate candidate = generator.bottomCandidate();
        for (int i = 0; i < 10; i++) {
            HallWayGeneratable generatable = candidate.getHallWayGeneratable();
            assertEquals(HallWayVector.Down, generatable.vector);
            assertTrue(10 <= generatable.position.X && generatable.position.X <= 19);
            assertTrue(generatable.position.Y == 9);
        }

        // test up
        candidate = generator.upperCandidate();
        for (int i = 0; i < 10; i++) {
            HallWayGeneratable generatable = candidate.getHallWayGeneratable();
            assertEquals(HallWayVector.Up, generatable.vector);
            assertTrue(10 <= generatable.position.X && generatable.position.X <= 19);
            assertTrue(generatable.position.Y == 20);
        }

        // test left
        candidate = generator.leftCandidate();
        for (int i = 0; i < 10; i++) {
            HallWayGeneratable generatable = candidate.getHallWayGeneratable();
            assertEquals(HallWayVector.Left, generatable.vector);
            assertTrue(10 <= generatable.position.Y && generatable.position.Y <= 19);
            assertTrue(generatable.position.X == 9);
        }

        // test right
        candidate = generator.rightCandidate();
        for (int i = 0; i < 10; i++) {
            HallWayGeneratable generatable = candidate.getHallWayGeneratable();
            assertEquals(HallWayVector.Right, generatable.vector);
            assertTrue(10 <= generatable.position.Y && generatable.position.Y <= 19);
            assertTrue(generatable.position.X == 20);
        }
    }
}
