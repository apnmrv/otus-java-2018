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
    public boolean checkGreeting(String str) {

        String fullString = str.trim();
        int fullStringSize = fullString.length();
        String greeting = fullString.substring(0,7);

        System.out.println("Строка полностью : "+fullString);
        System.out.println("Размер строки : "+fullStringSize);
        System.out.println("Приветствие : "+greeting);

        if (!greeting.equals("Привет,")) {
            System.out.println("В начале должно быть слово Привет и запятая");
            System.out.println("Тест не пройден!");
            System.out.println("#####################################");
            return false;
        }

        if (!fullString.endsWith("!")) {
            System.out.println("В конце должен быть восклицательный знак");
            System.out.println("Тест не пройден!");
            System.out.println("#####################################");
            return false;
        }

        String fullName = fullString.substring(fullString.indexOf(",")+1, fullStringSize-1).trim();
        System.out.println("Имя и фамилия : "+fullName);

        if (fullName.indexOf(" ") == -1) {
            System.out.println("Должны быть указаны и имя, и фамилия");
            System.out.println("Тест не пройден!");
            System.out.println("#####################################");
            return false;
        }

        String firstName = fullName.substring(0,fullName.indexOf(" ")).trim();
        int firstNameSize = firstName.length();
        String firstNameInitial = String.valueOf(firstName.charAt(0));

        String lastName = fullName.substring(firstNameSize+1).trim();
        int lastNameSize = lastName.length();
        String lastNameInitial = String.valueOf(lastName.charAt(0));

        System.out.println("Имя : "+firstName);
        System.out.println("Фамилия : "+lastName);

        if (firstNameSize < 4) {
            System.out.println("Имя слишком короткое");
            System.out.println("Тест не пройден!");
            System.out.println("#####################################");
            return false;
        }

        if (lastNameSize < 4) {
            System.out.println("Фамилия слишком короткая");
            System.out.println("Тест не пройден!");
            System.out.println("#####################################");
            return false;
        }

        if (!firstNameInitial.equals(firstNameInitial.toUpperCase())) {
            System.out.println("Первая буква имени должна быть заглавной");
            System.out.println("Тест не пройден!");
            System.out.println("#####################################");
            return false;
        }

        if (!lastNameInitial.equals(lastNameInitial.toUpperCase())) {
            System.out.println("Первая буква имени должна быть заглавной");
            System.out.println("Тест не пройден!");
            System.out.println("#####################################");
            return false;
        }

        System.out.println("Тест пройден!");
        System.out.println("#####################################");
        return true;
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
