package model.shape.polygon;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class RectangleTest {
    @Test
    @DisplayName("중복된 점이 있으면 예외를 발생시킨다.")
    void generate_ExceptionByOverlappedPoints() {
        assertThatIllegalArgumentException().isThrownBy(() -> Rectangle.generate(new String[]{"(1,1)", "(1,1)", "(2,1)", "(2,2)"}))
                .withMessage("네 점 중에 중복되는 점이 있습니다.");
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