package com.hbsd.rjxy.ect.entity;

public class PlanCount {
    private long total;
    private long done;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getDone() {
        return done;
    }

    public void setDone(long dong) {
        this.done = done;
    }

    public PlanCount(long total, long done) {
        this.total = total;
        this.done = done;
    }
}
