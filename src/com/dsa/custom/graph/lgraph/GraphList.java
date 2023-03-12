package com.dsa.custom.graph.lgraph;

import com.dsa.custom.list.List;
import com.dsa.custom.list.linked.llinked.LList;

import java.util.Iterator;

public class GraphList<E extends Edge> implements List<E> {

    private final List<E> eList;

    public GraphList() {
        this.eList = new LList<>();
    }

    @Override
    public void clear() {
        eList.clear();
    }

    @Override
    public void insert(E item) {
        eList.insert(item);
    }

    @Override
    public void append(E item) {
        eList.append(item);
    }

    @Override
    public E remove() {
        return eList.remove();
    }

    @Override
    public void moveToStart() {
        eList.moveToStart();
    }

    @Override
    public void moveToEnd() {
        eList.moveToEnd();
    }

    @Override
    public void prev() {
        eList.prev();
    }

    @Override
    public void next() {
        eList.next();
    }

    @Override
    public int length() {
        return eList.length();
    }

    @Override
    public int currPos() {
        return eList.currPos();
    }

    @Override
    public void moveToPos(int pos) {
        eList.moveToPos(pos);
    }

    @Override
    public E getValue() {
        return eList.getValue();
    }

    @Override
    public Iterator<E> iterator() {
        return List.super.iterator();
    }
}
