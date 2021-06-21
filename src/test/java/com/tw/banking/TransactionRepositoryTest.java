package com.tw.banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.List;
import org.junit.jupiter.api.Test;

class TransactionRepositoryTest {

    @Test
    void should_return_transaction_with_same_amount_when_invoke_addDeposit() {
        Clock clock = mock(Clock.class);
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        int mockedAmount = 100;
        String mockedTodayString = "Good Mocked";
        given(clock.todayAsString()).willReturn(mockedTodayString);
        transactionRepository.addDeposit(mockedAmount);
        List<Transaction> result = transactionRepository.transactions;
        assertSame(1, result.size());
        assertEquals(mockedTodayString, result.get(0).date());
        assertEquals(mockedAmount, result.get(0).amount());
    }

    @Test
    void should_return_transaction_with_negative_amount_when_invoke_addWithdraw() {
        Clock clock = mock(Clock.class);
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        int mockedAmount = 100;
        String mockedTodayString = "Good Mocked";
        given(clock.todayAsString()).willReturn(mockedTodayString);
        transactionRepository.addWithdraw(mockedAmount);
        List<Transaction> result = transactionRepository.transactions;
        assertSame(1, result.size());
        assertEquals(mockedTodayString, result.get(0).date());
        assertEquals(-1 * mockedAmount, result.get(0).amount());
    }

}