package view.inputview;

import java.util.Scanner;

public class CoordinateInput implements InputDisplay {
    private final Scanner scanner = new Scanner(System.in);

    public String inputPoints() {
        System.out.println("점을 입력하세요.");
        return scanner.nextLine();
    }

    public void showExceptionMessage(String message) {
        System.out.println(message + "다시 입력하세요.");
    }
}
