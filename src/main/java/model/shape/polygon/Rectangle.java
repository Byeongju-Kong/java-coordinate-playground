package model.shape.polygon;

import model.shape.Line;
import model.shape.Point;

import java.util.Arrays;
import java.util.List;

public class Rectangle implements Shape {
    private final List<Point> points;

    private Rectangle(final List<Point> points) throws IllegalArgumentException {
        this.points = points;
        validate(points);
    }

    public static Rectangle generate(final List<Point> points) throws IllegalArgumentException {
        return new Rectangle(points);
    }

    private void validate(final List<Point> points) throws IllegalArgumentException {
        if (!points.stream().allMatch(this::hasSameXOrY)) {
            throw new IllegalArgumentException("네 각이 직각이 아닙니다.");
        }
    }

    private boolean hasSameXOrY(final Point standardPoint) {
        return points.stream()
                .filter(point -> point.getXDifference(standardPoint) == 0 || point.getYDifference(standardPoint) == 0)
                .filter(point -> !(point.getXDifference(standardPoint) == 0 && point.getYDifference(standardPoint) == 0))
                .count() == 2;
    }

    @Override
    public double getAttribute() {
        double[] lengthsForArea = points.stream()
                .filter(point -> points.get(0).getXDifference(point) == 0 || points.get(0).getYDifference(point) == 0)
                .filter(point -> !(points.get(0).getXDifference(point) == 0 && points.get(0).getYDifference(point) == 0))
                .mapToDouble(point -> Line.generate(Arrays.asList(points.get(0), point)).getAttribute())
                .toArray();
        return lengthsForArea[0] * lengthsForArea[1];
    }
}
