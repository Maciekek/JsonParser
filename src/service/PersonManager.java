package service;

import domain.Person;

/**
 * Created by Maciek on 2015-03-04.
 */
public class PersonManager {
    public Person createPerson (String name,String lastName,int age){
        Person person = new Person(name, lastName,age);
        return person;
    }

}
