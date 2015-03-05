package test;

import JsonParser.JsonParser;
import domain.Person;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonParserTest {
    @Test
    public void getJsonTest() throws IllegalAccessException {
        String jsonExpected = "{\"name\": \"Maciej\",\"lastName\": \"test\",\"age\": \"21\"}";

        Person person = new Person("Maciej", "test", 21);

        String jsonCreated = JsonParser.getJson(person);
        System.out.printf(jsonCreated);
        assertEquals(jsonExpected,jsonCreated);
    }

}