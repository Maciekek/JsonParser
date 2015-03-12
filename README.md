# JsonParser
"Program" napisany na potrzeby przedmiotu JVN Internals 2015 Uniwersytet Gdański.

----

##Kilka słów o programie

Zadanie polegało na napisaniu parsera z/do foramtu JSON z użyciem Reflection API. 
Mój formater wspiera następujące typy danych:
>- typy prostę (String, int, boolean, char...)
>- Kolekcje - listy typów prostych. 
>- (TODO) - kolekje obiektów. Ta cześć jest jeszcze w fazie tworzenia. 

###Użycie parsera

Metody główne parsera odpowiedzialne za parsowanie z/do JSON są ```static```, więc łatwo z nich korzystać. 

#####Object to JSON
```
String jsonName = JsonParser.getJson(object);
```


#####JSON to Object
```
ObjectReturnType objectName = 
    (ObjectReturnType) JsonParser.jsonToObject(jsonString, ObjectReturnType.class);
```


###Czas działania
Do uzupełnienia w domu....



##Podsumowanie
Zadanie było bardzo ciekawe. Fajna możliwość podejscia do tematu z którym można się zmierzyć i porównać swoje rozwiązania np. z Googlowym Gson`em. Oczywiście porównanie Gson to mojego JsonParsera to pewnie jak porównanie rowera i samochodu ale jednak dla takich prostych typów różnice czasowe są dosyć spore. A wynika to pewnie z ilości typów wsieranych przez te 2 rozwiązania. Pierwsze zetknięcie z Reflection API dużo nauczyło i było ciekawym doświadczeniem. Odskocznią od uczelnianych implementacji sortowania itp. :) 

