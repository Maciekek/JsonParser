package JsonParser;

import domain.Person;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;


/**
 * Created by Maciek on 2015-03-05.
 */
public class JsonParser {
    public enum Type {
        BOOL, NUMBER, ANY, LIST
    }
    public static String getJson(Person ob) throws IllegalAccessException {

        StringBuilder jsonToOutput = new StringBuilder("{");
        Field fields[] = ob.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Type typeOfData = checkTypeOfField(fields[i].get(ob));

            jsonToOutput.append("\"" + fields[i].getName() + "\"" + ":");

            if(typeOfData == Type.NUMBER || typeOfData == Type.BOOL ){
                jsonToOutput.append(addElementWithOutQuotation(fields[i].get(ob)));
            } else if(typeOfData == Type.LIST) {
                jsonToOutput.append(addCollectionToJson(fields[i].get(ob)));
            } else{
                jsonToOutput.append(addAnyElement(fields[i].get(ob)));
            }

            if (lastElement(fields, i)) {
                jsonToOutput.append(",");
            }
        }
        jsonToOutput.append("}");
        return jsonToOutput.toString();


    }

    public static String addCollectionToJson(Object list) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        for(Object element :(List) list ){
            json.append("\"" + element + "\"");
            json.append(",");
        }

        return json.substring(0, json.toString().length()-1) + "]";
    }

    public static String addAnyElement(Object o){
        return "\"" + o + "\"";
    }

    public static String addElementWithOutQuotation(Object o) {
        return o.toString();
    }

    public static JsonParser.Type checkTypeOfField(Object element) {

        if (element instanceof Boolean){
            return Type.BOOL;
        }
        if(element instanceof Number){
            return Type.NUMBER;
        }
        if(element instanceof List){
            return Type.LIST;
        }
        return Type.ANY;
    }

    public static boolean lastElement(Field fields[], int i) {
        return i != (fields.length - 1);
    }
}
