package model.shape;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class PointTest {
    @ParameterizedTest
    @DisplayName("길이가 1이거나 공백을 포함하는 문자열로 객체를 생성하면 예외를 발생시킨다.")
    @MethodSource("provideWrongPointAndMessage")
    void create_ExceptionByBlank(String wrongPoint, String message) {
        assertThatIllegalArgumentException().isThrownBy(() -> Point.create(wrongPoint))
                .withMessage(message);
    }

    private static Stream<Arguments> provideWrongPointAndMessage() {
        return Stream.of(
                Arguments.of("()", "좌표에 대한 입력에 공백이 있거나 좌표에 대한 정보가 없습니다."),
                Arguments.of("( )", "좌표에 대한 입력에 공백이 있거나 좌표에 대한 정보가 없습니다."),
                Arguments.of("(1 , 2)", "좌표에 대한 입력에 공백이 있거나 좌표에 대한 정보가 없습니다."),
                Arguments.of("(12)", "x, y 값을 구분하는 구분자 입력이 없습니다."),
                Arguments.of("(1,)", "x, y 값 중 하나만 입력하셨습니다."),
                Arguments.of("(,2)", "x, y 값 중 하나만 입력하셨습니다.")
        );
    }

    @Test
    @DisplayName("두 점의 x값 차, y값의 차를 반환한다.")
    void getDifference() {
        Point firstPoint = Point.create("(1,1)");
        Point secondPoint = Point.create("(2,2)");
        double expect = 1.414;
        double actual = firstPoint.getDistance(secondPoint);
        assertThat(actual).isEqualTo(expect, offset(0.00099));
    }

    @ParameterizedTest
    @DisplayName("Point 객체를 받아 x 값이 같으면 true를 반환한다.")
    @MethodSource("providePointsAndSameXExpect")
    void hasSameX(Point another, boolean expect) {
        Point standard = Point.create("(1,1)");
        boolean actual = standard.hasSameX(another);
        assertThat(actual).isEqualTo(expect);
    }

    private static Stream<Arguments> providePointsAndSameXExpect() {
        return Stream.of(
                Arguments.of(Point.create("1,2"), true),
                Arguments.of(Point.create("2,1"), false)
        );
    }

    @ParameterizedTest
    @DisplayName("Point 객체를 받아 x 값이 같으면 true를 반환한다.")
    @MethodSource("providePointsAndSameYExpect")
    void hasSameY(Point another, boolean expect) {
        Point standard = Point.create("(1,1)");
        boolean actual = standard.hasSameY(another);
        assertThat(actual).isEqualTo(expect);
    }

    private static Stream<Arguments> providePointsAndSameYExpect() {
        return Stream.of(
                Arguments.of(Point.create("1,2"), false),
                Arguments.of(Point.create("2,1"), true)
        );
    }
}