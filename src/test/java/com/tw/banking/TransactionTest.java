package com.tw.banking;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class TransactionTest {
    @Test
    void should_return_1_when_call_compareTo_given_transaction_small() {
        Transaction given = new Transaction("12/05/2019", 1);
        Transaction transaction = new Transaction("12/05/2020", 1);
        int result = transaction.compareTo(given);
        assertEquals(1, result);
    }
    @Test
    void should_return_minus_1_when_call_compareTo_given_transaction_big() {
        Transaction given = new Transaction("12/05/2019", 1);
        Transaction transaction = new Transaction("12/05/2018", 1);
        int result = transaction.compareTo(given);

        assertEquals(-1, result);
    }

    @Test
    void should_return_1_when_call_compareTo_given_transaction_equal() {
        Transaction given = new Transaction("12/05/2019", 1);
        Transaction transaction = new Transaction("12/05/2019", 1);

        int result = transaction.compareTo(given);

        assertEquals(1, result);
    }
}