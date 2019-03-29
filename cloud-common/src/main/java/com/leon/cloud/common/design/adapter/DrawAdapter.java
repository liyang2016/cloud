package com.leon.cloud.common.design.adapter;

/**
 * Created by leon on 2019/1/3.
 */
public class DrawAdapter {
    private DrawAdvanced drawAdvanced;

    public DrawAdapter(String type) {
        if (type.equalsIgnoreCase("rentangle")) {
            this.drawAdvanced = new DrawAdvancedImpl();
        }

    }

    public void draw(String type) {
        if (type.equalsIgnoreCase("rentangle")) {
            drawAdvanced.drawRentangle();
        }
    }
}
