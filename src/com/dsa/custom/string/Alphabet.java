package com.dsa.custom.string;

public interface Alphabet {

//    Alphabet(String s) //Create a new alphabet from chars in s

    char toChar(int index); //convert index to corresponding alphabet char

    int toIndex(char c); //convert c to an index between 0 and R-1

    boolean contains(char c); //is c in the alphabet?

    int R(); //radix (number of characters in alphabet)

    int lgR(); //number of bits to represent an index

    int[] toIndices(String s); //convert s to base-R integer

    String toChars(int[] indices); //convert base-R integer to string over this alphabet
}