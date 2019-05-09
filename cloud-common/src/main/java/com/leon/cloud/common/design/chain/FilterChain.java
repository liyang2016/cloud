package com.leon.cloud.common.design.chain;

import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
class FilterChain {

    private List<Filter> filters = new ArrayList<>();
    private int pos = 0;

    void addFilter(Filter filter) {
        filters.add(filter);
    }

    void doFilter(Map request, Map response) {
        int index = this.pos++;
        if (index >= filters.size()) {
            log.info("request:{},response:{}", request, response.size());
        } else {
            filters.get(index).doFilter(request, response, this);
        }
    }
}
