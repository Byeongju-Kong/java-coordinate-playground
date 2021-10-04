package model.shape.polygon;

import model.shape.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class TriangleTest {
    @ParameterizedTest
    @DisplayName("삼각형을 만들 수 없으면 예외를 발생시킨다.")
    @MethodSource("provideWrongPointsAndMessage")
    void generate_ExceptionByWrongPoints(List<Point> points) {
        assertThatIllegalArgumentException().isThrownBy(() -> Triangle.generate(points))
                .withMessage("세 점이 선을 이룹니다.");

    }

    private static Stream<Arguments> provideWrongPointsAndMessage() {
        return Stream.of(
                Arguments.of(Arrays.asList(Point.create("(1,1)"), Point.create("(2,1)"), Point.create("(3,1)"))),
                Arguments.of(Arrays.asList(Point.create("(1,1)"), Point.create("(1,2)"), Point.create("(1,3)"))),
                Arguments.of(Arrays.asList(Point.create("(1,1)"), Point.create("(2,2)"), Point.create("(3,3)")))
        );
    }

    @Test
    @DisplayName("삼각형의 면적을 반환한다.")
    void getArea() {
        Triangle triangle = Triangle.generate(Arrays.asList(Point.create("(0,0)"), Point.create("(2,0)"), Point.create("(0,2)")));
        double expect = 2.0;
        double actual = triangle.getAttribute();
        assertThat(actual).isEqualTo(expect, offset(0.00099));
    }
}