package model.shape;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class PointTest {
    @ParameterizedTest
    @DisplayName("길이가 1이거나 공백을 포함하는 문자열로 객체를 생성하면 예외를 발생시킨다.")
    @ValueSource(strings = {"", " ", "1 , 2"})
    void create_ExceptionByBlank(String wrongCoordinate) {
        assertThatIllegalArgumentException().isThrownBy(() -> Point.create(wrongCoordinate))
                .withMessage("좌표에 대한 입력에 공백이 있거나 좌표에 대한 정보가 없습니다.");
    }

    @Test
    @DisplayName("x와 y를 구분하는 구분자가 없는 문자열로 객체를 생성하면 예외를 발생시킨다.")
    void create_ExceptionByNonHavingDelimiter() {
        assertThatIllegalArgumentException().isThrownBy(() -> Point.create("12"))
                .withMessage("x, y 값을 구분하는 구분자 입력이 없습니다.");
    }

    @ParameterizedTest
    @DisplayName("x, y 중 하나의 값에 대한 정보로만 객체를 생성하면 예외를 발생시킨다.")
    @ValueSource(strings = {"1,", ",2"})
    void create_ExceptionByOnlyOneValue(String wrongCoordinate) {
        assertThatIllegalArgumentException().isThrownBy(() -> Point.create(wrongCoordinate))
                .withMessage("x, y 값 중 하나만 입력하셨습니다.");
    }

    @Test
    @DisplayName("두 점의 x값 차, y값의 차를 반환한다.")
    void getDifference() {
        Point firstPoint = Point.create("1,1");
        Point secondPoint = Point.create("2,2");
        double expect = 1.414;
        double actual = firstPoint.getDistance(secondPoint);
        assertThat(actual).isEqualTo(expect, offset(0.00099));
    }

    @ParameterizedTest
    @DisplayName("x, y 중 하나의 값만 같은 경우에만 true를 반환한다.")
    @MethodSource("providePointsAndNotSameOrDiagonalExpect")
    void isNotSameOrDiagonal(Point another, boolean expect) {
        Point standard = Point.create("1,1");
        boolean actual = standard.isNotSameOrDiagonal(another);
        assertThat(actual).isEqualTo(expect);
    }

    private static Stream<Arguments> providePointsAndNotSameOrDiagonalExpect() {
        return Stream.of(
                Arguments.of(Point.create("1,1"), false),
                Arguments.of(Point.create("1,2"), true),
                Arguments.of(Point.create("2,1"), true),
                Arguments.of(Point.create("2,2"), false)
        );
    }
}