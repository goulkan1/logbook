package com.example.ewe.utility;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class Example {

    List<User> users = List.of(new User("joko", "admin"),
            new User("budi", "user"),
            new User("siti", "user"));

    Multimap<String, String> grouped = ArrayListMultimap.create();

    public void groupUsers() {

        for (User user : users) {
            grouped.put(user.role, user.name);
        }

        System.out.println("grouped = " + grouped);
    }

    public void printUserByRole(String role) {
        Collection<String> userForRole = grouped.get(role);
        System.out.println(userForRole);

    }

    public void iterateUsers() {
        for (var user : grouped.entries()) {
            System.out.println("Role: " + user.getKey() + ", User: " + user.getValue());
        }
    }

    public void clearRole(String role) {
        grouped.removeAll(role);
        System.out.println("Cleared users with role: " + role + grouped);
    }

    public static void main(String[] args) {
        Example example = new Example();
        example.groupUsers();
        example.printUserByRole("admin");
        example.iterateUsers();
        example.clearRole("admin");
    }
}
