package model.shape;

import model.vo.Number;

import java.util.Objects;

public class Point {
    private static final String DELIMITER = ",";
    private static final String LEFT_BRACKET = "(";
    private static final String RIGHT_BRACKET = ")";
    private final Number x;
    private final Number y;

    private Point(final String point) throws IllegalArgumentException {
        String[] numbers = splitXAndY(point);
        this.x = Number.generate(numbers[0]);
        this.y = Number.generate(numbers[1]);
    }

    public static Point create(final String point) throws IllegalArgumentException {
        return new Point(point);
    }

    private String[] splitXAndY(String point) throws IllegalArgumentException {
        validateCoordinate(point);
        point = point.replace(LEFT_BRACKET, "").replace(RIGHT_BRACKET, "");
        return point.split(DELIMITER);
    }

    private void validateCoordinate(final String point) throws IllegalArgumentException {
        if (point.contains(" ")) {
            throw new IllegalArgumentException("좌표에 대한 입력에 공백이 있거나 좌표에 대한 정보가 없습니다.");
        }
        if (!point.contains(DELIMITER)) {
            throw new IllegalArgumentException("x, y 값을 구분하는 구분자 입력이 없습니다.");
        }
        if (!(String.valueOf(point.charAt(0)).equals(LEFT_BRACKET)
                && String.valueOf(point.charAt(point.length() - 1)).equals(RIGHT_BRACKET))) {
            throw new IllegalArgumentException("점을 담는 괄호가 잘못되었습니다.");
        }
        if (String.valueOf(point.charAt(point.indexOf(DELIMITER) - 1)).equals(LEFT_BRACKET) ||
                String.valueOf(point.charAt(point.indexOf(DELIMITER) + 1)).equals(RIGHT_BRACKET)) {
            throw new IllegalArgumentException("x, y 값 중 하나만 입력했습니다.");
        }
    }

    public double getDistance(final Point another) {
        return Math.sqrt(Math.pow(x.getDifference(another.x), 2)
                + Math.pow(y.getDifference(another.y), 2));
    }

    public boolean hasSameX(final Point another) {
        return x.equals(another.x);
    }

    public boolean hasSameY(final Point another) {
        return y.equals(another.y);
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