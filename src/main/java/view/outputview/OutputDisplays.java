package view.outputview;

import java.util.Arrays;
import java.util.List;

public class OutputDisplays {
    private OutputDisplays() {
    }

    private static final List<OutputDisplay> displays =
            Arrays.asList(new LineDisplay(), new RectangleDisplay(), new TriangleDisplay());

    public static OutputDisplay findOutputView(int numberOfPoints) {
        return displays.stream()
                .filter(outputDisplay -> outputDisplay.hasAppropriateNumber(numberOfPoints))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("입력한 점으로 출력할 수 없는 도형이 없습니다. (도형은 선, 삼각형, 사각형 중 하나)"));
    }
}
