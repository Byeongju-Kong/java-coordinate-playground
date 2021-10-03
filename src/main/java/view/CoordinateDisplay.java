package view;

import java.util.Scanner;

public abstract class CoordinateDisplay implements Display {
    public static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public String inputPoints() {
        return SCANNER.nextLine();
    }

    @Override
    public void alertWrongInput(String exceptionMessage) {
        System.out.println(exceptionMessage + "다시 입력하세요.");
    }
}
