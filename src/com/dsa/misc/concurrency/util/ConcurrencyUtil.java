package com.dsa.misc.concurrency.util;

public class ConcurrencyUtil {


    private static final int DOUG_LEA_BLACK_MAGIC_OPERAND_1 = 20;
    private static final int DOUG_LEA_BLACK_MAGIC_OPERAND_2 = 12;
    private static final int DOUG_LEA_BLACK_MAGIC_OPERAND_3 = 7;
    private static final int DOUG_LEA_BLACK_MAGIC_OPERAND_4 = 4;

    private ConcurrencyUtil() {
    }

    /**
     * Returns a hash code for non-null Object x.
     * <p>
     * This function ensures that hashCodes that differ only by
     * constant multiples at each bit position have a bounded
     * number of collisions. (Doug Lea)
     *
     * @param object the object serving as a key
     * @return the hash code
     */
    public static int hash(Object object) {
        int h = object.hashCode();
        h ^= (h >>> DOUG_LEA_BLACK_MAGIC_OPERAND_1) ^ (h >>> DOUG_LEA_BLACK_MAGIC_OPERAND_2);
        return h ^ (h >>> DOUG_LEA_BLACK_MAGIC_OPERAND_3) ^ (h >>> DOUG_LEA_BLACK_MAGIC_OPERAND_4);
    }

    /**
     * Selects a lock for a key. The same lock is always used for a given key.
     *
     * @param key key
     * @return the selected lock index
     */
    public static int selectLock(final Object key, int numberOfLocks) throws IllegalArgumentException {
        int number = numberOfLocks & (numberOfLocks - 1);
        if (number != 0) {
            throw new IllegalArgumentException("Lock number must be a power of two: " + numberOfLocks);
        }
        if (key == null) {
            return 0;
        } else {
            return hash(key) & (numberOfLocks - 1);
        }
    }


    public static int findNextHighestPowerOfTwo(int num) {
        if (num <= 1) {
            return 1;
        } else if (num >= 0x40000000) {
            return 0x40000000;
        }
        int highestBit = Integer.highestOneBit(num);
        return num <= highestBit ? highestBit : highestBit << 1;
    }
}
