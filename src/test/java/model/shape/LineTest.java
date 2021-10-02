package model.shape;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class LineTest {
    @Test
    @DisplayName("Line을 생성하고 길이를 반환한다.")
    void getLength() {
        Line line = Line.generate(Arrays.asList(Point.create("(1,1)"), Point.create("(2,2)")));
        double actual = line.getAttribute();
        double expect = 1.414;
        assertThat(actual).isEqualTo(expect, offset(0.00099));
    }

    @Test
    @DisplayName("동일한 두 점 혹은 하나의 점으로 Line을 생성하려고 하면 예외를 발생시킨다.")
    void create_ExceptionBySamePoints() {
        assertThatIllegalArgumentException().isThrownBy(() -> Line.generate(Arrays.asList(Point.create("(1,1)"), Point.create("(1,1)"))))
                .withMessage("두 점이 같은 위치에 있으면 선을 생성할 수 없습니다.");
    }
}

