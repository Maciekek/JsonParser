package JsonParser;

import domain.Person;

import java.lang.reflect.Field;

/**
 * Created by Maciek on 2015-03-05.
 */
public class JsonParser {
    public static String getJson(Person ob) throws IllegalAccessException {
        String jsonToOutput = "{";
        Field fields[] = ob.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
//            System.out.println(ob.getName());
//            System.out.println(field.get(ob));
            jsonToOutput += "\"" + fields[i].getName() + "\"" + ": ";
            jsonToOutput += "\"" + fields[i].get(ob) + "\"";
            if (i != (fields.length - 1)) {
                jsonToOutput += ",";
            }
        }
        jsonToOutput += "}";
        System.out.println(jsonToOutput);
        return jsonToOutput;


    }
}
