package com.leon.cloud.common.design.decorator;

/**
 * Created by leon on 2019/1/3.
 */
public class Main {

    public static void main(String[] args) {
        Shape shape = new Circle();
        RedAbstractShape redAbstractShape = new RedAbstractShape(shape);
        redAbstractShape.draw();
    }
}
