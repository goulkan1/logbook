package com.example.ewe.utility;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

public class JsonPathExample {

  public static void main(String[] args) {
    // Example usage of JsonPath
    String json = """
        {
          "store": {
            "book": [
              { "category": "reference",
                "author": "Nigel Rees",
                "title": "Sayings of the Century",
                "price": 8.95
              },
              { "category": "fiction",
                "author": "Evelyn Waugh",
                "title": "Sword of Honour",
                "price": 12.99
              },
              { "category": "fiction",
                "author": "Herman Melville",
                "title": "Moby Dick",
                "isbn": "0-553-21311-3",
                "price": 8.99
              },
              { "category": "fiction",
                "author": "J. R. R. Tolkien",
                "title": "The Lord of the Rings",
                "isbn": "0-395-19395-8",
                "price": 22.99
              }
            ],
            "bicycle": {
              "color": "red",
              "price": 19.95
            }
          }
        }

                """;

    List<String> titles = JsonPath.read(json, "$.store.book[*].title");
    System.out.println("Book Titles: " + titles);

    List<String> fiction = JsonPath.read(json, "$.store.book[?(@.category == 'fiction')].title");
    System.out.println("Fiction Titles: " + fiction);

    List<String> cheapBook = JsonPath.read(json, "$.store.book[?(@.price <10)].title");
    System.out.println("Cheap Books: " + cheapBook);

    Double hargaSepeda = JsonPath.read(json, "$.store.bicycle.price");
    System.out.println("Harga Sepeda: " + hargaSepeda);

    List<String> isbn = JsonPath.read(json, "$.store.book[?(@.isbn)]");
    System.out.println("ISBNs: " + isbn.size());

    String firstBook = JsonPath.read(json, "$.store.book[0].title");
    System.out.println("First Book: " + firstBook);

    List<String> category = JsonPath.read(json, "$.store.book[*].category");
    System.out.println("Categories: " + category);

    // remove duplicates
    List<String> dupList = category.stream().distinct().toList();
    System.out.println("Distinct Categories: " + dupList);

    Map<String, Object> bicyle = JsonPath.read(json, "$.store.bicycle");
    System.out.println(bicyle);

    Configuration config = Configuration.builder().options(Option.SUPPRESS_EXCEPTIONS).build();

    Object result = JsonPath.using(config).parse(json).read("$.store.nonexistent", Object.class);
    System.out.println("Result with Suppress Exceptions: " + result);

    Object result2 = JsonPath.parse(json).read("$.store.nonexistent", Object.class);
    System.out.println("Result with Suppress Exceptions: " + result2);
  }

}