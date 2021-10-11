package view.outputview;

public interface OutputDisplay {
    void showAttribute(double attribute);

    boolean hasAppropriateNumber(int numberOfPoints);

    void printPoint(boolean correctPoint);

    void enterNewLine();
}
