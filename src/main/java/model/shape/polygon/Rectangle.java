package model.shape.polygon;

import model.shape.Point;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Rectangle implements Polygon {
    private final List<Point> points;

    private Rectangle(final String[] points) {
        validateQuadrilateral(points);
        this.points = Arrays.stream(points)
                .map(Point::create)
                .collect(Collectors.toUnmodifiableList());
        validateRightAngle();
    }

    public static Rectangle generate(final String[] points) {
        return new Rectangle(points);
    }

    private void validateQuadrilateral(final String[] points) {
        if (points.length != 4) {
            throw new IllegalArgumentException("점이 4개가 아닙니다.");
        }
        if (Arrays.stream(points).distinct().count() != 4) {
            throw new IllegalArgumentException("점에 중복이 있습니다.");
        }
    }

    private void validateRightAngle() {
        if (!this.points.stream().allMatch(this::hasSameXOrY)) {
            throw new IllegalArgumentException("네 각이 직각이 아닙니다.");
        }
    }

    private boolean hasSameXOrY(final Point standardPoint) {
        return points.stream()
                .filter(standardPoint::hasSameXOrSameY)
                .count() == 2;
    }

    public double getArea() {
        List<Double> lines = points.stream()
                .filter(point -> points.get(0).hasSameXOrSameY(point))
                .map(point -> points.get(0).getDistance(point))
                .collect(Collectors.toUnmodifiableList());
        return lines.get(0) * lines.get(1);
    }
}
