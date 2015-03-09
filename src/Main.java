import JsonParser.JsonParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Car;
import domain.Person;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, NoSuchFieldException {
//        List logins = new ArrayList();
//        logins.add("test");
//        logins.add("test2");
//
//        Person person = new Person("Maciej", "test", 21, true, logins);
//        String json = JsonParser.getJson(person);
//        System.out.println(json);
//
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        System.out.println(gson.toJson(person));



        String name="macek";
        String lastName = "nazwisko";
        int age = 21;
        boolean ifAgree = true;
        List logins;


        String json = "{\"ClassName\":\"domain.Person\",\"name\":\""+name+"\",\"lastName\":\""+lastName+"\",\"age\":"+age+"," +
                "\"ifAgree\":\"true\",\"logins\":[{\"logins\":"+ "\"testowy1\"" + "},{\"logins\":\"testowy2\"}]}";

//        String json = "{\"ClassName\":\"domain.Person\",\"name\":\""+name+"\",\"lastName\":\""+lastName+"\",\"age\":"+age+"," +
//                "\"ifAgree\":\"true\",\"logins\":[{\"logins\":21},{\"logins\":22}]}";


        Person pCreated = (Person) JsonParser.jsonToObject(json);
        System.out.println(json);
        System.out.println("MAIN-----");
        System.out.println(pCreated.getLogins().toString());
        System.out.println(pCreated.getLogins().get(0));
        System.out.println(pCreated.getLogins().get(1));
        System.out.println(pCreated.getName());
        System.out.println(pCreated.getAge());
        System.out.println(pCreated.getLastName());


    }


}


