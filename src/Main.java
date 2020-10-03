//  Текст задания:

//  Создать массив с набором слов (20-30 слов, должны встречаться повторяющиеся):
//  Найти список слов, из которых состоит текст (дубликаты не считать);
//  Посчитать сколько раз встречается каждое слово (использовать HashMap);

//  Написать простой класс PhoneBook(внутри использовать HashMap):
//  В качестве ключа использовать фамилию
//  В каждой записи всего два поля: phone, e-mail
//  Отдельный метод для поиска номера телефона по фамилии
//  (ввели фамилию, получили ArrayList телефонов),
//  и отдельный метод для поиска e-mail по фамилии.

//  * Следует учесть, что под одной фамилией может быть несколько записей.
//  Итого должно получиться 3 класса Main, PhoneBook, Person.


import java.util.ArrayList;
import java.util.HashMap;

public class Main
{
    public static final String DEFAULT_STRING = "однажды был случай в далеком макао макака коалу в какао макала коала какао лениво лакала макака макала коала икала";

    public static final String[] FAMILY_NAMES = {"Иванов","Петров","Сидоров","Рабинович","Чапаев","Штирлиц"};
    public static final String[] EMAIL_PREFIXES = {"sales","info","support","marketing","ceo"};
    public static final String[] EMAIL_POSTFIXES = {"yandex.ru","google.com","microsoft.com","facebook.com"};

    public static void main (String[] args)
    {
        // Первое задание
        HashMap<String, Integer> map = stringToMap(DEFAULT_STRING);
        System.out.println("Изначальная строка: " +DEFAULT_STRING);
        System.out.println("Список слов с частотой употребления: " +map);


        // Второе задание
        // Создаем Персону и пытаемся положить ей два одинаковых Емейла и телефона (не прокатит)
        String phone = "0 (000) 000-00-00";
        String email = "sobaka@sobaka.ru";
        Person ivanov = new Person(phone, email);
        ivanov.addPhoneNumber(phone);
        ivanov.addEmail(email);

        System.out.println("\n===> Проверка добавления дублей в одну и ту же персону");
        System.out.println("Переведение переменной ivanov в строку:" +ivanov);
        System.out.println("Если дублей нет - все работает корректно");

        // Несколько раз добавим Иванова в книгу
        // (и под разными и под одними и теми же фамилиями)
        // - он добавится, но при поиске телефонов и емейлов копии не отобразятся
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addPerson(FAMILY_NAMES[0],ivanov);
        phoneBook.addPerson(FAMILY_NAMES[0],ivanov);
        phoneBook.addPerson(FAMILY_NAMES[1],ivanov);


        // Смотрим, телефоны и емейлы Иванова
        String name = FAMILY_NAMES[0];
        ArrayList<String> personPhones = phoneBook.getPhonesByFamilyName(name);
        ArrayList<String> personEmails = phoneBook.getEmailsByFamilyName(name);

        System.out.println();
        System.out.println("===> Результат запроса по фамилии \"" +name +"\" ДО добавления случайных персонажей в базу");
        System.out.println("Телефоны найдены: " +personPhones);
        System.out.println("Емейлы найдены: " +personEmails);

        // Генерируем набор случайных пользователей на основе готовых массивов
        // (телефоны генерятся вообще рандомно) и добавляем их в книгу
        for (int i=0; i<10; i++)
        {
            phoneBook.addPerson(getRandomFromArray(FAMILY_NAMES), generatePerson());
        }

        // Еще раз смотрим, телефоны и емейлы Иванова
        personPhones = phoneBook.getPhonesByFamilyName(name);
        personEmails = phoneBook.getEmailsByFamilyName(name);

        System.out.println();
        System.out.println("===> Результат запроса по фамилии \"" +name +"\" ПОСЛЕ добавления случайных персонажей в базу");
        System.out.println("Телефоны найдены: " +personPhones);
        System.out.println("Емейлы найдены: " +personEmails);


        // Выводим всю книгу для проверки наполнения
        // телефоны и емейлы разных персон выводятся на разных строчках
        System.out.println();
        System.out.println("===> Печать всей книги");
        System.out.println(phoneBook);
    }


    // Конвертим строку в мапу
    public static HashMap<String, Integer> stringToMap (String string)
    {
        String[] words = string.split(" ");

        HashMap<String, Integer> map = new HashMap<>();

        for (String key : words)
        {
            Integer value = map.get(key);

            if (value == null)
                map.put(key,1);
            else
                map.put(key,value+1);
        }

        return map;
    }


    // Генерируем рандомную персону
    public static Person generatePerson ()
    {
        String familyName = getRandomFromArray(FAMILY_NAMES);

        // Количество телефонов от 1 до 3
        String[] phones = new String[(int)(Math.random()*2)+1];
        for (int i=0; i<phones.length; i++)
            phones[i]=getRandomPhoneNumber();

        // Количество емейлов от 1 до 3
        String[] emails = new String[(int)(Math.random()*2)+1];
        for (int i=0; i<emails.length; i++)
            emails[i]= getRandomFromArray(EMAIL_PREFIXES) + "@" + getRandomFromArray(EMAIL_POSTFIXES);

        Person person = new Person (phones,emails);

        return person;
    }


    // Берем случайное значение из массива строк
    private static String getRandomFromArray (String[] array)
    {
        int rnd = (int)(Math.random()*array.length);
        return array[rnd];
    }


    // Генерируем случайный телефонный номер
    public static String getRandomPhoneNumber ()
    {
        String number = Double.toString(Math.random());
        String formattedNumber = "";

        for (int i=0; i<13; i++)
        {
            if (i == 2)
                formattedNumber += number.charAt(i) + " (";

            else if (i == 5)
                formattedNumber += number.charAt(i) + ") ";

            else if (i == 8 || i == 10)
                formattedNumber += number.charAt(i) + "-";

            else if (i > 2)
                formattedNumber += number.charAt(i);
        }

        return formattedNumber;
    }
}
