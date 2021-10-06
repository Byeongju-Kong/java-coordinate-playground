package model.shape.polygon;

import model.shape.Line;
import model.shape.Point;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.pow;

public class Rectangle implements Shape {
    private final List<Point> points;

    private Rectangle(final List<Point> points) throws IllegalArgumentException {
        this.points = points;
        validate();
    }

    public static Rectangle generate(final List<Point> points) throws IllegalArgumentException {
        return new Rectangle(points);
    }

    private void validate() throws IllegalArgumentException {
        double lengthOfDiagonal = findDiagonal();
        if (!points.stream().allMatch(point -> isRightAngle(point, lengthOfDiagonal))) {
            throw new IllegalArgumentException("네 각이 직각이 아닙니다.");
        }
    }

    private double findDiagonal() {
        List<Double> lengths = points.stream()
                .filter(point -> !point.equals(points.get(0)))
                .map(point -> Line.generate(Arrays.asList(points.get(0), point)).getAttribute())
                .collect(Collectors.toUnmodifiableList());
        return Collections.max(lengths);
    }

    private boolean isRightAngle(final Point standardPoint, final double lengthOfDialog) {
        double[] lengths = points.stream()
                .filter(point -> !standardPoint.equals(point))
                .mapToDouble(point -> Line.generate(Arrays.asList(point, standardPoint)).getAttribute())
                .filter(length -> length != lengthOfDialog)
                .toArray();
        if (isSameBaseLineWithDiagonal(lengths)) {
            return Math.round((pow(lengths[0], 2) + pow(lengths[0], 2)) * 100) / 100.0
                    == Math.round(pow(lengthOfDialog, 2) * 100) / 100.0;
        }
        return Math.round((pow(lengths[0], 2) + pow(lengths[1], 2)) * 100) / 100.0
                == Math.round(pow(lengthOfDialog, 2) * 100) / 100.0;
    }

    private boolean isSameBaseLineWithDiagonal(double[] lengths) {
        return lengths.length == 1;
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