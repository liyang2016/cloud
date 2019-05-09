package com.leon.cloud.common.design.chain;

import java.util.Map;

public interface Filter {
    void doFilter(Map request, Map response, FilterChain filterChain);

    void init();
}
