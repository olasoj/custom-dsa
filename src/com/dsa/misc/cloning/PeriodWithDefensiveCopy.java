package com.dsa.misc.cloning;

import java.util.Date;

public final class PeriodWithDefensiveCopy {
    private final Date start;
    private final Date end;

    /**
     * @param start the beginning of the period
     * @param end   the end of the period; must not precede start * @throws IllegalArgumentException if start is after end
     * @throws NullPointerException if start or end is null
     */
    public PeriodWithDefensiveCopy(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        if (this.start.compareTo(this.end) > 0) throw new IllegalArgumentException(
                this.start + " after " + this.end);
    }

    public static void main(String[] args) {
        Date start = new Date();
        Date end = new Date();
        System.out.println(end);
        PeriodWithDefensiveCopy p = new PeriodWithDefensiveCopy(start, end);
        end.setYear(78);
        System.out.println(p.end);
        p.end().setYear(78); // Modifies internals of p!
        System.out.println(p.end());
    }

    public Date start() {
        return new Date(start.getTime());
    }

    public Date end() {
        return new Date(end.getTime());
    }
}
