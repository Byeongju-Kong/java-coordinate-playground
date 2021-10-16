package view.outputview;

public class RectangleDisplay implements OutputDisplay {
    private static final String POINT_SYMBOL = " *";
    private static final String EMPTY = "  ";

    @Override
    public void showAttribute(double attribute) {
        System.out.println("사각형의 넓이는 " + attribute);
    }

    @Override
    public void printPoint(boolean correctPoint) {
        if (correctPoint) {
            System.out.print(POINT_SYMBOL);
        }
        if (!correctPoint) {
            System.out.print(EMPTY);
        }
    }

    @Override
    public void enterNewLine() {
        System.out.println();
    }
}
