package com.leon.cloud.common.design.decorator;

/**
 * Created by leon on 2019/1/3.
 */
public class AbstractShape implements Shape {
    private Shape shape;

    public AbstractShape(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void draw() {
        shape.draw();
    }
}
