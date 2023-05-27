package com.dsa.functions.chapter4;

import java.util.function.Supplier;

public interface TailCall<T> {

    static <T> Return<T> ret(T t) {
        return new Return<>(t);
    }

    static <T> Suspend<T> sus(Supplier<TailCall<T>> s) {
        return new Suspend<>(s);
    }

    TailCall<T> resume();

    T eval();

    boolean isSuspend();

    class Return<T> implements TailCall<T> {
        private final T t;

        public Return(T t) {
            this.t = t;
        }

        @Override
        public T eval() {
            return t;
        }

        @Override
        public boolean isSuspend() {
            return false;
        }

        @Override
        public TailCall<T> resume() {
            throw new IllegalStateException("Return has no resume");
        }
    }

    class Suspend<T> implements TailCall<T> {
        private final Supplier<TailCall<T>> resume;

        private Suspend(Supplier<TailCall<T>> resume) {
            this.resume = resume;
        }

        @Override
        public T eval() {
            TailCall<T> tailRec = this;
            while (tailRec.isSuspend()) {
                tailRec = tailRec.resume();
            }
            return tailRec.eval();
        }

        @Override
        public boolean isSuspend() {
            return true;
        }

        @Override
        public TailCall<T> resume() {
            return resume.get();
        }
    }
}