package view.outputview;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class OutputDisplaysTest {
    @ParameterizedTest
    @DisplayName("점의 개수에 따라 출력을 위한 객체를 반환한다.")
    @CsvSource({"2, LineDisplay", "3, TriangleDisplay", "4, RectangleDisplay"})
    void findOutputView(int numberOfPoints, String expect) {
        String actual = OutputDisplays.findOutputView(numberOfPoints).getClass().getSimpleName();
        assertThat(actual).isEqualTo(expect);
    }

    @ParameterizedTest
    @DisplayName("점의 개수가 2, 3, 4가 아니라면 예외를 발생시킨다.")
    @ValueSource(ints = {1,5})
    void findOutputView_ExceptionByWrongNumberOfPoints(int wrongNumberOfPoints) {
        assertThatIllegalArgumentException().isThrownBy(() -> OutputDisplays.findOutputView(wrongNumberOfPoints))
                .withMessage("입력한 점으로 출력할 수 없는 도형이 없습니다. (도형은 선, 삼각형, 사각형 중 하나)");
    }
}