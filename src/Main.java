import JsonParser.JsonParser;
import domain.Person;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {

        Person person = new Person("Maciej", "test", 21);
        JsonParser.getJson((Person) person);

    }


}


