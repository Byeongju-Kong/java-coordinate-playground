package model.shape;

import model.vo.Number;

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
        point = point.replace(LEFT_BRACKET, "").replace(RIGHT_BRACKET, "");
        validateCoordinate(point);
        return point.split(DELIMITER);
    }

    private void validateCoordinate(final String point) throws IllegalArgumentException {
        if (point.isBlank() || point.contains(" ")) {
            throw new IllegalArgumentException("좌표에 대한 입력에 공백이 있거나 좌표에 대한 정보가 없습니다.");
        }
        if (!point.contains(DELIMITER)) {
            throw new IllegalArgumentException("x, y 값을 구분하는 구분자 입력이 없습니다.");
        }
        if (String.valueOf(point.charAt(0)).equals(DELIMITER) ||
                String.valueOf(point.charAt(point.length() - 1)).equals(DELIMITER)) {
            throw new IllegalArgumentException("x, y 값 중 하나만 입력하셨습니다.");
        }
    }

    public Integer getXDifference(final Point another) {
        return x.getDifference(another.x);
    }

    public Integer getYDifference(final Point another) {
        return y.getDifference(another.y);
    }
}