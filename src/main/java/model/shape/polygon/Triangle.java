package model.shape.polygon;

import model.shape.Line;
import model.shape.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Triangle {
    private final List<Line> lines;

    private Triangle(final List<Point> points) {
        checkPointsGenerateLine(points);
        lines = new ArrayList<>();
        IntStream.range(0, 3)
                .forEach(index -> lines.add(Line.generate(Arrays.asList(points.get(index), points.get((index+1) % 3)))));
    }

    public static Triangle generate(final List<Point> points) {
        return new Triangle(points);
    }

    private void checkPointsGenerateLine(final List<Point> points) {
        if (points.stream().anyMatch(point -> doesGenerateLine(point, points))) {
            throw new IllegalArgumentException("세 점이 선을 이룹니다.");
        }
    }

    private boolean doesGenerateLine(final Point standardPoint, final List<Point> points) {
        return points.stream().allMatch(standardPoint::hasSameX)
                || points.stream().allMatch(standardPoint::hasSameY);
    }

    public double getAttribute() {
        double s = (lines.get(0).getAttribute() + lines.get(1).getAttribute() + lines.get(2).getAttribute()) / 2;
        return Math.sqrt(s * (s - lines.get(0).getAttribute()) * (s - lines.get(1).getAttribute())
                * (s - lines.get(2).getAttribute()));
    }
}
