package com.leon.cloud.common.design.chain;

import lombok.extern.log4j.Log4j2;

import java.util.Map;

@Log4j2
public class EncodeFilter implements Filter{
    private String filterName="";

    EncodeFilter(){
        init();
    }


    @Override
    public void doFilter(Map request, Map response, FilterChain filterChain) {
        log.info("filter: {} work",filterName);
        request.put("xss","xssFilter");
        filterChain.doFilter(request,response);
    }

    @Override
    public void init() {
        filterName="ENCODE_FILTER";
    }
}
