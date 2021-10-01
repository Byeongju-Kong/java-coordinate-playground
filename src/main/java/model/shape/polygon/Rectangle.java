package model.shape.polygon;

import model.shape.Point;

import java.util.List;
import java.util.stream.Collectors;

public class Rectangle implements Polygon {
    private final List<Point> points;

    private Rectangle(final List<Point> points) {
        this.points = points;
        validate(points);
    }

    public static Rectangle generate(final List<Point> points) {
        return new Rectangle(points);
    }

    private void validate(final List<Point> points) {
        if (!points.stream().allMatch(this::hasSameXOrY)) {
            throw new IllegalArgumentException("네 각이 직각이 아닙니다.");
        }
    }

    private boolean hasSameXOrY(final Point standardPoint) {
        return points.stream()
                .filter(point -> point.hasSameX(standardPoint) || point.hasSameY(standardPoint))
                .filter(point -> !(point.hasSameX(standardPoint) && point.hasSameY(standardPoint)))
                .count() == 2;
    }

    public double getArea() {
        List<Double> lines = points.stream()
                .filter(point -> points.get(0).hasSameX(point) || points.get(0).hasSameY(point))
                .filter(point -> !(points.get(0).hasSameX(point) && points.get(0).hasSameY(point)))
                .map(point -> points.get(0).getDistance(point))
                .collect(Collectors.toUnmodifiableList());
        return lines.get(0) * lines.get(1);
    }
}
