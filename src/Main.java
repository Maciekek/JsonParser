import JsonParser.JsonParser;
import com.google.gson.Gson;
import domain.Person;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {

        Person person = new Person();
        Gson gson = new Gson();

        long startGson = System.nanoTime();
        String jsonUsingGson = gson.toJson(person);
        long endGson = System.nanoTime();

        long startJsonParser= System.nanoTime();
        String jsonUsingJsonParser = JsonParser.getJson(person);
        long endJsonParser = System.nanoTime();


        String name="macek";
        String lastName = "nazwisko";
        int age = 2109;

        System.out.println("Gson took: " + (endGson-startGson));
        System.out.println("JsonParser took: " + (endJsonParser-startJsonParser));

        String jsonToCreateObject = "{\"name\":\""+name+"\",\"lastName\":\""+lastName+"\",\"age\":"+age+"," +
                "\"ifAgree\":true,\"logins\":[{\"logins\":1},{\"logins\":2}]}";


        long startByJsonParser= System.nanoTime();
        Person personFromJsonParser = (Person) JsonParser.jsonToObject(jsonToCreateObject, Person.class);
        long endByJsonParser = System.nanoTime();

        System.out.println("Json to object by JsonParser: " + (endByJsonParser - startByJsonParser));

        long startByGson = System.nanoTime();
        Person personFromGson = (Person) gson.fromJson(jsonToCreateObject, Person.class);
        long endByGson = System.nanoTime();
        System.out.println("Json to object by Gson: " + (endByGson-startByGson));


    }

}


