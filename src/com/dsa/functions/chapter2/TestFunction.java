package com.dsa.functions.chapter2;

public class TestFunction {

    static Function<Integer, Integer> compose(Function<Integer, Integer> f1, Function<Integer, Integer> f2) {
        return arg -> f1.apply(f2.apply(arg));
    }

    public static void main(String[] args) {

        Function<Integer, Integer> triple = arg -> arg * 3;
        Function<Integer, Integer> square = arg -> arg * arg;
        square.apply(triple.apply(3));

        Function<Integer, Function<Integer, Integer>> add = x -> y -> x + y;

        Integer apply = add.apply(5).apply(6);
        System.out.println(apply);

        /*
         HIGHER ORDER COMPOSITION
         */

        Function<Function<Integer, Integer>,
                Function<Function<Integer, Integer>,
                        Function<Integer, Integer>>> hof = x -> y -> z -> x.apply(y.apply(z));

        Integer hofApplied = hof.apply(triple)
                .apply(square)
                .apply(4);

        Integer higherCompose2 = Function.<Integer, Integer, Integer>higherCompose()
                .apply(triple)
                .apply(square)
                .apply(4);

        System.out.println(hofApplied);
        System.out.println(higherCompose2);
    }
}
