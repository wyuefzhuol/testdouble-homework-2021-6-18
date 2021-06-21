package com.tw.banking;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class AccountTest {
    @Test
    void should_invoke_addDeposit_amount_1_when_call_deposit_given_amount_1() {
        TransactionRepository spyTransactionRepository = mock(TransactionRepository.class);
        Account account = new Account(spyTransactionRepository, mock(Printer.class));
        account.deposit(1);
        verify(spyTransactionRepository, times(1)).addDeposit(1);
    }
    @Test
    void should_invoke_addWithdraw_amount_1_when_call_withdraw_given_amount_1() {
        TransactionRepository spyTransactionRepository = mock(TransactionRepository.class);
        Account account = new Account(spyTransactionRepository, mock(Printer.class));
        account.withdraw(1);

        verify(spyTransactionRepository, times(1)).addWithdraw(1);
    }

    @Test
    void should_invoke_print_stub_transactions_when_call_printStatement() {
        TransactionRepository stubTransactionRepository = mock(TransactionRepository.class);
        Printer spyPrinter = mock(Printer.class);
        Account account = new Account(stubTransactionRepository, spyPrinter);
        List<Transaction> stubTransactions = new ArrayList<>();
        when(stubTransactionRepository.allTransactions()).thenReturn(stubTransactions);

        account.printStatement();

        verify(spyPrinter, times(1)).print(stubTransactions);
    }
}