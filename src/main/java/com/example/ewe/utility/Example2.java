package com.example.ewe.utility;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;

public class Example2 {

    public static void main(String[] args) {
        Multimap<String, String> multimap = ArrayListMultimap
                .create();
        multimap.put("fruit", "apple");
        multimap.put("fruit", "banana");
        multimap.put("vegetable", "carrot");

        System.out.println("Multimap contents: " + multimap);

        ImmutableList<String> immutableList = ImmutableList.of("apple", "banana", "cherry");
        System.out.println("Immutable list contents: " + immutableList);

        String csv = "apple,banana,cherry";
        List<String> fruits = Splitter.on(",").trimResults().splitToList(csv);
        System.out.println("Fruits from CSV: " + fruits);

        String join = Joiner.on(";").join(fruits);
        System.out.println("Joined fruits: " + join);

        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .build();
        cache.put("key1", "value1");
        System.out.println("Cache value for key1: " + cache.getIfPresent("key1"));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Cache value for key1 after expiration: " + cache.getIfPresent("key1"));

    }

}
