package model.shape;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

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
                Arguments.of("(12)", "x, y 값을 구분하는 구분자 입력이 없습니다."),
                Arguments.of("(1,)", "x, y 값 중 하나의 정보만 입력했습니다."),
                Arguments.of("(,2)", "x, y 값 중 하나의 정보만 입력했습니다.")
        );
    }

    @Test
    @DisplayName("두 점의 x값 차, y값의 차를 반환한다.")
    void getDifference() {
        Point firstPoint = Point.create("(2,3)");
        Point secondPoint = Point.create("(1,1)");
        int expectXDifference = 1;
        int actualXDifference = firstPoint.getXDifference(secondPoint);
        int expectYDifference = 2;
        int actualYDifference = firstPoint.getYDifference(secondPoint);
        assertAll(
                () -> assertThat(actualXDifference).isEqualTo(expectXDifference),
                () -> assertThat(actualYDifference).isEqualTo(expectYDifference)
        );
    }
}