package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte userId = 10;
        short numOfFriends = 200;
        int numOfMessages = 1500;
        long accountBalance = 500000L;
        float userRating = 4.75F;
        double totalSpent = 12345.67;
        char initial = 'P';
        boolean isActive = true;

        LOG.debug("User ID: {}, Number of Friends: {}, Number of Messages: {}, Account Balance: {}, User Rating: {},"
                        + "Total Spent: {}, Initial: {}, Active: {}",
                userId, numOfFriends, numOfMessages, accountBalance, userRating, totalSpent, initial, isActive);
    }
}