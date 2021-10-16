package model.dto;

import model.vo.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class InputDTOTest {
    @Test
    @DisplayName("점에 대한 문자열을 구분자 단위로 나누어 문자열 배열로 반환한다.")
    void getInputPoints() {
        InputDTO inputDTO = InputDTO.create("(1,1)-(2,2)-(3,3)");
        List<Point> expect = Arrays.asList(Point.create("(1,1)"), Point.create("(2,2)"), Point.create("(3,3)"));
        List<Point> actual = inputDTO.getInputPoints();
        assertThat(actual).isEqualTo(expect);
    }

    @ParameterizedTest
    @DisplayName("점이 하나거나 점 들 사이에 중복이 있으면 예외를 발생시킨다.")
    @MethodSource("provideWrongInputAndExceptionMessage")
    void create_ExceptionByOnePointOrOverlap(String input, String message) {
        assertThatIllegalArgumentException().isThrownBy(() -> InputDTO.create(input))
                .withMessage(message);
    }

    private static Stream<Arguments> provideWrongInputAndExceptionMessage() {
        return Stream.of(
                Arguments.of("(1,1)", "점이 하나 밖에 존재하지 않습니다."),
                Arguments.of("1,1)-(2,2)","점을 담는 괄호가 잘못되었습니다."),
                Arguments.of("(1,1(-(2,2)","점을 담는 괄호가 잘못되었습니다."),
                Arguments.of(")1,1)-(2,2)","점을 담는 괄호가 잘못되었습니다."),
                Arguments.of("(1,1-(2,2)","점을 담는 괄호가 잘못되었습니다."),
                Arguments.of("(1,1)-)2,2)","점을 담는 괄호가 잘못되었습니다."),
                Arguments.of("(1,1)-(2,2","점을 담는 괄호가 잘못되었습니다.")
        );
    }
}