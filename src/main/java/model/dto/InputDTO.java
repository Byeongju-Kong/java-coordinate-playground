package model.dto;

import model.shape.Point;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputDTO {
    private static final String DELIMITER = "-";
    private final String[] points;

    private InputDTO(final String input) throws IllegalArgumentException {
        points = parseInput(input);
    }

    public static InputDTO create(final String input) throws IllegalArgumentException {
        return new InputDTO(input);
    }

    private String[] parseInput(final String input) throws IllegalArgumentException {
        if (!input.contains(DELIMITER)) {
            throw new IllegalArgumentException("점이 하나 밖에 존재하지 않습니다.");
        }
        String[] points = input.split(DELIMITER);
        if (Arrays.stream(points).distinct().count() != points.length) {
            throw new IllegalArgumentException("중복된 점이 있습니다.");
        }
        return points;
    }

    public List<Point> getInputPoints() {
        return Arrays.stream(points)
                .map(Point::create)
                .collect(Collectors.toUnmodifiableList());
    }
}
