# JsonParser
Program was written for JVN Internals 2015 University of Gdansk
----


##About program
We had to write program to parsing java object to JSON and rewerse (JSON to java object)
My JsonParser supports following types of data:
>- Simply type (String, int, boolean, char)
>- Collections of simple types
>- (TODO) - Collection of object. This part is under construction now and I hope that I will do this in near future

###Using of JsonParser

Main methods responsibility for parsing from/to JSON are ```static```, so it`s realy easy to use them.

#####Object to JSON
```
String jsonName = JsonParser.getJson(object);
```


#####JSON to Object
```
ObjectReturnType objectName = 
    (ObjectReturnType) JsonParser.jsonToObject(jsonString, ObjectReturnType.class);
```


###Time of parsing to/from JSON
|          |to Json (nanosec)     |from Json (nanosec) |to Json (microsec)     |from Json (micosec) |
|----------|----------------------|--------------------|-----------------------|--------------------|
|JsonParser|814742                |1706452             |814                    |1706                |
|Gson      |22176270              |5989995             |22176                  |5989                |

This time was calculated after 1000 repeat of the same code. This results show 1001 run of loop.



##Summation

It was realy interesting task. Cool oportunity to get knowlegde about Reflection API. This kind of programing is't developed at school. I can see diference between
my solution of Json parsing and Gson. I know that, comparison my json parser and google's is like comparison bike and car. But for simply type of data it can working better than big parser. 
