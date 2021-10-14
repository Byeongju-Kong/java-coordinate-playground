package controller;

import model.shape.polygon.Shape;
import view.inputview.InputDisplay;
import view.outputview.OutputDisplay;
import view.outputview.OutputDisplays;

public class CoordinateController {
    private final ShapeGenerator shapeGenerator;

    public CoordinateController(InputDisplay inputDisplay) {
        shapeGenerator = new ShapeGenerator(inputDisplay);
    }

    public void run() {
        Shape shape = shapeGenerator.generateShape();
        OutputDisplay outputDisplay = OutputDisplays.findOutputView(shapeGenerator.getNumberOfPoints());
        outputDisplay.showAttribute(shape.getAttribute());
    }
}