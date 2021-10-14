package controller;

import model.dto.InputDTO;
import model.shape.Shapes;
import model.shape.polygon.Shape;
import view.inputview.InputDisplay;
import view.outputview.OutputDisplay;
import view.outputview.OutputDisplays;

public class CoordinateController {
    private Shape shape;
    private final InputDisplay inputDisplay;
    private OutputDisplay outputDisplay;
    private InputDTO inputDTO;
    private boolean inputPointsGenerateWrongShape;

    public CoordinateController(InputDisplay inputDisplay) {
        this.inputDisplay = inputDisplay;
        inputPointsGenerateWrongShape = true;
    }

    public void run() {
        while (inputPointsGenerateWrongShape) {
            generateShape();
        }
        findOutputDisplay();
        outputDisplay.showAttribute(shape.getAttribute());
    }

    private void generateShape() {
        try {
            inputPoints();
        } catch (IllegalArgumentException exception) {
            inputDisplay.showExceptionMessage(exception.getMessage());
        }
    }

    private void inputPoints() throws IllegalArgumentException {
        inputDTO = InputDTO.create(inputDisplay.inputPoints());
        shape = Shapes.findShape(inputDTO.getInputPoints());
        inputPointsGenerateWrongShape = false;
    }

    private void findOutputDisplay() {
        outputDisplay = OutputDisplays.findOutputView(inputDTO.getNumberOfPoints());
    }
}
