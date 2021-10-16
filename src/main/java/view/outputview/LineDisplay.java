package view.outputview;

public class LineDisplay implements OutputDisplay {
    private static final String POINT_SYMBOL = "*";
    private static final String EMPTY = "  ";

    @Override
    public void showAttribute(double attribute) {
        System.out.println("선의 길이는 " + attribute);
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
