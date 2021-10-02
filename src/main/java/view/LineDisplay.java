package view;

public class LineDisplay extends CoordinateDisplay {
    @Override
    public void showAttribute(double attribute) {
        System.out.println("선의 길이는 " + attribute);
    }
}
