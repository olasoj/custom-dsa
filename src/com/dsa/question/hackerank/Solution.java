package com.dsa.question.hackerank;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/*
 * Create TransactionException, DigitalWallet, and DigitalWalletTransaction classes here.
 */
public class Solution {
    private static final Scanner INPUT_READER = new Scanner(System.in);
    private static final DigitalWalletTransaction DIGITAL_WALLET_TRANSACTION = new DigitalWalletTransaction();

    private static final Map<String, DigitalWallet> DIGITAL_WALLETS = new HashMap<>();

    public static void main(String[] args) {
        int numberOfWallets = Integer.parseInt(INPUT_READER.nextLine());
        while (numberOfWallets-- > 0) {
            String[] wallet = INPUT_READER.nextLine().split(" ");
            DigitalWallet digitalWallet;

            if (wallet.length == 2) {
                digitalWallet = new DigitalWallet(wallet[0], wallet[1]);
            } else {
                digitalWallet = new DigitalWallet(wallet[0], wallet[1], wallet[2]);
            }

            DIGITAL_WALLETS.put(wallet[0], digitalWallet);
        }

        int numberOfTransactions = Integer.parseInt(INPUT_READER.nextLine());
        while (numberOfTransactions-- > 0) {
            String[] transaction = INPUT_READER.nextLine().split(" ");
            DigitalWallet digitalWallet = DIGITAL_WALLETS.get(transaction[0]);

            if (transaction[1].equals("add")) {
                try {
                    DIGITAL_WALLET_TRANSACTION.addMoney(digitalWallet, Integer.parseInt(transaction[2]));
                    System.out.println("Wallet successfully credited.");
                } catch (TransactionException ex) {
                    System.out.println(ex.getErrorCode() + ": " + ex.getMessage() + ".");
                }
            } else {
                try {
                    DIGITAL_WALLET_TRANSACTION.payMoney(digitalWallet, Integer.parseInt(transaction[2]));
                    System.out.println("Wallet successfully debited.");
                } catch (TransactionException ex) {
                    System.out.println(ex.getErrorCode() + ": " + ex.getMessage() + ".");
                }
            }
        }

        System.out.println();

        DIGITAL_WALLETS.keySet()
                .stream()
                .sorted()
                .map((digitalWalletId) -> DIGITAL_WALLETS.get(digitalWalletId))
                .forEachOrdered((digitalWallet) -> {
                    System.out.println(digitalWallet.getWalletId()
                            + " " + digitalWallet.getUsername()
                            + " " + digitalWallet.getWalletBalance());
                });
    }
}

class DigitalWalletTransaction {

    public void addMoney(DigitalWallet digitalWallet, int amount) {

        validateTransaction(digitalWallet, amount);

        if (digitalWallet != null) {
            digitalWallet.setWalletBalance(digitalWallet.getWalletBalance() + amount);
        }

    }

    private void validateTransaction(DigitalWallet digitalWallet, int amount) {
        if (Objects.nonNull(digitalWallet) && (digitalWallet.getUserAccessToken() == null || digitalWallet.getUserAccessToken().isEmpty()))
            throw new TransactionException("User not authorized", "USER_NOT_AUTHORIZED");

        //Amount should be greater than zero
        if (Objects.nonNull(digitalWallet) && (amount <= 0))
            throw new TransactionException("Amount should be greater than zero", "INVALID_AMOUNT");


    }

    public void payMoney(DigitalWallet digitalWallet, int amount) {

        validateTransaction(digitalWallet, amount);

        //Amount should be greater than zero.
        if (Objects.nonNull(digitalWallet) && (amount > digitalWallet.getWalletBalance()))
            throw new TransactionException("Insufficient balance", "INSUFFICIENT_BALANCE");


        if (digitalWallet != null) {
            digitalWallet.setWalletBalance(digitalWallet.getWalletBalance() - amount);
        }

    }

}

class DigitalWallet {

    private final String userName;
    private final String walletId;
    private String userAccessCode;
    private int walletBalance;

    public DigitalWallet(String walletId, String userName) {
        this.userName = userName;
        this.walletId = walletId;
        this.walletBalance = 0;
    }

    public DigitalWallet(String walletId, String userName, String userAccessCode) {
        this(walletId, userName);
        this.userAccessCode = userAccessCode;
    }

    public String getUsername() {
        return userName;
    }

    public String getWalletId() {
        return walletId;
    }

    public String getUserAccessToken() {
        return userAccessCode;
    }

    public int getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(int walletBalance) {
        this.walletBalance = walletBalance;
    }


}

class TransactionException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    public TransactionException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }
}