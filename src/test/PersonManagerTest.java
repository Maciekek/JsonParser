package test;

import junit.framework.TestCase;
import service.PersonManager;

public class PersonManagerTest extends TestCase {
    public void test(){
        PersonManager personManager = new PersonManager();
        assertEquals(0, personManager.test());

    }
}