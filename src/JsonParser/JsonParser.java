package JsonParser;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Maciek on 2015-03-05.
 */
public class JsonParser {
    public static String getJson(Object ob) {

        StringBuilder jsonToOutput = new StringBuilder("{");

        Field fields[] = ob.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Object elementValue = null;
            try {
                elementValue = fields[i].get(ob);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return "error";
            }
            Type typeOfData = checkTypeOfField(elementValue);

            jsonToOutput.append("\"" + fields[i].getName() + "\"" + ":");

            if (typeOfData == Type.NUMBER || typeOfData == Type.BOOL) {
                jsonToOutput.append(addElementWithOutQuotation(elementValue));
            } else if (typeOfData == Type.LIST) {
                jsonToOutput.append(addCollectionToJson(elementValue));
            } else {
                jsonToOutput.append(addAnyElement(elementValue));
            }

            if (lastElement(fields, i)) {
                jsonToOutput.append(",");
            }
        }
        jsonToOutput.append("}");
        return jsonToOutput.toString();
    }

    public static Object jsonToObject(String json) throws IllegalAccessException {
        List<String> jsonAttributes = prepareJson(json);

        Class c = null;
        try {
            c = Class.forName(getClassNameFromJson(json));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Object object = null;
        try {
            object = c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            String name = getValueFromFieldName(fields[i].getName(), jsonAttributes);
            System.out.println(name);
            Type typeOfData = checkTypeOfName(name);
            if (typeOfData == Type.ANY) {
                try {
                    fields[i].set(object, name);
                } catch (IllegalArgumentException e) {
                    fields[i].setInt(object, Integer.parseInt(name));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if(typeOfData ==Type.BOOL){
                fields[i].setBoolean(object,Boolean.parseBoolean(name));
            }
            if(typeOfData ==Type.LIST){
                //fields[i].setBoolean(object,Boolean.parseBoolean(name));
                System.out.println("dodaje liste!");
            }
        }
        return (Object) object;
    }

    public static String getClassNameFromJson(String json) {
        Pattern p = Pattern.compile("\"ClassName\":\"(.*?)\",");
        Matcher m = p.matcher(json);
        if (m.find()) {
            return m.group(1);
        }

        return null;
    }

    private static String getValueFromFieldName(String name, List<String> jsons) {
        Pattern p = Pattern.compile(".*:\\s*(.*)");
        for (String json : jsons) {
            if (json.contains(name)) {
                Matcher m = p.matcher(json);
                if (m.find()) {
                    return m.group(1);
                }
            }
        }
        return null;
    }

    public static List prepareJson(String json) {
        json = json.substring(1, json.length());
        json = json.substring(0, json.toString().length() - 1);

        json = json.replaceAll("[\"\"]+", "");

        List<String> jsonAttributes;

        jsonAttributes = Arrays.asList(json.split(","));

        return jsonAttributes;
    }

    public static String addCollectionToJson(Object list) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        for (Object element : (List) list) {
            json.append("\"" + element + "\"");
            json.append(",");
        }

        return json.substring(0, json.toString().length() - 1) + "]";
    }

    public static String addAnyElement(Object o) {
        return "\"" + o + "\"";
    }

    public static String addElementWithOutQuotation(Object o) {
        return o.toString();
    }

    public static JsonParser.Type checkTypeOfField(Object element) {

        if (element instanceof Boolean) {
            return Type.BOOL;
        }
        if (element instanceof Number) {
            return Type.NUMBER;
        }
        if (element instanceof List) {
            return Type.LIST;
        }
        return Type.ANY;
    }
    public static JsonParser.Type checkTypeOfName(String name) {
        if (name.equals("true") || name.equals("false")) {
            return Type.BOOL;
        }
//        if (element instanceof Number) {
//            return Type.NUMBER;
//        }
//        if (element instanceof List) {
//            return Type.LIST;
//        }
        return Type.ANY;
    }

    public static boolean lastElement(Field fields[], int i) {
        return i != (fields.length - 1);
    }

    public enum Type {
        BOOL, NUMBER, ANY, LIST
    }
}
