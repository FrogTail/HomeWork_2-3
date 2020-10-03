import java.util.ArrayList;

public class Person
{

    private ArrayList<String> phoneNumbers = new ArrayList<>();;
    private ArrayList<String> emails = new ArrayList<>();


    Person()
    {

    }


    Person (String phone, String email)
    {
        addPhoneNumber(phone);
        addEmail(email);
    }

    Person (String[] phones, String[] emails)
    {
        for (String p : phones)
            addPhoneNumber(p);

        for (String e : emails)
            addEmail(e);
    }


    public void addPhoneNumber (String number)
    {
        if (!phoneNumbers.contains(number))
            phoneNumbers.add(number);
    }


    public void addEmail (String email)
    {
        if (!emails.contains(email))
            emails.add(email);
    }


    // Не совсем уверен, в том, что правильно отправлять ссылки на списки номеров и емейлов,
    // тк после этого с ними можно делать, что угодно, но тк в ТЗ ничего не сказано
    // о практическом использовании данной программы - так и оставлю (может тут так и надо)
    public ArrayList<String> getPhoneNumbers ()
    {return phoneNumbers;}

    public ArrayList<String> getEmails ()
    {return emails;}


    @Override
    public String toString ()
    {
        StringBuilder string = new StringBuilder();

        string.append("\nТелефоны: ");
        for (int i=0; i <phoneNumbers.size(); i++)
        {
            string.append(phoneNumbers.get(i));
            if (i<phoneNumbers.size()-1)
                string.append(", ");
        }

        string.append("\nЕмейлы: ");
        for (int i=0; i <emails.size(); i++)
        {
            string.append(emails.get(i));
            if (i<emails.size()-1)
                string.append(", ");
        }

        return string.toString();
    }
}
