package view;

public interface Display {
    String inputPoints();

    void showAttribute(double attribute);

    void alertWrongInput(String exceptionMessage);
}
