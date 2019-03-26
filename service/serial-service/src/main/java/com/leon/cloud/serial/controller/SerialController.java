package com.leon.cloud.serial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by leon on 2019/3/14.
 * 唯一编号生成
 */

@RestController
public class SerialController {

    @GetMapping(value = "/genId")
    public String genId(){
        return "";
    }
}
