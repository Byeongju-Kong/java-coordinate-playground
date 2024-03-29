package model.shape.polygon;

import model.vo.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class RectangleTest {
    @ParameterizedTest
    @DisplayName("직사각형을 만들 수 없으면 예외를 발생시킨다.")
    @MethodSource("provideWrongPointsAndMessage")
    void generate_ExceptionByOverlappedPoints(List<Point> points, String message) {
        assertThatIllegalArgumentException().isThrownBy(() -> Rectangle.generate(points))
                .withMessage(message);
    }

    private static Stream<Arguments> provideWrongPointsAndMessage() {
        return Stream.of(
                Arguments.of(Arrays.asList(Point.create("(1,2)"), Point.create("(1,3)"), Point.create("(3,1)"), Point.create("(3,3)")),
                        "네 각이 직각이 아닙니다."),
                Arguments.of(Arrays.asList(Point.create("(1,1)"), Point.create("(2,1)"), Point.create("(2,2)"), Point.create("(3,2)")),
                        "네 각이 직각이 아닙니다.")
        );
    }

    @Test
    @DisplayName("사각형의 면적을 반환한다.")
    void getArea() {
        Rectangle rectangle = Rectangle.generate(
                Arrays.asList(Point.create("(1,1)"), Point.create("(1,3)"), Point.create("(3,1)"), Point.create("(3,3)")));
        double expect = 4.0;
        double actual = rectangle.getAttribute();
        assertThat(actual).isEqualTo(expect);
    }
    
    @ParameterizedTest
    @DisplayName("x, y 값을 받아 도형이 해당 점과 같은 점을 갖고 있는지 반환한다.")
    @CsvSource({"1, 1, true", "1, 2, false"})
    void hasPoint(int x, int y, boolean expect) {
        Rectangle rectangle = Rectangle.generate(
                Arrays.asList(Point.create("(1,1)"), Point.create("(1,3)"), Point.create("(3,1)"), Point.create("(3,3)")));
        boolean actual = rectangle.hasPoint(x, y);
        assertThat(actual).isEqualTo(expect);
    }
}