package controller;

import view.inputview.CoordinateInput;
import view.inputview.InputDisplay;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputDisplay inputDisplay = new CoordinateInput(scanner);
        CoordinateController coordinateController = new CoordinateController(inputDisplay);
        coordinateController.run();
    }
}
