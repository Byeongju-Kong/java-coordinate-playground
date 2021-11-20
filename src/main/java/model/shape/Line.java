package model.shape;

import model.shape.polygon.Shape;
import model.vo.Point;

import java.util.List;

public class Line implements Shape {
    private final Point firstPoint;
    private final Point secondPoint;

    public static Line generate(final List<Point> points) throws IllegalArgumentException {
        return new Line(points);
    }

    private Line(final List<Point> points) throws IllegalArgumentException {
        firstPoint = points.get(0);
        secondPoint = points.get(1);
    }

    @Override
    public double getAttribute() {
        return Math.sqrt(Math.pow(firstPoint.getXDifference(secondPoint), 2)
                + Math.pow(firstPoint.getYDifference(secondPoint), 2));
    }

    @Override
    public boolean hasPoint(final int x, final int y) {
        return firstPoint.isSamePoint(x, y) || secondPoint.isSamePoint(x, y);
    }
}
