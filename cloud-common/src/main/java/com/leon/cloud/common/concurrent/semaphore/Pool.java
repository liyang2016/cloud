package com.leon.cloud.common.concurrent.semaphore;

import java.util.concurrent.Semaphore;

class Pool {

    Pool(Object[] items) {
        this.items = items;
    }

    private static final int MAX_AVAILABLE = 10;
    private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

    Object getItem() throws InterruptedException {
        available.acquire();
        return getNextAvailableItem();
    }

    void putItem(Object x) {
        if (markAsUnused(x))
            available.release();
    }

    private Object[] items;
    private boolean[] used = new boolean[MAX_AVAILABLE];

    private synchronized Object getNextAvailableItem() {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (!used[i]) {
                used[i] = true;
                return items[i];
            }
        }
        return null; // not reached
    }

    private synchronized boolean markAsUnused(Object item) {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (item == items[i]) {
                if (used[i]) {
                    used[i] = false;
                    return true;
                } else
                    return false;
            }
        }
        return false;
    }
}

