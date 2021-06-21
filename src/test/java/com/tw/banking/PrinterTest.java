package com.tw.banking;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
class PrinterTest {
    @Test
    void should_print_statement_header_when_call_print_given_anyTransactions() {
        Console spyConsole = mock(Console.class);
        Printer printer = new Printer(spyConsole);
        printer.print(anyList());
        verify(spyConsole, times(1)).printLine(Printer.STATEMENT_HEADER);
    }
    @Test
    void should_not_change_given_transactions_when_call_print() {
        Console dummyConsole = mock(Console.class);
        Printer printer = new Printer(dummyConsole);
        Transaction transaction1 = new Transaction("18/06/2021", 1);
        Transaction transaction2 = new Transaction("18/06/2022", 1);
        List<Transaction> givenTransactions = Arrays.asList(transaction1, transaction2);
        printer.print(givenTransactions);
        List<Transaction> expectedTransactions = Arrays.asList(transaction1, transaction2);
        assertTransactionsEquals(expectedTransactions, givenTransactions);
    }
    @Test
    void should_print_with_separator_between_when_call_statementLine_given_date_amount_balance() {
        Console dummyConsole = mock(Console.class);
        Printer printer = new Printer(dummyConsole);
        String result = printer.statementLine(new Transaction("18/06/2021", 1), 2);
        assertEquals("18/06/2021 | 1 | 2", result);
    }
    @Test
    void should_print_with_date_desc_when_call_print_given_date_casual_order() {
        Console spyConsole = mock(Console.class);
        Printer printer = new Printer(spyConsole);
        Transaction transaction1 = new Transaction("18/06/2021", 0);
        Transaction transaction2 = new Transaction("18/06/2022", 0);
        Transaction transaction3 = new Transaction("18/06/2023", 0);
        List<Transaction> givenTransactions = Arrays.asList(transaction1, transaction2, transaction3);
        printer.print(givenTransactions);
        verify(spyConsole, times(1)).printLine("18/06/2021 | 0 | 0");
        verify(spyConsole, times(1)).printLine("18/06/2022 | 0 | 0");
        verify(spyConsole, times(1)).printLine("18/06/2023 | 0 | 0");
    }

    @Test
    void should_print_accumulate_balance_along_with_date_later_when_call_print_given_date_casual_order() {
        Console spyConsole = mock(Console.class);
        Printer printer = new Printer(spyConsole);
        Transaction transaction1 = new Transaction("18/06/2019", 1);
        Transaction transaction2 = new Transaction("18/06/2018", 5);
        Transaction transaction3 = new Transaction("18/06/2017", 2);
        List<Transaction> givenTransactions = Arrays.asList(transaction1, transaction2, transaction3);

        printer.print(givenTransactions);

        verify(spyConsole, times(1)).printLine("18/06/2019 | 1 | 8");
        verify(spyConsole, times(1)).printLine("18/06/2018 | 5 | 3");
        verify(spyConsole, times(1)).printLine("18/06/2017 | 2 | 2");
    }

    private void assertTransactionsEquals(List<Transaction> expectedTransactions, List<Transaction> givenTransactions) {
        for (int i = 0; i < expectedTransactions.size(); i++) {
            assertEquals(expectedTransactions.get(i), givenTransactions.get(i));
        }
    }
}
