package model.shape;

import model.shape.polygon.Shape;

import java.util.List;

public class Line implements Shape {
    private final Point firstPoint;
    private final Point secondPoint;

    private Line(final List<Point> points) {
        validateLine(points);
        firstPoint = points.get(0);
        secondPoint = points.get(1);
    }

    public static Line generate(final List<Point> points) {
        return new Line(points);
    }

    public void validateLine(final List<Point> points) {
        if (isSamePoints(points)) {
            throw new IllegalArgumentException("두 점이 같은 위치에 있으면 선을 생성할 수 없습니다.");
        }
    }

    private boolean isSamePoints(final List<Point> points) {
        return points.get(0).hasSameX(points.get(1)) && points.get(0).hasSameY(points.get(1));
    }

    @Override
    public double getAttribute() {
        return firstPoint.getDistance(secondPoint);
    }
}
