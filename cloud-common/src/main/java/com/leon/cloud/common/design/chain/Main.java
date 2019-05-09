package com.leon.cloud.common.design.chain;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map request = new HashMap();
        Map response = new HashMap();
        Filter filter = new EncodeFilter();
        Filter xssFilter=new XssFilter();
        FilterChain filterChain=new FilterChain();
        filterChain.addFilter(filter);
        filterChain.addFilter(xssFilter);
        filterChain.doFilter(request,response);
    }
}
