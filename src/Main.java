import JsonParser.JsonParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Car;
import domain.Person;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

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
                "\"ifAgree\":true,\"logins\":[{\"logins\":"+ "\"testowy1\"" + "},{\"logins\":\"testowy2\"}]}";
        System.out.println(json);
        Car carCreated = (Car) JsonParser.jsonToObject(json);
//
//        System.out.println("------------------");
//        System.out.println("car model: " + carCreated.getModel());
//        System.out.println("car mark: " + carCreated.getMark());
//        System.out.println("car mark: " + carCreated.getYop());



    }


}


