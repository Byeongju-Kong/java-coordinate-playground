package model.shape;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("같은 위치의 두 점으로 객체를 생성하면 예외를 발생시킨다.")
    void create_ExceptionBySamePoints() {
        assertThatIllegalArgumentException().isThrownBy(() -> Line.generate(new String[]{"1,1", "1,1"}))
                .withMessage("두 점이 같은 위치에 있으면 선을 생성할 수 없습니다.");
    }
}