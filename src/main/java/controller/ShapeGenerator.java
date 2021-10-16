package controller;

import model.dto.InputDTO;
import model.shape.Shapes;
import model.shape.polygon.Shape;
import view.inputview.InputDisplay;

public class ShapeGenerator {
    private final InputDisplay inputDisplay;
    private boolean inputPointsGenerateWrongShape = true;
    private Shape shape;
    private InputDTO inputDTO;

    public ShapeGenerator(final InputDisplay inputDisplay) throws IllegalArgumentException {
        this.inputDisplay = inputDisplay;
    }

    public Shape generateShape() throws IllegalArgumentException {
        while (inputPointsGenerateWrongShape) {
            generateShapeByPoints();
        }
        return shape;
    }

    private void generateShapeByPoints() throws IllegalArgumentException {
        try {
            inputDTO = InputDTO.create(inputDisplay.inputPoints());
            shape = Shapes.findShape(inputDTO.getInputPoints());
            inputPointsGenerateWrongShape = false;
        } catch (Exception exception) {
            inputDisplay.showExceptionMessage(exception.getMessage());
        }
    }

    public int getNumberOfPoints() {
        return inputDTO.getNumberOfPoints();
    }
}