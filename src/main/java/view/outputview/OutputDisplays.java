package view.outputview;

import java.util.HashMap;
import java.util.Map;

public class OutputDisplays {
    private static final Map<Integer, OutputDisplay> OUTPUT_DISPLAYS = new HashMap<>();

    static {
        OUTPUT_DISPLAYS.put(2, new LineDisplay());
        OUTPUT_DISPLAYS.put(3, new TriangleDisplay());
        OUTPUT_DISPLAYS.put(4, new RectangleDisplay());
    }

    private OutputDisplays() {
    }

    public static OutputDisplay findOutputView(int numberOfPoints) {
        if (!OUTPUT_DISPLAYS.containsKey(numberOfPoints)) {
            throw new IllegalArgumentException("입력한 점으로 출력할 수 없는 도형이 없습니다. (도형은 선, 삼각형, 사각형 중 하나)");
        }
        return OUTPUT_DISPLAYS.get(numberOfPoints);
    }
}
