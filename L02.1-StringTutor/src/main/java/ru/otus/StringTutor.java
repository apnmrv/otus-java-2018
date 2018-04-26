package ru.otus;

import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.String;

public class StringTutor {



    /**
     *  Убедитесь, что приветствие greeting имеет вид
     *  Привет, Иван Иванов!
     *  или
     *  Привет,Петр Первый!
     *  т.е. начинается на Привет, заканчивается на !
     *  и содержит 2 слова для имени и фамилии,
     *  причем имя и фамилия не короче 3 букв
     *  и начинаются с большой буквы
     */
    public boolean checkGreeting(String greeting) {

        greeting = greeting.trim();
        if (!greeting.substring(0,6).equalsIgnoreCase("Привет,")) {
            System.out.println("В начале должно быть слово Привет и запятая");
            return false;
        }
        if (greeting.charAt(greeting.length() {

        }
        // Characters from 1-st to "," --- Привет,
        // Last char --- !
        // There's a space inside a trimmed string from ","(excluded) to "!"
        // Trimmed string from ","(excluded) to " " includes > 3 chars
        // First char of the trimmed string from ","(excluded) to " " --- uppercase
        // Trimmed string from space to ! includes > 3 chars
        //
        else return true;
    }

    @Test
    public void testCheckGreeting() {
        assertTrue(checkGreeting("Привет, Иван Иванов!"));
        assertTrue(checkGreeting("Привет,Петр Первый!"));
        assertTrue(checkGreeting("Привет, Петр Первый!"));
        assertTrue(checkGreeting("Привет, Петр Первый !"));

        assertFalse("В начале должно быть слово Привет и запятая",
                checkGreeting("Здравствуйте, Петр Первый!"));
        assertFalse("В конце должен быть восклицательный знак",
                checkGreeting("Привет, Петр Первый"));
        assertFalse("Имя слишком короткое",
                checkGreeting("Привет, Ли Сунь!"));
        assertFalse("Фамилия слишком короткая",
                checkGreeting("Привет, Куй Ли!"));
        assertFalse("Должны быть указаны и имя, и фамилия",
                checkGreeting("Привет, Петр!"));
        assertFalse("Первая буква имени должна быть заглавной",
                checkGreeting("Привет, петр Первый!"));
        assertFalse("Первая буква фамилии должна быть заглавной",
                checkGreeting("Привет, Петр первый!"));
    }
}
