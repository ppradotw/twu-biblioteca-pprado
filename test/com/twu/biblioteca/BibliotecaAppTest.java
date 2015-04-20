package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import sun.jvm.hotspot.HelloWorld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class BibliotecaAppTest {

    BibliotecaApp bibliotecaApp;
    Biblioteca    biblioteca;

    @Before
    public void setUp() {
        bibliotecaApp = new BibliotecaApp();
        biblioteca    = new Biblioteca("TW Library");
    }

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Rule
    public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Test
    public void testCreateBibliotecaAppObject() {
        assertNotNull(bibliotecaApp);
    }

    @Test
    public void testWelcomeMessage() {
        systemInMock.provideText("2\n");
        bibliotecaApp.main(null);
        assertEquals("Welcome to TW Library\n" +
                     "1 - List Books\n" +
                     "2 - Quit\n", log.getLog());
    }

    @Test
    public void testMenuMessage() {
        systemInMock.provideText("1\n");
        bibliotecaApp.menu();
        assertEquals("1 - List Books\n" +
                     "2 - Quit\n", log.getLog());
    }

    @Test
    public void testListBooksOption() {
        BibliotecaApp.chooseOption(1, biblioteca);
        assertEquals("  Book           | Author         | Year Published       \n" +
                     "A Song of Ice And Fire | George R. R. Martin | 1996\n" +
                     "Steve Jobs | Walter Isaacson | 2011\n" +
                     "The Hitchhiker's Guide to the Galaxy | Douglas Adams | 1979\n", log.getLog());
    }

    @Test
    public void testInvalidMenuOption() {
        BibliotecaApp.chooseOption(123, biblioteca);
        assertEquals("Select a valid option!\n", log.getLog());
    }


}
