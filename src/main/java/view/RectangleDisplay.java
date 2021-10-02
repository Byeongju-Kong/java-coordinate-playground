package view;

public class RectangleDisplay extends CoordinateDisplay {
    @Override
    public void showAttribute(double attribute) {
        System.out.println("사각형의 넓이는 " + attribute);
    }
}
