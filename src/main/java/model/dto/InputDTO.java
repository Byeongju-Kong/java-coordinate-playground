package model.dto;

import model.vo.Point;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputDTO {
    private static final String DELIMITER = "-";
    private static final char OPEN_BRACKET = '(';
    private static final char CLOSE_BRACKET = ')';
    private final String[] points;
    private boolean isBracketOpen = false;

    public static InputDTO create(final String input) throws IllegalArgumentException {
        return new InputDTO(input);
    }

    private InputDTO(final String input) throws IllegalArgumentException {
        validateInput(input);
        points = input.split(DELIMITER);
    }

    private void validateInput(final String input) throws IllegalArgumentException {
        if (!input.contains(DELIMITER)) {
            throw new IllegalArgumentException("점이 하나 밖에 존재하지 않습니다.");
        }
        IntStream.range(0, input.length())
                .filter(index -> input.charAt(index) == '(' || input.charAt(index) == ')')
                .forEach(index -> checkWrongBracket(input.charAt(index)));
        if (isBracketOpen) {
            throw new IllegalArgumentException("점을 담는 괄호가 잘못되었습니다.");
        }
    }

    private void checkWrongBracket(char bracket) {
        if ((bracket == OPEN_BRACKET && isBracketOpen) || (bracket == CLOSE_BRACKET && !isBracketOpen)) {
            throw new IllegalArgumentException("점을 담는 괄호가 잘못되었습니다.");
        }
        isBracketOpen = !isBracketOpen;
    }

    public List<Point> getInputPoints() {
        return Arrays.stream(points)
                .map(Point::create)
                .collect(Collectors.toUnmodifiableList());
    }

    public int getNumberOfPoints() {
        return points.length;
    }
}
