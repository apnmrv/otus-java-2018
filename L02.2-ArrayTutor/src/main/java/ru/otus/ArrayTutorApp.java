package ru.otus;

/**
 * ArrayTutorApp
 *
 */
public class ArrayTutorApp
{
    public static void main( String[] args )
    {
        ArrayCopyTutor act = new ArrayCopyTutor();

        act.log("*******************************");
        act.log("Исходный список");
        act.log("*******************************");
        act.fillAnimalsList();
        act.showAnimals();
        act.log("*******************************");
        act.log("Добавим Капибару в конец списка");
        act.log("*******************************");
        act.addAnimal("Капибара");
        act.showAnimals();
        act.log("*******************************");
        act.log("Удалим из 5-й позиции " + act.findAnimal(5));
        act.log("*******************************");
        act.deleteAnimal(5);
        act.showAnimals();
        act.log("*******************************");
        act.log("Вставим Хомяка между "
                + act.findAnimal(2)
                + " и "
                + act.findAnimal(3));
        act.log("*******************************");
        act.insertAnimal(3,"Хомяк");
        act.showAnimals();
        act.log("***************Тест пройден****************");
    }
}
