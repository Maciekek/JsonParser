package JsonParser;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
                return "Can`t access to field.";
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

    public static Object jsonToObject(String json, Class classTemplate) throws IllegalAccessException {
        Class c = null;
        Object newObject = null;
        try {
            c = Class.forName(classTemplate.getName());
            newObject = c.newInstance();
        } catch (ClassNotFoundException e) {
            System.out.println("Class: " + classTemplate.getName() + " not found..." );
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.out.println("Unfortunately I can`t create new instantion of " + classTemplate.getName());
            e.printStackTrace();
        }

        Field[] classFields = c.getDeclaredFields();
        for (int i = 0; i < classFields.length; i++) {
            Field fieldName = classFields[i];
            fieldName.setAccessible(true);

            String jsonActualValue = getJsonToThisField(fieldName.getName(), json);
            Type typeOfData = checkTypeOfName(fieldName);

            if (typeOfData == Type.LIST) {
                List preparedValue = prepareListToAdd(jsonActualValue);
                fieldName.set(newObject, preparedValue);
            }

            if (typeOfData == Type.ANY) {
                String preparedValue = getValueFromFieldName(jsonActualValue);
                try {
                    fieldName.set(newObject, preparedValue);
                } catch (IllegalArgumentException e) {
                    fieldName.setInt(newObject, Integer.parseInt(preparedValue));
                }
            }

            if (typeOfData == Type.BOOL) {
                String preparedValue = getValueFromFieldName(jsonActualValue);
                fieldName.setBoolean(newObject, Boolean.valueOf(preparedValue));
            }
        }
        return newObject;
    }

    public static String getValueFromFieldName(String json) {
        Pattern p = Pattern.compile(".*:\"?\\s*(\\w*)");
        Matcher m;
        try {
            m = p.matcher(json);
        } catch (NullPointerException e) {
            return "null";
        }
        if (m.find()) {
            return m.group(1);
        }
        return "null";
    }

    public static List prepareListToAdd(String jsonActualValue) {
        List<String> preparedValues = new ArrayList<String>();
        List<String> jsonAttributes = Arrays.asList(jsonActualValue.split(","));
        for (String json : jsonAttributes) {
            Pattern p = Pattern.compile("\\{\"logins\":\"?(.*?)\"?\\W");
            Matcher m = p.matcher(json);
            if (m.find()) {
                preparedValues.add(m.group(1));
            }
        }
        return preparedValues;
    }

    private static String getJsonToThisField(String name, String json) {
        Pattern p = Pattern.compile("\"" + name + "\":\"?(.*?)\"?,?\\W");
        Pattern p2 = Pattern.compile("\"" + name + "\":\\[\\{.*}?\"?\\w\"?}");

        Matcher m2 = p2.matcher(json);

        if (m2.find()) {
            return m2.group(0);
        } else {
            Matcher m = p.matcher(json);
            if (m.find()) {
                return m.group(0);
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

    public static JsonParser.Type checkTypeOfName(Field name) {
        if (name.getType().toString().contains("java.util.List")) {
            return Type.LIST;
        } else if (name.getType().toString().contains("java.lang.Boolean") || name.getType().toString().contains("boolean")) {
            return Type.BOOL;
        } else {
            return Type.ANY;
        }
    }

    public static boolean lastElement(Field fields[], int i) {
        return i != (fields.length - 1);
    }

    public enum Type {
        BOOL, NUMBER, ANY, LIST
    }
}
