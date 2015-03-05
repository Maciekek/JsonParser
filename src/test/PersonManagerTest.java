package test;

import domain.Person;
import junit.framework.TestCase;
import service.PersonManager;

public class PersonManagerTest extends TestCase {
    public void test(){
        String name= "Maciej";
        String lastName = "Test";
        int age = 21;

        PersonManager personManager = new PersonManager();
        Person personCreated = personManager.createPerson(name, lastName, age);

        Person personExpected = new Person(name, lastName, age);

        assertEquals(personExpected.getName(), personCreated.getName());
        assertEquals(personExpected.getLastName(), personCreated.getLastName());
        assertEquals(personExpected.getAge(), personCreated.getAge());

    }


}