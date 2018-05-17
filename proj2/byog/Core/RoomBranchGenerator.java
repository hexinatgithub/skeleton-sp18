package byog.Core;

import byog.lab5.Position;

import java.util.Random;

public class RoomBranchGenerator {
    Room room;
    Random random;

    public RoomBranchGenerator(Room room, Random random) {
        this.room = room;
        this.random = random;
    }

    public RoomBranch[] randomBranch(int num) {
        num = Math.min(num, 4);
        BranchCandidate[] candidates = {
                bottomCandidate(), upperCandidate(), leftCandidate(), rightCandidate()
        };
        RoomBranch[] branches = new RoomBranch[num];

        RandomUtils.shuffle(random, candidates);
        // from each side generator a branch
        for (int i = 0; i < num; i++) {
            branches[i] = candidates[i].getBranch();
        }

        return branches;
    }

    protected BranchCandidate bottomCandidate() {
        Position p1 = room.space.bottomLeftPosition();
        Position p2 = room.space.bottomRightPosition();
        p1.Y -= 1;
        p2.Y -= 1;
        return new BranchCandidate(p1, p2, Vector.downRight);
    }

    protected BranchCandidate upperCandidate() {
        Position p1 = room.space.upperLeftPosition();
        Position p2 = room.space.upperRightPosition();
        p1.Y += 1;
        p2.Y += 1;
        return new BranchCandidate(p1, p2, Vector.upRight);
    }

    protected BranchCandidate leftCandidate() {
        Position p1 = room.space.bottomLeftPosition();
        Position p2 = room.space.upperLeftPosition();
        p1.X -= 1;
        p2.X -= 1;
        return new BranchCandidate(p1, p2, Vector.upLeft);
    }

    protected BranchCandidate rightCandidate() {
        Position p1 = room.space.bottomRightPosition();
        Position p2 = room.space.upperRightPosition();
        p1.X += 1;
        p2.X += 1;
        return new BranchCandidate(p1, p2, Vector.upRight);
    }

    protected class BranchCandidate {
        Position position1;
        Position position2;
        Vector vector;

        public BranchCandidate(Position position1, Position position2, Vector vector) {
            this.position1 = position1;
            this.position2 = position2;
            this.vector = vector;
        }

        private Position randomPoint() {
            if (position1.X != position2.X && position1.Y != position2.Y) {
                throw new RuntimeException("branch select should be horizontal or vertical.");
            }

            if (position1.X == position2.X) {
                return verticalRandomPoint();
            }

            return horizontalRandomPoint();
        }

        // Random select a horizontal position between start and end position, inclusion two position.
        private Position horizontalRandomPoint() {
            // x direct.
            int xDrt = position2.X - position1.X > 0 ? 1 : -1;
            // x size.
            int xWidth = Math.abs(position2.X - position1.X);
            // offset from start position.
            int offset = RandomUtils.uniform(random, Math.max(xWidth, 1)) * xDrt;
            return new Position(position1.X + offset, position1.Y);
        }

        private Position verticalRandomPoint() {
            // y direct.
            int yDrt = position2.Y - position1.Y > 0 ? 1 : -1;
            // x size.
            int yHeight = Math.abs(position2.Y - position1.Y);
            // offset from start position.
            int offset = RandomUtils.uniform(random, Math.max(yHeight, 1)) * yDrt;
            return new Position(position1.X, position1.Y + offset);
        }

        public RoomBranch getBranch() {
            Position p = randomPoint();
            return new RoomBranch(room, p, vector);
        }
    }

}
