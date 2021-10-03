package model.shape;

import model.shape.polygon.Rectangle;
import model.shape.polygon.Shape;
import model.shape.polygon.Triangle;

import java.util.List;

public class Shapes {
    private Shapes() {
    }

    public static Shape findShape(List<Point> points) {
        if (points.size() == 2) {
            return Line.generate(points);
        }
        if (points.size() == 3) {
            return Triangle.generate(points);
        }
        return Rectangle.generate(points);
    }
}
