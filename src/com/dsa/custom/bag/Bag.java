package com.dsa.custom.bag;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class Bag<E> implements Iterable<E> {

    private final BagNode<E> head;
    private int size = 0;
    private BagNode<E> head2;

    public Bag() {
        head = new BagNode<>();
        head2 = head;
    }

    public void add(E item) {
        head2.nextBag = new BagNode<>(item);
        head2 = head2.nextBag;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new BagIterator(head.nextBag);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Bag)) return false;
        Bag<?> bag = (Bag<?>) obj;
        return size == bag.size && Objects.equals(head, bag.head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, head);
    }

    @Override
    public String
    toString() {
        return new StringJoiner(", ", Bag.class.getSimpleName() + "[", "]")
                .add("size=" + size)
                .add("head=" + head)
                .toString();
    }

    static class BagNode<E> {
        E value;
        BagNode<E> nextBag;

        public BagNode() {
        }

        public BagNode(E value) {
            this.value = value;
        }

        public BagNode(BagNode<E> nextBag) {
            this.nextBag = nextBag;
        }

        public BagNode(E value, BagNode<E> nextBag) {
            this.value = value;
            this.nextBag = nextBag;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof BagNode)) return false;
            BagNode<?> bagNode = (BagNode<?>) obj;
            return Objects.equals(value, bagNode.value) && Objects.equals(nextBag, bagNode.nextBag);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, nextBag);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", BagNode.class.getSimpleName() + "[", "]")
                    .add("value=" + value)
                    .add("nextBag=" + nextBag)
                    .toString();
        }
    }

    class BagIterator implements Iterator<E> {

        private BagNode<E> bagNode;

        public BagIterator(BagNode<E> bagNode) {
            this.bagNode = bagNode;
        }

        @Override
        public boolean hasNext() {
            return bagNode.nextBag != null;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException("No item in bag");

            BagNode<E> bagNodeLocal = bagNode;
            bagNode = bagNode.nextBag;
            return bagNodeLocal.value;
        }
    }
}


