package com.dsa.custom.graph.cc;

public class DiaGraphCC implements ConnectedComponent {

    @Override
    public boolean connected(int v, int w) {
        return false;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public int id(int v) {
        return 0;
    }
}
