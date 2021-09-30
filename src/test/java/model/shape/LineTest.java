package model.shape;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class LineTest {
    private final Line line = Line.generate(new String[]{"1,1", "2,2"});

    @Test
    @DisplayName("Line을 생성하고 길이를 반환한다.")
    void getLength() {
        double actual = line.getLength();
        double expect = 1.414;
        assertThat(actual).isEqualTo(expect, offset(0.00099));
    }

    @ParameterizedTest
    @DisplayName("동일한 두 점 혹은 하나의 점으로 Line을 생성하려고 하면 예외를 발생시킨다.")
    @MethodSource("provideWrongPointsAndMessage")
    void create_ExceptionBySamePoints(String[] points, String message) {
        assertThatIllegalArgumentException().isThrownBy(() -> Line.generate(points))
                .withMessage(message);
    }

    private static Stream<Arguments> provideWrongPointsAndMessage() {
        return Stream.of(
          Arguments.of(new String[]{"(1,1)", "(1,1)"}, "두 점이 같은 위치에 있으면 선을 생성할 수 없습니다."),
          Arguments.of(new String[]{"(1,1)"}, "점이 두 개 있어야 선을 생성 할 수 있습니다.")
        );
    }
}

