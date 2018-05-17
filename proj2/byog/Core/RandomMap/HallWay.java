package byog.Core.RandomMap;

import byog.Core.Room;
import byog.lab5.Position;

public class HallWay extends Room {
    HallWayVector vector;

    public HallWay(Position p, int width, int height, HallWayVector vector) {
        super(p, width, height);
        this.vector = vector;
    }
}
