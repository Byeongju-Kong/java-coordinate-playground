package view.outputview;

public class TriangleDisplay implements OutputDisplay  {
    @Override
    public boolean hasAppropriateNumber(int numberOfPoints) {
        return numberOfPoints == 3;
    }

    @Override
    public void showAttribute(double attribute) {
        System.out.println("삼각형의 넓이는 " + attribute);
    }
}
