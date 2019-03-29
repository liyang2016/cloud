package com.leon.cloud.common.design.adapter;

/**
 * Created by leon on 2019/1/3.
 */
public class DrawImpl implements Draw {

    private DrawAdapter drawAdapter;

    @Override
    public void draw(String type) {
        if(type.equalsIgnoreCase("rentangle")){
            drawAdapter.draw(type);
        }else{
            this.draw("");
        }
    }
}
