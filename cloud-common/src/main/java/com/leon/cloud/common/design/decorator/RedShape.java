package com.leon.cloud.common.design.decorator;

/**
 * Created by leon on 2019/1/3.
 */
public class RedShape extends AbstractShape {


    public RedShape(Shape shape) {
        super(shape);
    }

    public void draw() {
        super.draw();
        System.out.println("red");
    }
}
