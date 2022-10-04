package com.dsa.misc.stream;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TWO;

public class DangerousStream {

    public static void main(String[] args) {

        Instant startTime = Instant.now();
        primes()
                //   .parallel()  //Dangerous parallelization of streams
//                .peek(p ->{if(p.intValueExact() < 60)System.out.println(p);})
                .map(p -> TWO.pow(p.intValueExact())
                        .subtract(ONE))
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(20)
                .forEach(System.out::println);

        Instant endTime = Instant.now();
        Duration timeElapsed = Duration.between(startTime, endTime);
        System.out.println("Operation took: " + timeElapsed.toMillis() + " milliseconds");

    }

    static Stream<BigInteger> primes() {
        return Stream.iterate(TWO, BigInteger::nextProbablePrime);
    }
}
