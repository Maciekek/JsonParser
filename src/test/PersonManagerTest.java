package test;

import domain.Person;
import junit.framework.TestCase;
import service.PersonManager;

import java.util.ArrayList;
import java.util.List;

public class PersonManagerTest extends TestCase {
    public void test(){
        String name= "Maciej";
        String lastName = "Test";
        int age = 21;
        boolean ifAgree = true;
        List logins = new ArrayList();
        logins.add("test");
        logins.add("test2");

        PersonManager personManager = new PersonManager();
        Person personCreated = personManager.createPerson(name, lastName, age,ifAgree, logins);

        Person personExpected = new Person(name, lastName, age,ifAgree, logins);

        assertEquals(personExpected.getName(), personCreated.getName());
        assertEquals(personExpected.getLastName(), personCreated.getLastName());
        assertEquals(personExpected.getAge(), personCreated.getAge());

    }




}