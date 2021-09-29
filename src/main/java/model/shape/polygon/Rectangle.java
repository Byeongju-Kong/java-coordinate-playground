package model.shape.polygon;

import model.shape.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Rectangle implements Polygon {
    private final List<Point> points;

    private Rectangle(final String[] points) {
        validateOverlap(points);
        this.points = new ArrayList<>();
        IntStream.range(0, 4)
                .forEach(index -> this.points.add(Point.create(points[index])));
    }

    public static Rectangle generate(final String[] points) {
        return new Rectangle(points);
    }

    private void validateOverlap(final String[] points) throws IllegalArgumentException {
        if (Arrays.stream(points).distinct().count() != 4) {
            throw new IllegalArgumentException("네 점 중에 중복되는 점이 있습니다.");
        }
    }

    public double getArea() {
        List<Double> lines = points.stream()
                .filter(point -> points.get(0).isNotSameOrDiagonal(point))
                .map(point -> points.get(0).getDistance(point))
                .collect(Collectors.toUnmodifiableList());
        return lines.get(0) * lines.get(1);
    }
}
