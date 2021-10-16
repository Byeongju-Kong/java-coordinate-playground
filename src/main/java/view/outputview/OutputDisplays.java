package view.outputview;

import java.util.Map;

public class OutputDisplays {
    private static final Map<Integer, OutputDisplay> OUTPUT_DISPLAYS
            = Map.of(2, new LineDisplay(),
            3, new TriangleDisplay(),
            4, new RectangleDisplay());

    private OutputDisplays() {
    }

    public static OutputDisplay findOutputView(int numberOfPoints) {
        if (!OUTPUT_DISPLAYS.containsKey(numberOfPoints)) {
            throw new IllegalArgumentException("입력한 점으로 출력할 수 없는 도형이 없습니다. (도형은 선, 삼각형, 사각형 중 하나)");
        }
        return OUTPUT_DISPLAYS.get(numberOfPoints);
    }
}
