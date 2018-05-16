package byog.Core;

import byog.lab5.Position;

public class Wall {
    public Rectangle wallRect;

    public Wall(Position position, int width, int height) {
        wallRect = new Rectangle(position, width, height);
    }

    public Position bottomLeftPosition() {
        return wallRect.bottomLeftPosition();
    }

    public Position upperRightPosition() {
        return wallRect.upperRightPosition();
    }

    public Position upperLeftPosition() {
        return wallRect.upperLeftPosition();
    }

    public Position bottomRightPosition() {
        return wallRect.bottomRightPosition();
    }
}
