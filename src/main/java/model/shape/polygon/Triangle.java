package model.shape.polygon;

import model.shape.Line;
import model.shape.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Triangle implements Shape {
    private final List<Line> lines;

    private Triangle(final List<Point> points) throws IllegalArgumentException {
        validate(points);
        lines = new ArrayList<>();
        IntStream.range(0, 3)
                .forEach(index -> lines.add(Line.generate(Arrays.asList(points.get(index), points.get((index + 1) % 3)))));
    }

    public static Triangle generate(final List<Point> points) throws IllegalArgumentException {
        return new Triangle(points);
    }

    private void validate(final List<Point> points) throws IllegalArgumentException {
        if (points.stream().distinct().count() != 3) {
            throw new IllegalArgumentException("점에 중복이 있습니다");
        }
        if (hasSameInclinations(points)) {
            throw new IllegalArgumentException("세 점이 선을 이룹니다.");
        }
    }

    private boolean hasSameInclinations(final List<Point> points) {
        return points.get(0).getYDifference(points.get(1)) / points.get(0).getXDifference(points.get(1)) ==
                points.get(1).getYDifference(points.get(2)) / points.get(1).getXDifference(points.get(2));
    }

    @Override
    public double getAttribute() {
        double s = (lines.get(0).getAttribute() + lines.get(1).getAttribute() + lines.get(2).getAttribute()) / 2;
        return Math.sqrt(s * (s - lines.get(0).getAttribute()) * (s - lines.get(1).getAttribute())
                * (s - lines.get(2).getAttribute()));
    }

    @Override
    public boolean hasPoint(final int x, final int y) {
        return lines.stream()
                .anyMatch(line -> line.hasPoint(x, y));
    }
}
