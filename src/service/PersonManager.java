package service;

import JsonParser.JsonParser;
import domain.Person;

import java.util.List;


/**
 * Created by Maciek on 2015-03-04.
 */
public class PersonManager {
    public Person createPerson (String name,String lastName,int age, boolean ifAgree, List logins){

        Person person = new Person(name, lastName,age, ifAgree,logins);
        return person;
    }



}
