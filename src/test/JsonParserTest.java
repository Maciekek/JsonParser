package test;

import JsonParser.JsonParser;
import domain.Person;
import org.junit.Test;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class JsonParserTest {
    @Test
    public void getJsonTest() throws IllegalAccessException {
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
//    @Test
//    public void lastElementTest(){
//        Field elements;
//        Boolean lastElement = JsonParser.lastElement(elements, 1);
//
//        assertTrue(lastElement);
//
//
//
//    }



}