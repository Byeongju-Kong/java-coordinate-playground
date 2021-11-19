package view.outputview;

public class LineDisplay implements OutputDisplay {
    @Override
    public void showAttribute(double attribute) {
        System.out.println("선의 길이는 " + attribute);
    }
}
