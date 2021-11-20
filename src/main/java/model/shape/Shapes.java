package model.shape;

import model.shape.polygon.Rectangle;
import model.shape.polygon.Shape;
import model.shape.polygon.Triangle;
import model.vo.Point;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Shapes {
    private static final Map<Integer, Function<List<Point>, Shape>> SHAPE_STRATEGY = new HashMap<>();

    static {
        SHAPE_STRATEGY.put(2, Line::generate);
        SHAPE_STRATEGY.put(3, Triangle::generate);
        SHAPE_STRATEGY.put(4, Rectangle::generate);
    }

    private Shapes() {
    }

    public static Shape findShape(List<Point> points) {
        return SHAPE_STRATEGY.get(points.size()).apply(points);
    }
}