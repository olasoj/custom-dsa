package com.dsa.functions.chapter1;

public class DonutShop {

    public static Donut buyDonutOld(CreditCard creditCard) {
        Donut donut = new Donut();
        creditCard.charge(Donut.price); //Charges the credit card as a side effect return donut;
        return donut;// C Returns the donut
    }

    public static Tuple<Donut, Payment> buyDonut(CreditCard creditCard) {
        Donut donut = new Donut();
        Payment payment = new Payment(creditCard, Donut.price);
        return new Tuple<>(donut, payment);
    }


}

class Donut {
    public static final double price = 9.0;
}

class CreditCard {
    void charge(double price) {

    }
}