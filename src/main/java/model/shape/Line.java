package model.shape;

public class Line {
    private final Point firstPoint;
    private final Point secondPoint;

    private Line(String[] points) {
        validateLine(points);
        firstPoint = Point.create(points[0]);
        secondPoint = Point.create(points[1]);
    }

    public static Line generate(String[] points) {
        return new Line(points);
    }

    public void validateLine(String[] points) {
        if (points[0].equals(points[1])) {
            throw new IllegalArgumentException("두 점이 같은 위치에 있으면 선을 생성할 수 없습니다.");
        }
    }

    public double getLength() {
        return firstPoint.getDistance(secondPoint);
    }
}
