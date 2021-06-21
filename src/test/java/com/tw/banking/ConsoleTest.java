package com.tw.banking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

class ConsoleTest {

    @Test
    void should_invoke_println_line_hello_when_call_printLine_given_hello() {
        PrintStream spyPrintStream = mock(PrintStream.class);
        System.setOut(spyPrintStream);
        Console console = new Console();

        console.printLine("hello Thomas");

        verify(spyPrintStream, times(1)).println("hello Thomas");
    }
}