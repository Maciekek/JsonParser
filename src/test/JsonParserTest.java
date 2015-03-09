package test;

import JsonParser.JsonParser;
import domain.Car;
import domain.Dog;
import domain.Person;
import org.junit.Test;

import com.google.gson.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class JsonParserTest {
    @Test
    public void getJsonTest(){
        List logins = new ArrayList();
        logins.add("Teest");
        logins.add("Test2");
        Person person = new Person("Maciej", "test", 21, true, logins);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String jsonExpected = gson.toJson(person);

        String jsonCreated = JsonParser.getJson(person);
        assertEquals(jsonExpected,jsonCreated);
    }

    @Test
    public void getJsonTestWithCar(){
        Car car = new Car("BMW", "m3", 2009);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String jsonExpected = gson.toJson(car);

        String jsonCreated = JsonParser.getJson(car);
        assertEquals(jsonExpected, jsonCreated);
    }

    @Test
    public void checkTypeOfFieldWithNumberTest(){
        Number number = 9;

        Enum elementType = JsonParser.checkTypeOfField(number);

        assertEquals(JsonParser.Type.NUMBER, elementType);
    }

    @Test
    public void checkTypeOfFieldWithBoolTest(){
        Boolean bool = false;

        Enum elementType = JsonParser.checkTypeOfField(bool);

        assertEquals(JsonParser.Type.BOOL, elementType);
    }

    @Test
      public void addAnyElementTest(){
        String testString = "test";
        String expectedString = "\"" + testString + "\"";

        String actualString = JsonParser.addAnyElement(testString);

        assertEquals(expectedString, actualString);
    }
    @Test
    public void addElementWithOutQuotation(){
        String testString = "test";
        String expectedString = testString;

        String actualString = JsonParser.addElementWithOutQuotation(testString);

        assertEquals(expectedString,actualString);
    }

    @Test
    public void addCollectionToJsonTest(){
        List list = new ArrayList();
        list.add("Test");
        list.add("Test2");
        list.add("Test3");

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String jsonExpected = gson.toJson(list);

        String jsonActual = JsonParser.addCollectionToJson(list);
        assertEquals(jsonExpected, jsonActual);
    }

    @Test
    public void jsonToObjectTest() throws NoSuchMethodException, IllegalAccessException, InstantiationException, NoSuchFieldException, InvocationTargetException, ClassNotFoundException {
        String mark = "BMW";
        String model = "m3";
        int yop = 2009;
        String json = "{\"ClassName\":\"domain.Car\",\"mark\":\""+mark+"\",\"model\":\""+model+"\",\"yop\":"+yop+"}";


        Car carExpected = new Car(mark,model,yop);

        Car carCreated =(Car) JsonParser.jsonToObject(json);

        assertEquals(carExpected.getMark(),carCreated.getMark());
        assertEquals(carExpected.getModel(),carCreated.getModel());
        assertEquals(carExpected.getYop(),carCreated.getYop());
    }

    @Test
    public void jsonToObjectTestWithDog() throws NoSuchMethodException, IllegalAccessException, InstantiationException, NoSuchFieldException, InvocationTargetException, ClassNotFoundException {
        String name = "test";
        int age = 12;
        String json = "{\"ClassName\":\"domain.Dog\",\"name\":\""+name+"\",\"age\":"+age+"}";

        Dog dogExpected = new Dog(name, age);
        Dog dogCreated = (Dog) JsonParser.jsonToObject(json);

        assertEquals(dogExpected.getName(), dogCreated.getName());
        assertEquals(dogExpected.getAge(), dogCreated.getAge());
    }

    @Test
    public void jsonToObjectTestWithListWithStrings() throws IllegalAccessException {
        String name="macek";
        String lastName = "nazwisko";
        int age = 2109;
        boolean ifAgree = true;
        List logins = new ArrayList();
        logins.add("testowy1");
        logins.add("testowy2");
        String jsonToCreateObject = "{\"ClassName\":\"domain.Person\",\"name\":\""+name+"\",\"lastName\":\""+lastName+"\",\"age\":"+age+"," +
                "\"ifAgree\":\"true\",\"logins\":[{\"logins\":"+ "\"testowy1\"" + "},{\"logins\":\"testowy2\"}]}";

        Person personExpected = new Person(name, lastName,age,ifAgree,logins);
        Person personCreated = (Person) JsonParser.jsonToObject(jsonToCreateObject);

        assertEquals(personExpected.getName(), personCreated.getName());
        assertEquals(personExpected.getLastName(), personCreated.getLastName());
        assertEquals(personExpected.getAge(), personCreated.getAge());
        assertEquals(personExpected.getLogins(), personCreated.getLogins());
        assertEquals(personExpected.isIfAgree(), personCreated.isIfAgree());
    }

    @Test
    public void jsonToObjectTestWithListWithNumbers() throws IllegalAccessException {
        String name="macek";
        String lastName = "nazwisko";
        int age = 2109;
        boolean ifAgree = true;
        List logins = new ArrayList();
        logins.add("1");
        logins.add("2");

        String jsonToCreateObject = "{\"ClassName\":\"domain.Person\",\"name\":\""+name+"\",\"lastName\":\""+lastName+"\",\"age\":"+age+"," +
                "\"ifAgree\":true,\"logins\":[{\"logins\":1},{\"logins\":2}]}";

        Person personExpected = new Person(name, lastName,age,ifAgree,logins);
        Person personCreated = (Person) JsonParser.jsonToObject(jsonToCreateObject);

        assertEquals(personExpected.getName(), personCreated.getName());
        assertEquals(personExpected.getLastName(), personCreated.getLastName());
        assertEquals(personExpected.getAge(), personCreated.getAge());
        assertEquals(personExpected.getLogins(), personCreated.getLogins());
        assertEquals(personExpected.isIfAgree(), personCreated.isIfAgree());
    }

    @Test
    public void jsonToObjectTestWithListWithIncompleteJson() throws IllegalAccessException {
        String name="macek";
        String lastName = "null";
        int age = 2109;
        boolean ifAgree = true;
        List logins = new ArrayList();
        logins.add("1");
        logins.add("2");

        String jsonToCreateObject = "{\"ClassName\":\"domain.Person\",\"name\":\""+name+"\",\"age\":"+age+"," +
                "\"ifAgree\":true,\"logins\":[{\"logins\":1},{\"logins\":2}]}";

        Person personExpected = new Person(name, lastName,age,ifAgree,logins);
        Person personCreated = (Person) JsonParser.jsonToObject(jsonToCreateObject);

        assertEquals(personExpected.getName(), personCreated.getName());
        assertEquals(personExpected.getLastName(), personCreated.getLastName());
        assertEquals(personExpected.getAge(), personCreated.getAge());
        assertEquals(personExpected.getLogins(), personCreated.getLogins());
        assertEquals(personExpected.isIfAgree(), personCreated.isIfAgree());
    }

    @Test
    public void prepareJsonTest(){
        String className = "domain.Car";
        String mark = "BMW";
        String model = "m3";
        int yop = 2009;
        String json = "{\"ClassName\":"+className+",\"mark\":\""+mark+"\",\"model\":\""+model+"\",\"yop\":"+yop+"}";

        List<String> jsonFormatExpected = new ArrayList<String>();
        jsonFormatExpected.add("ClassName:"+className);
        jsonFormatExpected.add("mark:"+mark);
        jsonFormatExpected.add("model:"+model);
        jsonFormatExpected.add("yop:"+yop);

        List<String> jsonFormatCreated = JsonParser.prepareJson(json);

        assertEquals(jsonFormatExpected,jsonFormatCreated);
    }

    @Test
    public void getClassNameFromJsonTest(){
        String className = "domain.Car";
        String mark = "BMW";
        String model = "m3";
        int yop = 2009;
        String json = "{\"ClassName\":\""+className+"\",\"mark\":\""+mark+"\",\"model\":\""+model+"\",\"yop\":"+yop+"}";

        String classNameExpected = className;

        String classNameReceived = JsonParser.getClassNameFromJson(json);

        assertEquals(classNameExpected, classNameReceived);
    }



}