
import java.util.HashMap;
import java.util.ArrayList;

public class PhoneBook
{
    private HashMap<String, ArrayList<Person>> map = new HashMap<>();

    // Добавляем персону в книгу
    // На идентичность не проверяем вообще, тк в ТЗ не указано,
    // как обрабатывать кейсы с полным или частичным совпадением данных
    public void addPerson (String familyName, Person person)
    {
        ArrayList<Person> namesakes = map.get(familyName);

        if (namesakes == null)
            namesakes = new ArrayList<>();

        namesakes.add(person);

        map.put(familyName,namesakes);
    }


    // Получаем список телефонов по фамилии
    public ArrayList<String> getPhonesByFamilyName (String familyName)
    {
        if (map.containsKey(familyName))
        {
            ArrayList<Person> namesakes = map.get(familyName);

            ArrayList<String> phones = new ArrayList<>();

            for (Person person : namesakes)
                for (String phone : person.getPhoneNumbers())
                    if (!phones.contains(phone))
                        phones.add(phone);

            return phones;
        }
        return null;
    }


    // Получаем список емейлов по фамилии
    public ArrayList<String> getEmailsByFamilyName (String familyName)
    {
        if (map.containsKey(familyName))
        {
            ArrayList<Person> namesakes = map.get(familyName);

            ArrayList<String> emails = new ArrayList<>();

            for (Person person : namesakes)
                for (String email : person.getEmails())
                    if (!emails.contains(email))
                        emails.add(email);

            return emails;
        }
        return null;
    }


    @Override
    public String toString ()
    {
        String string = "";

        for (HashMap.Entry entry : map.entrySet())
        {
            string += "\nФамилия: " + entry.getKey();

            ArrayList<Person> people = (ArrayList<Person>)entry.getValue();

            for (Person p : people)
            {
                string += p.toString();
            }

            string += "\n";
        }
        return string;
    }
}
