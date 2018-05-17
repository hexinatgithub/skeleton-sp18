package byog.Core;

import byog.lab5.Position;

public class Rectangle implements Shape {
    public Position position;
    public Size size;

    public Size getSize() {
        return new Size(size.X, size.Y);
    }

    public Rectangle(Position position, int width, int height) {
        this.position = position;
        this.size = new Size(width, height);
    }

    @Override
    public boolean collision(Shape otherShape) {
        Rectangle other = (Rectangle) otherShape;
        boolean collisionX = bottomRightPosition().X >= other.bottomLeftPosition().X &&
                other.bottomRightPosition().X >= bottomLeftPosition().X;
        boolean collisionY = upperRightPosition().Y >= other.bottomRightPosition().Y &&
                other.upperRightPosition().Y >= bottomRightPosition().Y;
        return collisionX && collisionY;
    }

    public Position bottomLeftPosition() {
        return new Position(position.X, position.Y);
    }

    public Position upperRightPosition() {
        return new Position(position.X + size.X - 1, position.Y + size.Y - 1);
    }

    public Position upperLeftPosition() {
        return new Position(position.X, position.Y + size.Y - 1);
    }

    public Position bottomRightPosition() {
        return new Position(position.X + size.X - 1, position.Y);
    }

    @Override
    public boolean equals(Object obj) {
        Rectangle other = (Rectangle) obj;
        return other.position.equals(position) && other.size.equals(size);
    }

}
