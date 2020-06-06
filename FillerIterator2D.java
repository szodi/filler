import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.function.BiPredicate;

public class FillerIterator2D<E> implements Iterator<Point> {

    private final E[][] values;
    private final Point[] directions;

    private final Deque<Point> stack = new ArrayDeque<>();
    private final BiPredicate<Integer, Integer> isAffected;
    private boolean[][] marked;

    public FillerIterator2D(E[][] values, Point[] directions, Point reference, BiPredicate<Integer, Integer> isAffected) {
	this.values = values;
	this.directions = directions;
	this.isAffected = isAffected;

	stack.add(reference);

	marked = new boolean[values.length][];
	for (int k = 0; k < values.length; k++) {
	    marked[k] = new boolean[values[k].length];
	}
	marked[reference.y][reference.x] = true;
    }

    @Override
    public boolean hasNext() {
	return stack.size() > 0;
    }

    @Override
    public Point next() {
	Point point = stack.pop();
	for (Point direction : directions) {
	    int di = point.x + direction.x;
	    int dj = point.y + direction.y;
	    if (isInRange(di, dj) && !marked[dj][di] && isAffected.test(di, dj)) {
		marked[dj][di] = true;
		stack.add(new Point(di, dj));
	    }
	}
	return point;
    }

    private boolean isInRange(int di, int dj) {
	return dj >= 0 && dj < values.length && di >= 0 && di < values[dj].length;
    }
}
