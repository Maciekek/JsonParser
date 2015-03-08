import JsonParser.JsonParser;
import domain.Person;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        List logins = new ArrayList();
        logins.add("test");
        logins.add("test2");

        Person person = new Person("Maciej", "test", 21, true, logins);
        String json = JsonParser.getJson(person);
        System.out.println(json);


//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        System.out.println(gson.toJson(person));


    }


}


