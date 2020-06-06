import java.awt.Point;

public class Directions {
    public static final Point NW = new Point(-1, -1);
    public static final Point N = new Point(0, -1);
    public static final Point NE = new Point(1, -1);
    public static final Point W = new Point(-1, 0);
    public static final Point O = new Point(0, 0);
    public static final Point E = new Point(1, 0);
    public static final Point SW = new Point(-1, 1);
    public static final Point S = new Point(0, 1);
    public static final Point SE = new Point(1, 1);

    public static final Point[] DIRECTIONS_4 = new Point[] { N, W, E, S };
    public static final Point[] DIRECTIONS_5 = new Point[] { O, N, W, E, S };
    public static final Point[] DIRECTIONS_8 = new Point[] { N, W, E, S, NW, NE, SW, SE };
    public static final Point[] DIRECTIONS_9 = new Point[] { O, NW, N, NE, W, E, SW, S, SE };
}
