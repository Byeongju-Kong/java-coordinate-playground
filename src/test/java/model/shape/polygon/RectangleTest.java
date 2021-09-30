package model.shape.polygon;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class RectangleTest {
    @ParameterizedTest
    @DisplayName("직사각형을 만들 수 없으면 예외를 발생시킨다.")
    @MethodSource("provideWrongPointsAndMessage")
    void generate_ExceptionByOverlappedPoints(String[] points, String message) {
        assertThatIllegalArgumentException().isThrownBy(() -> Rectangle.generate(points))
                .withMessage(message);
    }

    private static Stream<Arguments> provideWrongPointsAndMessage() {
        return Stream.of(
                Arguments.of(new String[]{"(1,1)", "(1,1)", "(2,1)", "(2,2)"}, "점에 중복이 있습니다."),
                Arguments.of(new String[]{"(1,1)", "(2,1)", "(2,2)"}, "점이 4개가 아닙니다."),
                Arguments.of(new String[]{"(1,2)", "(1,3)", "(3,1)", "(3,3)"}, "네 각이 직각이 아닙니다."),
                Arguments.of(new String[]{"(1,1)", "(2,1)", "(2,2)", "(3,2)"}, "네 각이 직각이 아닙니다.")
        );
    }

    @Test
    @DisplayName("사각형의 면적을 반환한다.")
    void getArea() {
        Rectangle rectangle = Rectangle.generate(new String[]{"(1,1)", "(1,3)", "(3,1)", "(3,3)"});
        double expect = 4.0;
        double actual = rectangle.getArea();
        assertThat(actual).isEqualTo(expect);
    }
}