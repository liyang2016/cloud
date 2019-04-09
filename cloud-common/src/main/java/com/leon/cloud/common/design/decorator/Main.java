package com.leon.cloud.common.design.decorator;

/**
 * Created by leon on 2019/1/3.
 */
public class Main {

    public static void main(String[] args) {
        Shape shape = new Circle();
        AbstractShape redAbstractShape = new RedShape(shape);
        redAbstractShape.draw();
    }
}
