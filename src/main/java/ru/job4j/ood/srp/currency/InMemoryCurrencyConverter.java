package ru.job4j.ood.srp.currency;

public class InMemoryCurrencyConverter implements CurrencyConverter {
    private static final int CURRENCIES_COUNT = Currency.values().length;
    private final double[][] conversationTable = new double[CURRENCIES_COUNT][CURRENCIES_COUNT];

    public InMemoryCurrencyConverter() {
        /* Актуально на 27.01.2025 */
        this.conversationTable[Currency.RUB.ordinal()][Currency.USD.ordinal()] = 0.01017;
        this.conversationTable[Currency.RUB.ordinal()][Currency.EUR.ordinal()] = 0.00969;
        this.conversationTable[Currency.USD.ordinal()][Currency.EUR.ordinal()] = 1.0500;
        this.conversationTable[Currency.USD.ordinal()][Currency.RUB.ordinal()] = 98.2636;
        this.conversationTable[Currency.EUR.ordinal()][Currency.USD.ordinal()] = 0.9524;
        this.conversationTable[Currency.EUR.ordinal()][Currency.RUB.ordinal()] = 103.1870;
    }

    @Override
    public double convert(Currency source, double sourceValue, Currency target) {
        return sourceValue * (source == target ? 1 : conversationTable[source.ordinal()][target.ordinal()]);
    }
}