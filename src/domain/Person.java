package domain;

import java.util.List;

/**
 * Created by Maciek on 2015-03-04.
 */
public class Person {
    public String name;
    public String lastName;
    public int age;
    public boolean ifAgree;
    public List logins;

    public Person() {
    }

    public Person(String name, String lastName, int age, boolean ifAgree, List logins) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.ifAgree = ifAgree;
        this.logins = logins;
    }

    public List getLogins() {
        return logins;
    }

    public void setLogins(List logins) {
        this.logins = logins;
    }

    public boolean isIfAgree() {
        return ifAgree;
    }

    public void setIfAgree(boolean ifAgree) {
        this.ifAgree = ifAgree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
