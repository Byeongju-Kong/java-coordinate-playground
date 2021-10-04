package view.outputview;

public class LineDisplay implements OutputDisplay {
    @Override
    public boolean hasAppropriateNumber(int numberOfPoints) {
        return numberOfPoints == 2;
    }

    @Override
    public void showAttribute(double attribute) {
        System.out.println("선의 길이는 " + attribute);
    }
}
