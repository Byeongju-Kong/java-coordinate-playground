package model.vo;

import java.util.Objects;

public class Point {
    private static final String DELIMITER = ",";
    private static final String LEFT_BRACKET = "(";
    private static final String RIGHT_BRACKET = ")";
    private final Number x;
    private final Number y;

    public static Point create(final String point) throws IllegalArgumentException {
        return new Point(point);
    }

    private Point(String point) throws IllegalArgumentException {
        point = point.replace(LEFT_BRACKET, "").replace(RIGHT_BRACKET, "");
        validateCoordinate(point);
        String[] numbers = point.split(DELIMITER);
        this.x = Number.generate(numbers[0]);
        this.y = Number.generate(numbers[1]);
    }

    private void validateCoordinate(final String point) throws IllegalArgumentException {
        if(point.isBlank()) {
            throw new IllegalArgumentException("점에 대한 빈 입력이 있습니다.");
        }
        if (!point.contains(DELIMITER)) {
            throw new IllegalArgumentException("x, y 값을 구분하는 구분자 입력이 없습니다.");
        }
        if (String.valueOf(point.charAt(0)).equals(DELIMITER) ||
                String.valueOf(point.charAt(point.length() - 1)).equals(DELIMITER)) {
            throw new IllegalArgumentException("x, y 값 중 하나의 정보만 입력했습니다.");
        }
    }

    public int getXDifference(final Point another) {
        return x.getDifference(another.x);
    }

    public int getYDifference(final Point another) {
        return y.getDifference(another.y);
    }

    public boolean isSamePoint(final int x, final int y) {
        return this.x.hasSameValue(x) && this.y.hasSameValue(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(x, point.x) && Objects.equals(y, point.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}