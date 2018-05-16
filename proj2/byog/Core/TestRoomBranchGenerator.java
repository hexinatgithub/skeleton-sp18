package byog.Core;

import byog.lab5.Position;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;

public class TestRoomBranchGenerator {
    @Test
    public void testBranchCandidate() {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                Position p = new Position(i, j);
                Room room = new Room(p, i, j);
                RoomBranchGenerator generator = new RoomBranchGenerator(room, new Random(i + j));
                assertBranchCorrect(generator);
            }
        }
    }

    private void assertBranchCorrect(RoomBranchGenerator generator) {
        Room room = generator.room;
        int minX = Math.min(room.space.bottomLeftPosition().X, room.space.bottomRightPosition().X);
        int maxX = Math.max(room.space.bottomLeftPosition().X, room.space.bottomRightPosition().X);
        int minY = Math.min(room.space.bottomLeftPosition().Y, room.space.upperLeftPosition().Y);
        int maxY = Math.max(room.space.bottomLeftPosition().Y, room.space.upperLeftPosition().Y);

        // bottom
        RoomBranchGenerator.BranchCandidate c = generator.bottomCandidate();
        RoomBranch b = c.getBranch();
        assertEquals(c.position1.Y, c.position2.Y);
        assertTrue(b.position.X >= minX && b.position.X <= maxX);
        assertEquals(b.vector, Vector.downRight);

        // upper
        c = generator.upperCandidate();
        b = c.getBranch();
        assertEquals(c.position1.Y, c.position2.Y);
        assertTrue(b.position.X >= minX && b.position.X <= maxX);
        assertEquals(b.vector, Vector.upRight);

        // left
        c = generator.leftCandidate();
        b = c.getBranch();
        assertEquals(c.position1.X, c.position2.X);
        assertTrue(b.position.Y >= minY && b.position.Y <= maxY);
        assertEquals(b.vector, Vector.upLeft);

        // right
        c = generator.rightCandidate();
        b = c.getBranch();
        assertEquals(c.position1.X, c.position2.X);
        assertTrue(b.position.Y >= minY && b.position.Y <= maxY);
        assertEquals(b.vector, Vector.upRight);
    }
}
