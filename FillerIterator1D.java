import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.function.Predicate;

public class FillerIterator1D implements Iterator<Integer> {

	private final int[] directionIndices;
	private final int perPixel;

	private final Deque<Integer> stack = new ArrayDeque<>();
	private final Predicate<Integer> isAffected;
	private boolean[] marked;

    public FillerIterator1D(int width, int height, int bufferSize, Point[] directions, int referenceIndex, Predicate<Integer> isAffected) {
		this.isAffected = isAffected;
		this.directionIndices = new int[directions.length];

		stack.add(referenceIndex);
		marked = new boolean[bufferSize];
		marked[referenceIndex] = true;
		perPixel = bufferSize / (width * height);
		for (int i = 0; i < directions.length; i++) {
			directionIndices[i] = perPixel * (directions[i].x + directions[i].y * width);
		}
    }

    @Override
    public boolean hasNext() {
		return stack.size() > 0;
    }

    @Override
    public Integer next() {
		int pointIndex = stack.pop();
		for (int directionIndex : directionIndices) {
			int dIndex = pointIndex + directionIndex;
			if (isInRange(dIndex) && !marked[dIndex] && isAffected.test(dIndex)) {
				marked[dIndex] = true;
				stack.add(dIndex);
			}
		}
		return pointIndex;
    }

    private boolean isInRange(int dIndex) {
		return dIndex >= 0 && dIndex < marked.length;
    }
}
