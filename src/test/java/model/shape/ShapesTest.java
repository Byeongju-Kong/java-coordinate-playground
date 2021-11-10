package model.shape;

import model.shape.polygon.Rectangle;
import model.shape.polygon.Triangle;
import model.vo.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ShapesTest {
    @ParameterizedTest
    @DisplayName("점의 개수에 따라 적절한 도형을 반환한다.")
    @MethodSource("providePointsAndShapeClass")
    void findShape(List<Point> points, Object expect) {
        Object actual = Shapes.findShape(points).getClass();
        assertThat(actual).isEqualTo(expect);
    }

    private static Stream<Arguments> providePointsAndShapeClass() {
        return Stream.of(
                Arguments.of(Arrays.asList(Point.create("(1,1)"), Point.create("(1,2)")),
                        Line.class),
                Arguments.of(Arrays.asList(Point.create("(5,1)"), Point.create("(1,2)"), Point.create("(2,2)")),
                        Triangle.class),
                Arguments.of(Arrays.asList(Point.create("(1,1)"), Point.create("(1,2)"), Point.create("(2,2)"), Point.create("(2,1)")),
                        Rectangle.class)
        );
    }
}